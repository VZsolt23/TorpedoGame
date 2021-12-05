package hu.nye.progtech.torpedo.service.utility;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.MapVO;

public class MapUtility {
    public boolean isMapCompleted(MapVO playerMap, MapVO aiMap) {
        boolean result = true;
        boolean result2 = true;

        char[][] map = playerMap.getMap();
        char[][] map2 = aiMap.getMap();
        for (char[] row : map) {
            for (char ship : row) {
                if (ship == '0') {
                    result = false;
                    break;
                }
            }
        }

        for (char[] row : map2) {
            for (char ship : row) {
                if (ship == '0') {
                    result2 = false;
                    break;
                }
            }
        }

        if (!result || !result2) {
            return false;
        }else{
            return true;
        }
    }
}
