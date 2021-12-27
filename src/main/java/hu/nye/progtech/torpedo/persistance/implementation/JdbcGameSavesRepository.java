package hu.nye.progtech.torpedo.persistance.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import hu.nye.progtech.torpedo.service.utility.MapToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC based implementation of {@link GameSavesRepository}.
 */
public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    static final String INSERT_STATEMENT = "INSERT INTO game_saves " +
            "(id, playermap, playershootable, aimap, aishootable) VALUES (1, ?, ?, ?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE id = 1;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE id = 1;";

    private Connection connection;
    private MapToString mapToString;
    private MapParser mapParser;

    public JdbcGameSavesRepository(Connection connection, MapToString mapToString, MapParser mapParser) {
        this.connection = connection;
        this.mapToString = mapToString;
        this.mapParser = mapParser;
    }

    @Override
    public void save(MapVO currentPlayerMap, MapVO currentAIMap) {
        try {
            deleteCurrentlyStoredSave();
            insertNewSave(currentPlayerMap, currentAIMap);
        } catch (SQLException e) {
            LOGGER.error("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public MapVO loadPlayerMap() {
        RawMap rawMap = readRawMap("playermap", "playershootable");
        try {
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (MapParsingException e) {
            throw new RuntimeException("Failed to parse loaded Player map");
        }
    }

    @Override
    public MapVO loadAIMap() {
        RawMap rawMap = readRawMap("aimap", "aishootable");
        try {
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (MapParsingException e) {
            throw new RuntimeException("Failed to parse loaded AI map");
        }
    }

    private RawMap readRawMap(String columnName1, String columnName2) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT)) {

            resultSet.next();
            String map = resultSet.getString(columnName1);
            String shootable = resultSet.getString(columnName2);
            RawMap rawMap = new RawMap(map, shootable);
            return rawMap;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load map from DB");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Delete old game state from JDBC.
     *
     * @throws SQLException when delete process is failed.
     */
    public void deleteCurrentlyStoredSave() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_STATEMENT);
        }
    }

    /**
     * Insert actual game state to JDBC.
     *
     * @param currentPlayerMap map to save.
     * @param currentAIMap     map to save.
     * @throws SQLException when failed to save game state.
     */
    public void insertNewSave(MapVO currentPlayerMap, MapVO currentAIMap) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, mapToString.convertMapVoMapToString(currentPlayerMap));
            preparedStatement.setString(2, mapToString.convertMapVoShootableToString(currentPlayerMap));
            preparedStatement.setString(3, mapToString.convertMapVoMapToString(currentAIMap));
            preparedStatement.setString(4, mapToString.convertMapVoShootableToString(currentAIMap));
            preparedStatement.executeUpdate();
        }
    }
}
