package hu.nye.progtech.torpedo.service.ingame;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.DeficientCoordinate;
import hu.nye.progtech.torpedo.service.map.MapGenerator;
import hu.nye.progtech.torpedo.ui.MapUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InGameProcess {
    private static Logger LOGGER = LoggerFactory.getLogger(InGameProcess.class);
    MapGenerator mapGenerator = new MapGenerator();
    InputHandler inputHandler = new InputHandler();
    MapUI mapUI = new MapUI();

    public void InGame() {
        char[][] map;
        String choice = "";
        String[] choiceSplit;
        int i = 0, x, y;

        MapVO mapVO = new MapVO(10, 10, mapGenerator.PlaceShips());
        map = mapVO.getMap();
        mapUI.Field(mapVO);

        while (i == 0) {
            System.out.println("Choose a coordinate! 1-10 (e.g. 1,1)");

            choice = inputHandler.InputReader();
            if (choice.toLowerCase().equals("exit")) {
                i = 1;
            } else {
                try {
                    choiceSplit = choice.split(",");
                    try {
                        x = Integer.parseInt(choiceSplit[0]);
                        y = Integer.parseInt(choiceSplit[1]);

                        if (y < 1 || y > 10 || x < 1 || x > 10) {
                            System.out.println("X or y coordinate is wrong! Add new ones!");
                            LOGGER.warn("X or y coordinate is wrong!");
                            continue;
                        } else {
                            if (map[x - 1][y - 1] == '0') {
                                map[x - 1][y - 1] = '+';
                            } else {
                                map[x - 1][y - 1] = 'X';
                            }
                        }
                        MapVO mapVO1 = new MapVO(10, 10, map);
                        mapUI.Field(mapVO1);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong coordinates! Try again!");
                        LOGGER.error("Userinput is wrong! Description: " + e);
                    }
                } catch (Exception e) {
                    try {
                        LOGGER.error("Process failed: choice.split()");
                        throw new DeficientCoordinate("Deficient coordinate! Try again!");
                    } catch (DeficientCoordinate ex) {
                        LOGGER.error("Exception throw failed!" + ex);
                    }
                }
            }
        }
    }
}
