package hu.nye.progtech.torpedo.service.command;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InputHandlerTest {
    private static final String INPUT = "something";

    private InputHandler underTest;

    @Mock
    private Command cmd;
    @Mock
    private Command cmd2;

    @BeforeEach
    public void setUp() {
        underTest = new InputHandler(List.of(cmd, cmd2));
    }

    @Test
    public void testHandleInputShouldRunOnlyTheFirstApplicableCommand() {
        // given
        given(cmd.canProcess(INPUT)).willReturn(true);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(cmd).canProcess(INPUT);
        verify(cmd).process(INPUT);
        verifyNoInteractions(cmd2);
    }

    @Test
    public void testHandleInputShouldRunNoCommandsWhenNoneOfThemIsApplicable() {
        // given
        given(cmd.canProcess(INPUT)).willReturn(false);
        given(cmd.canProcess(INPUT)).willReturn(false);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(cmd).canProcess(INPUT);
        verifyNoMoreInteractions(cmd);
        verify(cmd2).canProcess(INPUT);
        verifyNoMoreInteractions(cmd2);
    }
}
