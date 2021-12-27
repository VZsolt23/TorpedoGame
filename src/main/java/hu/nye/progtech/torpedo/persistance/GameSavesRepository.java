package hu.nye.progtech.torpedo.persistance;

import hu.nye.progtech.torpedo.model.MapVO;

/**
 * Interface for storing and retrieving current Sudoku game states.
 */
public interface GameSavesRepository {
    void save(MapVO currentPlayerMap, MapVO currentAIMap);

    MapVO loadPlayerMap();

    MapVO loadAIMap();
}
