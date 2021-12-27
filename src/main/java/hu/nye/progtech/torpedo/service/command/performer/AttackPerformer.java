package hu.nye.progtech.torpedo.service.command.performer;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to do an attack at position.
 */
public class AttackPerformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttackPerformer.class);

    /**
     * Shooting to the given position.
     * Shoot if the position is shootable.
     *
     * @param aiMap       the map to update.
     * @param rowIndex    the index of the row.
     * @param columnIndex the index of the column.
     * @return with updated map.
     * @throws AttackException when position is not shootable.
     */
    public MapVO perform(MapVO aiMap, int rowIndex, int columnIndex) throws AttackException {
        LOGGER.info("Performing shoot operation with map = {}, rowIndex = {}, columnIndex = {}",
                aiMap, rowIndex, columnIndex);

        char[][] map = aiMap.getMap();
        boolean[][] shootable = aiMap.getIsShootable();

        if (!shootable[rowIndex][columnIndex]) {
            LOGGER.warn("Can't shoot to this position, as position at rowIndex = {} and columnIndex = {} is fired",
                    rowIndex, columnIndex);
            throw new AttackException("Can't shoot to this position");
        }

        if (map[rowIndex][columnIndex] == '~') {
            map[rowIndex][columnIndex] = 'X';
            shootable[rowIndex][columnIndex] = false;
        } else if (map[rowIndex][columnIndex] == '0') {
            map[rowIndex][columnIndex] = '+';
            shootable[rowIndex][columnIndex] = false;
        }

        return new MapVO(aiMap.getNumberOfRows(), aiMap.getNumberOfColumns(), map, shootable);
    }

}
