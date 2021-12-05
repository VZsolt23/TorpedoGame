package hu.nye.progtech.torpedo.service.command.implementation;

import static org.junit.jupiter.api.Assertions.*;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.implementation.SaveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SaveCommandTest {
    private static final String SAVE_COMMAND = "save";

    private SaveCommand underTest;
    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    @BeforeEach
    public void setUp(){
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new SaveCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsRight(){
        //given in setup

        //when
        boolean result = underTest.canProcess(SAVE_COMMAND);
        //then
        assertTrue(result);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsWrong(){
        //given
        String input = "I'm wrong";
        //when
        boolean result = underTest.canProcess(input);
        //then
        assertFalse(result);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testProcessShouldSaveCurrentGameState(){
        //given
        MapVO playerMap = Mockito.mock(MapVO.class);
        MapVO aiMap = Mockito.mock(MapVO.class);
        Mockito.when(gameState.getCurrentPlayerMap()).thenReturn(playerMap);
        Mockito.when(gameState.getCurrentAIMap()).thenReturn(aiMap);
        //when
        underTest.process(SAVE_COMMAND);
        //then
        Mockito.verify(gameState).getCurrentPlayerMap();
        Mockito.verify(gameState).getCurrentAIMap();
        Mockito.verify(gameSavesRepository).save(playerMap, aiMap);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerMap, aiMap);
    }

    @Test
    public void testProcessShouldNSaveCurrentGameStateWhenInputIsNull(){
        //given
        MapVO playerMap = Mockito.mock(MapVO.class);
        MapVO aiMap = Mockito.mock(MapVO.class);
        Mockito.when(gameState.getCurrentPlayerMap()).thenReturn(playerMap);
        Mockito.when(gameState.getCurrentAIMap()).thenReturn(aiMap);
        //when
        underTest.process(null);
        //then
        Mockito.verify(gameState).getCurrentPlayerMap();
        Mockito.verify(gameState).getCurrentAIMap();
        Mockito.verify(gameSavesRepository).save(playerMap, aiMap);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerMap, aiMap);
    }
}
