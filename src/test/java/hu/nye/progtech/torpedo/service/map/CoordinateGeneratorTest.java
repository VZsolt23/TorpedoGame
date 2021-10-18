package hu.nye.progtech.torpedo.service.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateGeneratorTest {
    private CoordinateGenerator underTest;

    private static final boolean EXPECTED_RESULT = true;

    @BeforeEach
    public void setUP() {
        underTest = new CoordinateGenerator();
    }

    @Test
    public void randomNumberGeneration() {
        //given in setup

        //when
        int number = underTest.GenerateCoordinate();
        boolean result = false;
        if (number >= 0 && number < 10) {
            result = true;
        }
        //then
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }
}
