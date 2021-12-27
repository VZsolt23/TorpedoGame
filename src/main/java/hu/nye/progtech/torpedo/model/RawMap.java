package hu.nye.progtech.torpedo.model;

import java.util.Objects;

/**
 * Raw representation of a Sudoku map.
 * The actual map state is stored in {@link String} values. This model class is mainly used for persist
 * a given game state.
 */
public class RawMap {
    private String map;
    private String shootable;

    public RawMap(String map, String shootable) {
        this.map = map;
        this.shootable = shootable;
    }

    public String getMap() {
        return map;
    }

    public String getShootable() {
        return shootable;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setShootable(String shootable) {
        this.shootable = shootable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RawMap rawMap = (RawMap) o;
        return map.equals(rawMap.map) && shootable.equals(rawMap.shootable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, shootable);
    }

    @Override
    public String toString() {
        return "RawMap{" +
                "map='" + map + '\'' +
                ", shootable='" + shootable + '\'' +
                '}';
    }
}
