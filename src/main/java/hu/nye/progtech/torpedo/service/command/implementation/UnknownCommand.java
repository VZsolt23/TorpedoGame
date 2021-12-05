package hu.nye.progtech.torpedo.service.command.implementation;

import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnknownCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnknownCommand.class);

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";

    private final PrintWrapper printWrapper;

    public UnknownCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing default command");
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }
}
