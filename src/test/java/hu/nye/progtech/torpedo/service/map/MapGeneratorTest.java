package hu.nye.progtech.torpedo.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapGeneratorTest {
    private MapGenerator underTest;

    private static char EXPECTED_DOT = '~';

    @BeforeEach
    public void setUp() {
        underTest = new MapGenerator();
    }

    @Test
    public void testGenerateMapForTheGameCheckValueDot() {
        //given in setup

        //when
        char[][] map = underTest.PlaceShips();
        //then
        assertEquals(EXPECTED_DOT, map[0][0]);
    }

    @Test
    public void testGenerateShootAbleArray(){
        //given
        boolean expected = true;
        //when
        boolean[][] shootable = underTest.ShootablePlaces();
        //then
        assertEquals(expected, shootable[0][0]);
    }
}
