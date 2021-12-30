package hu.nye.progtech.torpedo.service.utility;

import hu.nye.progtech.torpedo.model.MapVO;

/**
 * Util class that helps to extract given parts of a {@link MapVO} object.
 */
public class MapUtility {
    /**
     * Determines if one of the given map is completed or not.
     * A map is considered as completed, if there are no more zeros left in it.
     *
     * @param playerMap the map to check
     * @param aiMap     the map to check
     * @return {@code true} if the map is completed, {@code false} otherwise
     */
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

        if (!result && !result2) {
            return false;
        } else {
            if (result) {
                System.out.println("You lost!");
            } else {
                System.out.println("You won!");
            }
            return true;
        }
    }
}
