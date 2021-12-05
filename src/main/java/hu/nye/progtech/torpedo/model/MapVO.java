package hu.nye.progtech.torpedo.model;

import java.util.Arrays;
import java.util.Objects;

public class MapVO {
    private final int numberOfRows;
    private final int numberOfColumns;
    private final char[][] map;
    private final boolean[][] isShootable;

    public MapVO(int numberOfRows, int numberOfColumns, char[][] map, boolean[][] isShootable) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = deepCopy(map);
        this.isShootable = deepCopyS(isShootable);
    }

    private char[][] deepCopy(char[][] map) {
        char[][] result = new char[map.length][];

        for (int i = 0; i < map.length; i++) {
            result[i] = new char[map[i].length];
            for (int j = 0; j < map.length; j++) {
                result[i][j] = map[i][j];
            }
        }

        return result;
    }

    private boolean[][] deepCopyS(boolean[][] shootable) {
        boolean[][] result = new boolean[shootable.length][];

        for (int i = 0; i < map.length; i++) {
            result[i] = new boolean[shootable[i].length];
            for (int j = 0; j < shootable.length; j++) {
                result[i][j] = shootable[i][j];
            }
        }

        return result;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public char[][] getMap() {
        return map;
    }

    public boolean[][] getIsShootable() {
        return isShootable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapVO mapVO = (MapVO) o;
        return numberOfRows == mapVO.numberOfRows && numberOfColumns == mapVO.numberOfColumns && Arrays.deepEquals(map, mapVO.map) && Arrays.deepEquals(isShootable, mapVO.isShootable);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.deepHashCode(map);
        result = 31 * result + Arrays.deepHashCode(isShootable);
        return result;
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", map=" + Arrays.deepToString(map) +
                ", isShootable=" + Arrays.deepToString(isShootable) +
                '}';
    }
}
