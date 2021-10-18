package hu.nye.progtech.torpedo.service.ingame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputHandlerTest {
    private InputHandler underTest;

    private static String EXPECTED_VALUE = "Test";
    private static String DATA = "Test";

    @BeforeEach
    private void setUp() {
        underTest = new InputHandler();
    }

    @Test
    public void testingUserInput() {
        //given in setUp

        //when
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(DATA.getBytes()));
        String input = underTest.InputReader();
        System.setIn(stdin);
        //then
        Assertions.assertEquals(EXPECTED_VALUE, input);
    }
}
