package hu.nye.progtech.torpedo.service.ingame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InGameProcessTest {

    private InGameProcess underTest;

    private static final String EXIT_GAME = "exit";
    private static final String CORRECT_DATA = "2,2";
    private static final String WRONG_DATA = "A,2";
    private static final String WRONG_DATA2 = "22";
    private static final String WRONG_DATA3 = "11,2";

    private static InputStream IS = System.in;

    @BeforeEach
    private void setUp() {
        underTest = new InGameProcess();
    }

    @Test
    public void testingInGameProcessIsWorking() {
        //given in setUp

        //when
        System.setIn(new ByteArrayInputStream(EXIT_GAME.getBytes()));
        underTest.InGame();
        System.setIn(IS);
        //then
    }

    @Test
    public void testingIfUsersChoiceIsCorrect() throws Exception {
        try {
            System.setIn(new ByteArrayInputStream(CORRECT_DATA.getBytes()));
            underTest.InGame();
            System.setIn(IS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testingIfUsersChoiceIsWrong() {
        try {
            System.setIn(new ByteArrayInputStream(WRONG_DATA.getBytes()));
            underTest.InGame();
            System.setIn(IS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testingIfUsersChoiceIsOnlyOneNumberOrLetter() {
        try {
            System.setIn(new ByteArrayInputStream(WRONG_DATA2.getBytes()));
            underTest.InGame();
            System.setIn(IS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testingIfUsersChoiceIsLargerThanExpected() {
        try {
            System.setIn(new ByteArrayInputStream(WRONG_DATA3.getBytes()));
            underTest.InGame();
            System.setIn(IS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
