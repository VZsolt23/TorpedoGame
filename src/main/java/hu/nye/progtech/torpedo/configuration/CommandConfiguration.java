package hu.nye.progtech.torpedo.configuration;

import java.util.List;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.service.command.InputHandler;
import hu.nye.progtech.torpedo.service.command.implementation.AttackCommand;
import hu.nye.progtech.torpedo.service.command.implementation.ExitCommand;
import hu.nye.progtech.torpedo.service.command.implementation.LoadCommand;
import hu.nye.progtech.torpedo.service.command.implementation.PrintCommand;
import hu.nye.progtech.torpedo.service.command.implementation.SaveCommand;
import hu.nye.progtech.torpedo.service.command.implementation.UnknownCommand;
import hu.nye.progtech.torpedo.service.command.performer.AttackPerformer;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for command specific Spring Beans.
 */
@Configuration
public class CommandConfiguration {
    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    PrintCommand printCommand(GameState gameState) {
        return new PrintCommand(gameState);
    }

    @Bean
    AttackCommand attackCommand(GameState gameState, AttackPerformer attackPerformer,
                                PrintWrapper printWrapper) {
        return new AttackCommand(gameState, attackPerformer, printWrapper);
    }

    @Bean
    SaveCommand saveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new SaveCommand(gameSavesRepository, gameState);
    }

    @Bean
    LoadCommand loadCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new LoadCommand(gameSavesRepository, gameState);
    }

    @Bean
    ExitCommand exitCommand(GameState gameState) {
        return new ExitCommand(gameState);
    }

    @Bean
    UnknownCommand unknownCommand(PrintWrapper printWrapper) {
        return new UnknownCommand(printWrapper);
    }
}
