package hu.nye.progtech.torpedo.service.ingame;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.ingame.performer.GameStepPerformer;
import hu.nye.progtech.torpedo.service.utility.MapUtility;
import hu.nye.progtech.torpedo.ui.MenuUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component that controls the flow of a game.
 */
public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private final GameState gameState;
    private final GameStepPerformer gameStepPerformer;
    private final MapUtility mapUtil;

    public Controller(GameState gameState, GameStepPerformer gameStepPerformer, MapUtility mapUtil) {
        this.gameState = gameState;
        this.gameStepPerformer = gameStepPerformer;
        this.mapUtil = mapUtil;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        LOGGER.info("Starting game loop");
        MenuUI menuUI = new MenuUI();
        menuUI.menuOptions();
        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
        }
        LOGGER.info("Game loop finished");
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit() &&
                !mapUtil.isMapCompleted(gameState.getCurrentPlayerMap(), gameState.getCurrentAIMap());
    }
}
