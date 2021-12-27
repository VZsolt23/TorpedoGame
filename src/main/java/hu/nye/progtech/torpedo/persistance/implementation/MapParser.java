package hu.nye.progtech.torpedo.persistance.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses a raw representation of a map into a {@link MapVO} object.
 */
public class MapParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapParser.class);

    private static final String VALID_ROW_REGEX = "[~0X+]+";

    private final int numberOfRows;
    private final int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Parses a map from a raw representation.
     *
     * @param rawMap the raw representation of a map
     * @return a parsed map as a {@link MapVO} object
     * @throws MapParsingException if the raw representation of the map was invalid
     */
    public MapVO parseMap(RawMap rawMap) throws MapParsingException {
        LOGGER.info("Parsing the raw map = {}", rawMap);

        List<String> rows = Arrays.asList(rawMap.getMap().split("\n"));
        checkNumberOfRows(rows);
        checkNumberOfColumns(rows);
        checkValues(rows);
        char[][] map = getMap(rows);

        List<String> shootableRows = Arrays.asList(rawMap.getShootable().split("\n"));
        boolean[][] shootable = getShootable(shootableRows);

        return new MapVO(numberOfRows, numberOfColumns, map, shootable);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParsingException {
        if (rows.size() != numberOfRows) {
            throw new MapParsingException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParsingException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new MapParsingException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkValues(List<String> rows) throws MapParsingException {
        for (String row : rows) {
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParsingException("Row contains invalid characters!");
            }
        }
    }

    private char[][] getMap(List<String> rows) {
        char[][] map = new char[numberOfRows][numberOfColumns];

        for (int x = 0; x < numberOfRows; x++) {
            String row = rows.get(x);
            String[] charactersAsString = row.split("");
            for (int y = 0; y < numberOfColumns; y++) {
                char n = charactersAsString[y].charAt(0);
                map[x][y] = n;
            }
        }

        return map;
    }

    private boolean[][] getShootable(List<String> shootableList) {
        boolean[][] shootable = new boolean[numberOfRows][numberOfColumns];

        for (int i = 0; i < shootableList.size(); i++) {
            String[] shootableValuesAsString = shootableList.get(i).split("");
            for (int j = 0; j < shootableValuesAsString.length; j++) {
                String shootableAsString = shootableValuesAsString[j];
                boolean shootableValue = shootableAsString.equals("1") ? true : false;
                shootable[i][j] = shootableValue;
            }
        }

        return shootable;
    }
}
