package hu.nye.progtech.torpedo.service.command.implementation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.implementation.LoadCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoadCommandTest {
    private static final String LOAD_COMMAND = "load";

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;
    private LoadCommand underTest;

    @BeforeEach
    public void setUp(){
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new LoadCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsRight(){
        //given in setup

        //when
        boolean result = underTest.canProcess(LOAD_COMMAND);
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

    /*@Test
    public void testProcessShouldGetSavedGameState(){
        //given
        MapVO playerMap = Mockito.mock(MapVO.class);
        MapVO aiMap = Mockito.mock(MapVO.class);
        Mockito.when(gameSavesRepository.load()).thenReturn(playerMap,aiMap);
        //when
        underTest.process("input");
        //then
        Mockito.verify(gameSavesRepository).load();
        Mockito.verify(gameSavesRepository).load();
        Mockito.verify(gameState).setCurrentPlayerMap(playerMap);
        Mockito.verify(gameState).setCurrentAIMap(aiMap);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerMap, aiMap);
    }*/


}
