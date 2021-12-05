package hu.nye.progtech.torpedo.persistance.implementation;

import java.sql.*;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import hu.nye.progtech.torpedo.service.utility.MapToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public MapVO load() {
        RawMap rawMap = readRawMap();
        try {
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (MapParsingException e) {
            throw new RuntimeException("Failed to parse loaded map");
        }
    }

    private RawMap readRawMap() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {

            resultSet.next();
            String map = resultSet.getString("map");
            String fixed = resultSet.getString("fixed");

            RawMap rawMap = new RawMap(map, fixed);
            return rawMap;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load map from DB");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void deleteCurrentlyStoredSave() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_STATEMENT);
        }
    }

    private void insertNewSave(MapVO currentPlayerMap, MapVO currentAIMap) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, mapToString.convertMapVoMapToString(currentPlayerMap));
            preparedStatement.setString(2, mapToString.convertMapVoShootableToString(currentPlayerMap));
            preparedStatement.setString(3, mapToString.convertMapVoMapToString(currentAIMap));
            preparedStatement.setString(4, mapToString.convertMapVoShootableToString(currentAIMap));
            preparedStatement.executeUpdate();
        }
    }
}
