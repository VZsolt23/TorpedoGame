package hu.nye.progtech.torpedo.service.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapGeneratorTest {
    private MapGenerator underTest;

    private static int NUMBER_OF_ROWS = 2;
    private static int NUMBER_OF_COLUMNS = 2;

    private static char EXPECTED_DOT = '~';
    private static char EXPECTED_SHIP = '0';

    @BeforeEach
    public void setUp() {
        underTest = new MapGenerator();
    }

    @Test
    public void generateMapForTheGame() {
        //given in setup

        //when
        char[][] map = underTest.PlaceShips();
        //then
        if (map[4][4] == '~') {
            Assertions.assertEquals(EXPECTED_DOT, map[4][4]);
        } else {
            Assertions.assertEquals(EXPECTED_SHIP, map[4][4]);
        }
    }
}
