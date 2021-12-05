package hu.nye.progtech.torpedo.service.ingame.ai;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import hu.nye.progtech.torpedo.service.map.CoordinateGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AIAttackTest {
    private static final char[][] MAP = {{'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},
            {'0', '~', '0', '~', '0', '~', '0', '~', '0', '~'},};
    private static final boolean[][] SHOOTABLE = {
            {false, false, false, false, true, false, false, false, false, false},
            {false, true, false, false, false, false, false, false, false, false},
            {true, false, false, true, false, false, false, false, false, false},
            {false, false, false, false, true, false, false, false, false, false},
            {true, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, true, false, false, false, true, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false}};

    private static final MapVO PLAYER_MAP = new MapVO(10, 10, MAP, SHOOTABLE);

    private AIAttack underTest;
    private CoordinateGenerator coordinateGenerator;

    @BeforeEach
    void setUp() {
        coordinateGenerator = Mockito.mock(CoordinateGenerator.class);
        underTest = new AIAttack();
    }

    @Test
    public void testPerformAiAttackShouldGenerateCoordinates() throws AttackException {
        //given

        //when
        underTest.perform(PLAYER_MAP);
        //then
    }

}
