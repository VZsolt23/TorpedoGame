package hu.nye.progtech.torpedo.service.ingame.performer;

import hu.nye.progtech.torpedo.service.command.InputHandler;
import hu.nye.progtech.torpedo.service.input.UserInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameStepPerformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    private final UserInputReader userInputReader;
    private final InputHandler inputHandler;

    public GameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }


    public void performGameStep() {
        String input = userInputReader.readInput();
        LOGGER.info("Read user input = '{}'", input);
        inputHandler.handleInput(input);
    }
}
