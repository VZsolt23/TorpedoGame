package hu.nye.progtech.torpedo.service.command.implementation;

import java.util.regex.Pattern;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.service.command.performer.AttackPerformer;
import hu.nye.progtech.torpedo.service.exception.AttackException;
import hu.nye.progtech.torpedo.service.ingame.ai.AIAttack;
import hu.nye.progtech.torpedo.ui.HideShips;
import hu.nye.progtech.torpedo.ui.MapUI;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttackCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttackCommand.class);

    private static final String ATTACK_COMMAND_REGEX = "^fire [0-9] [0-9]$";
    private static final String ATTACK_ERROR_MESSAGE = "Can't shoot to this position";

    private final GameState gameState;
    private final AttackPerformer attackPerformer;
    private final PrintWrapper printWrapper;

    public AttackCommand(GameState gameState, AttackPerformer attackPerformer, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.attackPerformer = attackPerformer;
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(ATTACK_COMMAND_REGEX, input);
    }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);
        int columnIndex = Integer.parseInt(parts[2]);
        MapUI mapUI = new MapUI();
        AIAttack aiAttack = new AIAttack();
        HideShips hideShips = new HideShips();

        LOGGER.info("Attack command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
        try {
            MapVO newAIMap = attackPerformer.perform(gameState.getCurrentAIMap(), rowIndex, columnIndex);
            gameState.setCurrentAIMap(newAIMap);

            System.out.println("AI map:");
            mapUI.Field(newAIMap);
            //hideShips.shipHider(gameState.getCurrentAIMap().getMap());

            Thread.sleep(1000);
            MapVO newPlayerMap = aiAttack.perform(gameState.getCurrentPlayerMap());
            gameState.setCurrentPlayerMap(newPlayerMap);

            System.out.println("Your map:");
            mapUI.Field(newPlayerMap);
        } catch (ArithmeticException | AttackException | InterruptedException e) {
            LOGGER.error("Exception occurred while performing shoot operation", e);
            printWrapper.printLine(ATTACK_ERROR_MESSAGE);
        }
    }
}
