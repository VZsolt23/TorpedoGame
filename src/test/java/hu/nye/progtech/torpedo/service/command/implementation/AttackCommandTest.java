package hu.nye.progtech.torpedo.service.command.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Map;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.command.implementation.AttackCommand;
import hu.nye.progtech.torpedo.service.command.performer.AttackPerformer;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import hu.nye.progtech.torpedo.service.ingame.ai.AIAttack;
import hu.nye.progtech.torpedo.ui.MapUI;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AttackCommandTest {
    private static final String ATTACK_COMMAND = "fire 2 3";
    private static final String ATTACK_ERROR_MESSAGE = "Can't shoot to this position";

    private static final char[][] MAP = {{'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},
                                        {'0','~','0','~','0','~','0','~','0','~'},};
    private static final boolean[][] SHOOTABLE = {{true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},
                                                {true,true,true,true,true,true,true,true,true,true},};

    private static final MapVO PLAYER_MAP = new MapVO(10, 10, MAP, SHOOTABLE);
    private static final MapVO AI_MAP = new MapVO(10, 10, MAP, SHOOTABLE);
    private static final MapVO NEW_PLAYER_MAP = new MapVO(10, 10, MAP, SHOOTABLE);
    private static final MapVO NEW_AI_MAP = new MapVO(10, 10, MAP, SHOOTABLE);

    private static final int ROW_INDEX = 2;
    private static final int COLUMN_INDEX = 3;

    private AttackCommand underTest;
    private GameState gameState;
    @Mock
    private AIAttack aiAttack;
    @Mock
    private PrintWrapper printWrapper;
    @Mock
    private MapUI mapUI;
    @Mock
    private AttackPerformer attackPerformer;

    @BeforeEach
    public void setUp() {
        attackPerformer = Mockito.mock(AttackPerformer.class);
        aiAttack = Mockito.mock(AIAttack.class);
        mapUI = Mockito.mock(MapUI.class);
        gameState = new GameState(PLAYER_MAP, AI_MAP, false);
        underTest = new AttackCommand(gameState, attackPerformer, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsRight() {
        //given in setup

        //when
        boolean result = underTest.canProcess(ATTACK_COMMAND);
        //then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsWrong() {
        //given
        String input = "I'm wrong";
        //when
        boolean result = underTest.canProcess(input);
        //then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPerformAttack() throws AttackException {
        // given
        given(attackPerformer.perform(AI_MAP, ROW_INDEX, COLUMN_INDEX)).willReturn(NEW_PLAYER_MAP);
        given(aiAttack.perform(AI_MAP)).willReturn(NEW_AI_MAP);

        // when
        underTest.process(ATTACK_COMMAND);

        // then
        verify(attackPerformer).perform(AI_MAP, ROW_INDEX, COLUMN_INDEX);
        assertEquals(NEW_PLAYER_MAP, gameState.getCurrentAIMap());
    }
}
