package hu.nye.progtech.torpedo.persistance.implementation;

import java.sql.*;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import hu.nye.progtech.torpedo.service.utility.MapToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JdbcGameSavesRepositoryTest {
    private JdbcGameSavesRepository underTest;

    private Connection connection;
    private MapToString mapToString;
    private MapParser mapParser;

    @BeforeEach
    public void init() {
        connection = Mockito.mock(Connection.class);
        mapToString = Mockito.mock(MapToString.class);
        mapParser = Mockito.mock(MapParser.class);

        underTest = new JdbcGameSavesRepository(connection, mapToString, mapParser);
    }

    @Test
    public void testSaveShouldDoNothingWhenThereIsAnSqlException() throws SQLException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        underTest.save(currentMap, currentMap);

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, currentMap);
    }

    @Test
    public void testCloseShouldDelegateCloseCallToConnection() throws Exception {
        // Given

        // When
        underTest.close();

        // Then
        Mockito.verify(connection).close();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser);
    }

    @Test
    public void testPlayerLoadShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.loadPlayerMap());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenPlayerMapParsingExceptionIsThrown() throws SQLException, MapParsingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("playermap")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("playershootable")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenThrow(MapParsingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.loadPlayerMap());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("playermap");
        Mockito.verify(resultSet).getString("playershootable");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, statement, resultSet);
    }

    @Test
    public void testLoadShouldReturnAPlayerMapVOWhenThereIsNoException() throws SQLException, MapParsingException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("playermap")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("playershootable")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenReturn(expected);

        // When
        MapVO actual = underTest.loadPlayerMap();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("playermap");
        Mockito.verify(resultSet).getString("playershootable");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAILoadShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.loadAIMap());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenAIMapParsingExceptionIsThrown() throws SQLException, MapParsingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("aimap")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("aishootable")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenThrow(MapParsingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.loadAIMap());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("aimap");
        Mockito.verify(resultSet).getString("aishootable");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, statement, resultSet);
    }

    @Test
    public void testLoadShouldReturnAAIMapVOWhenThereIsNoException() throws SQLException, MapParsingException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("aimap")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("aishootable")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenReturn(expected);

        // When
        MapVO actual = underTest.loadAIMap();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("aimap");
        Mockito.verify(resultSet).getString("aishootable");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testInsertNewSaveShouldUploadMapsToDataBase() throws SQLException {
        // Given
        MapVO PLAYER_MAP = Mockito.mock(MapVO.class);
        MapVO AI_MAP = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT))
                .thenReturn(preparedStatement);
        String playerMapString = "playerMapString";
        Mockito.when(mapToString.convertMapVoMapToString(PLAYER_MAP)).thenReturn(playerMapString);
        String playerShootableString = "playerShootableString";
        Mockito.when(mapToString.convertMapVoShootableToString(PLAYER_MAP)).thenReturn(playerShootableString);
        String aiMapString = "aiMapString";
        Mockito.when(mapToString.convertMapVoMapToString(AI_MAP)).thenReturn(aiMapString);
        String aiShootableString = "aiShootableString";
        Mockito.when(mapToString.convertMapVoShootableToString(AI_MAP)).thenReturn(aiShootableString);

        // When
        underTest.save(PLAYER_MAP, AI_MAP);

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeUpdate(JdbcGameSavesRepository.DELETE_STATEMENT);
        Mockito.verify(statement).close();
        Mockito.verify(connection).prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT);
        Mockito.verify(mapToString).convertMapVoMapToString(PLAYER_MAP);
        Mockito.verify(preparedStatement).setString(1, playerMapString);
        Mockito.verify(mapToString).convertMapVoShootableToString(PLAYER_MAP);
        Mockito.verify(preparedStatement).setString(2, playerShootableString);
        Mockito.verify(mapToString).convertMapVoMapToString(AI_MAP);
        Mockito.verify(preparedStatement).setString(3, aiMapString);
        Mockito.verify(mapToString).convertMapVoShootableToString(AI_MAP);
        Mockito.verify(preparedStatement).setString(4, aiShootableString);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(preparedStatement).close();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, PLAYER_MAP, AI_MAP,
                statement, preparedStatement);
    }
}
