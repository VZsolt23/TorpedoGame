package hu.nye.progtech.torpedo.ui;

import java.util.Arrays;

import hu.nye.progtech.torpedo.model.MapVO;

/**
 * Class used to print Battleship map.
 */
public class MapUI {
    public void field(MapVO mapVO) {
        Arrays.stream(mapVO.getMap())
                .forEach(n -> System.out.println(Arrays.toString(n).replace(",", "")));
    }
}
