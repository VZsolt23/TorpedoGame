package hu.nye.progtech.torpedo.service.command.implementation;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.ui.MapUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to print player and AI map.
 */
public class PrintCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;

    public PrintCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        MapUI mapUI = new MapUI();
        LOGGER.info("Performing print command");
        System.out.println("Your map:");
        mapUI.field(gameState.getCurrentPlayerMap());
        System.out.println("\nAI map:");
        mapUI.field(gameState.getCurrentAIMap());
    }
}
