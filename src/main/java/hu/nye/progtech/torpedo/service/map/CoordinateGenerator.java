package hu.nye.progtech.torpedo.service.map;

import java.util.Random;

/**
 * Class used to generate numbers which used to coordinate.
 */
public class CoordinateGenerator {
    /**
     * Number generation.
     *
     * @return with a number.
     */
    public int generateCoordinate() {
        int coordinate;
        Random r = new Random();
        coordinate = r.nextInt(10);
        return coordinate;
    }
}
