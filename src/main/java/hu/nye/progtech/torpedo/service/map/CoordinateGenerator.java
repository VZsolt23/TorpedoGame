package hu.nye.progtech.torpedo.service.map;

import java.util.Random;

public class CoordinateGenerator {
    public int GenerateCoordinate() {
        int coordinate;
        Random r = new Random();
        coordinate = r.nextInt(10);
        return coordinate;
    }
}
