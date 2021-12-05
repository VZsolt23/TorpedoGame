package hu.nye.progtech.torpedo.service.command.performer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttackPerformerTest {
    private static final int TARGET_ROW_INDEX = 1;
    private static final int TARGET_COLUMN_INDEX = 1;

    private static final char[][] MAP = {{'~','~','~'},
                                        {'~','0','~'},
                                        {'~','~','~'}};
    private static final boolean[][] SHOOTABLE = {{true,true,true},
                                                {true,true,true},
                                                {true,true,true}};
    private static final boolean[][] EXPECTED_SHOOTABLE = {{true,true,true},
            {true,false,true},
            {true,true,true}};
    private static final boolean[][] WRONG_SHOOTABLE = {{false,false,false},
            {false,false,false},
            {false,false,false}};
    private static final char[][] EXPECTED_MAP = {{'~','~','~'},
                                                {'~','+','~'},
                                                {'~','~','~'}};

    private static final MapVO AI_MAP = new MapVO(3,3, MAP, SHOOTABLE);
    private static final MapVO WRONG_MAP = new MapVO(3,3, MAP, WRONG_SHOOTABLE);
    private static final MapVO EXPECTED_AI_MAP =
            new MapVO(3, 3, EXPECTED_MAP, EXPECTED_SHOOTABLE);

    private AttackPerformer underTest;

    @BeforeEach
    public void underTest(){
        underTest = new AttackPerformer();
    }

    @Test
    public void testAttackPerformerShouldCreateANewMap() throws AttackException {
        //given

        //when
        MapVO result = underTest.perform(AI_MAP, TARGET_ROW_INDEX, TARGET_COLUMN_INDEX);
        //then
        assertEquals(EXPECTED_AI_MAP, result);
    }

    @Test
    public void testAttackPerformShouldThrowAttackException(){
        //given

        //when
        assertThrows(AttackException.class, () ->
        {underTest.perform(WRONG_MAP, TARGET_ROW_INDEX, TARGET_COLUMN_INDEX);});
    }
}
