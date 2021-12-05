package hu.nye.progtech.torpedo.persistance;

import hu.nye.progtech.torpedo.model.MapVO;

public interface GameSavesRepository {
    void save(MapVO currentPlayerMap, MapVO currentAIMap);

    MapVO load();
}
