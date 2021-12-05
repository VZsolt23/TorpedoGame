package hu.nye.progtech.torpedo.service.ingame.performer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.torpedo.service.command.InputHandler;
import hu.nye.progtech.torpedo.service.input.UserInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameStepPerformerTest {
    private static final String INPUT = "something";

    private GameStepPerformer underTest;

    @Mock
    private UserInputReader userInputReader;
    @Mock
    private InputHandler inputHandler;

    @BeforeEach
    public void setUp() {
        underTest = new GameStepPerformer(userInputReader, inputHandler);
    }

    @Test
    public void testPerformGameStepIsWorking() {
        //given
        given(userInputReader.readInput()).willReturn(INPUT);
        //when
        underTest.performGameStep();
        //then
        verify(userInputReader).readInput();
        verify(inputHandler).handleInput(INPUT);
    }
}
