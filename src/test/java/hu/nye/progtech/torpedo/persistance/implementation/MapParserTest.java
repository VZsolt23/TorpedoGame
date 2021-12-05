package hu.nye.progtech.torpedo.persistance.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapParserTest {
    private static final String MAP = "~~\n0~\n";
    private static final String WRONG_MAP = "1~~\n0~2\n";
    private static final String SHOOTABLE = "11\n11\n";
    private static int ROW_INDEX = 2;
    private static int COLUMN_INDEX = 2;

    private static final char[][] EXPECTED_MAP = {{'~', '~'}, {'0', '~'}};
    private static final boolean[][] EXPECTED_SHOOTABLE = {{true, true}, {true, true}};

    private static final MapVO EXPECTED_MAPVO = new MapVO(ROW_INDEX, COLUMN_INDEX, EXPECTED_MAP, EXPECTED_SHOOTABLE);

    private RawMap rawMap1, rawMap2;
    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        rawMap1 = new RawMap(MAP, SHOOTABLE);
        rawMap2 = new RawMap(WRONG_MAP, SHOOTABLE);
        underTest = new MapParser(ROW_INDEX, COLUMN_INDEX);
    }

    @Test
    public void testParseMapShouldReturnNewMapVO() throws MapParsingException {
        //given

        //when
        MapVO mapVO = underTest.parseMap(rawMap1);
        //then
        assertEquals(EXPECTED_MAPVO, mapVO);
    }

    @Test
    public void testParseMapShouldThrowException() throws MapParsingException {
        //given

        //when
        assertThrows(MapParsingException.class, () -> {
            underTest.parseMap(rawMap2);
        });
    }
}
