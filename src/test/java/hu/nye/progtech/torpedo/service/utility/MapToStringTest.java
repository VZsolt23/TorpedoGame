package hu.nye.progtech.torpedo.service.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.nye.progtech.torpedo.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapToStringTest {
    private static final char[][] MAP = {{'~','~'},{'~','0'}};
    private static final boolean[][] SHOOTABLE = {{true,true},{true,true}};

    private static final String EXPECTED_MAP = "~~\n~0\n";
    private static final String EXPECTED_SHOOTABLE = "11\n11\n";

    private static final MapVO mapVO = new MapVO(2,2, MAP, SHOOTABLE);

    private MapToString underTest;

    @BeforeEach
    public void setUp(){
        underTest = new MapToString();
    }

    @Test
    public void testMapToStringShouldReturnWithAMapRepresentation(){
        //given

        //when
        String result = underTest.convertMapVoMapToString(mapVO);
        //then
        assertEquals(EXPECTED_MAP, result);
    }

    @Test
    public void testMapToStringShouldReturnWithAShootAbleRepresentation(){
        //given

        //when
        String result = underTest.convertMapVoShootableToString(mapVO);
        //then
        assertEquals(EXPECTED_SHOOTABLE, result);
    }
}
