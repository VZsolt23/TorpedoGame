package hu.nye.progtech.torpedo.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class MapGeneratorTest {
    private MapGenerator underTest;

    private static char EXPECTED_DOT = '~';
    private static char EXPECTED_SHIP = '0';

    @BeforeEach
    public void setUp() {
        underTest = new MapGenerator();
    }

    @Test
    public void testGenerateMapForTheGameCheckValueDot() {
        //given in setup

        //when
        char[][] map = underTest.placeShips();
        //then
        Assert.isTrue(EXPECTED_DOT == map[0][0] || EXPECTED_SHIP == map[0][0]);
    }

    @Test
    public void testGenerateShootAbleArray(){
        //given
        boolean expected = true;
        //when
        boolean[][] shootable = underTest.shootablePlaces();
        //then
        assertEquals(expected, shootable[0][0]);
    }
}
