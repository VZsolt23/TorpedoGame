package hu.nye.progtech.torpedo.service.ingame.ai;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.command.performer.AttackPerformer;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import hu.nye.progtech.torpedo.service.map.CoordinateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to make AI attacks.
 */
public class AIAttack {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttackPerformer.class);

    /**
     * Performing an AI attack.
     *
     * @param playerMap to update map.
     * @return updated player map.
     * @throws AttackException failed attack move.
     */
    public MapVO perform(MapVO playerMap) throws AttackException {
        LOGGER.info("Performing AI shoot operation with map = {}",
                playerMap);

        char[][] map = playerMap.getMap();
        boolean[][] shootable = playerMap.getIsShootable();

        CoordinateGenerator cg = new CoordinateGenerator();
        int x = cg.generateCoordinate();
        int y = cg.generateCoordinate();
        while (!shootable[y][x]) {
            LOGGER.info("AI choosed wrong position");
            x = cg.generateCoordinate();
            y = cg.generateCoordinate();
        }

        if (map[y][x] == '~') {
            map[y][x] = 'X';
            shootable[y][x] = false;
        } else if (map[y][x] == '0') {
            map[y][x] = '+';
            shootable[y][x] = false;
        }

        return new MapVO(playerMap.getNumberOfRows(), playerMap.getNumberOfColumns(), map, shootable);
    }
}
