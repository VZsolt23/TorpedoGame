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
    public void testLoadShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenMapParsingExceptionIsThrown() throws SQLException, MapParsingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("map")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("fixed")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenThrow(MapParsingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("map");
        Mockito.verify(resultSet).getString("fixed");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, statement, resultSet);
    }

    @Test
    public void testLoadShouldReturnAMapVOWhenThereIsNoException() throws SQLException, MapParsingException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("map")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("fixed")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenReturn(expected);

        // When
        MapVO actual = underTest.load();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("map");
        Mockito.verify(resultSet).getString("fixed");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }
}
