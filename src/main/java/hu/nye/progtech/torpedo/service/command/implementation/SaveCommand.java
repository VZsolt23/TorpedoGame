package hu.nye.progtech.torpedo.service.command.implementation;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to save actual game state.
 */
public class SaveCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    public SaveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Save command was called");
        gameSavesRepository.save(gameState.getCurrentPlayerMap(), gameState.getCurrentAIMap());
        LOGGER.info("Save was successfully persisted");
    }
}
