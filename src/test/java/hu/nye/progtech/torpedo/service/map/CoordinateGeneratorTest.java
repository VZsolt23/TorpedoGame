package hu.nye.progtech.torpedo.service.map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateGeneratorTest {
    private CoordinateGenerator underTest;

    @BeforeEach
    public void setUP() {
        underTest = new CoordinateGenerator();
    }

    @Test
    public void randomNumberGeneration() {
        //given in setup

        //when
        int number = underTest.generateCoordinate();
        //then
        assertTrue(0 <= number && number <= 9);
    }
}
