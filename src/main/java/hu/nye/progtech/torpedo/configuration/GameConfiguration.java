package hu.nye.progtech.torpedo.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.command.InputHandler;
import hu.nye.progtech.torpedo.service.command.performer.AttackPerformer;
import hu.nye.progtech.torpedo.service.ingame.Controller;
import hu.nye.progtech.torpedo.service.ingame.performer.GameStepPerformer;
import hu.nye.progtech.torpedo.service.input.UserInputReader;
import hu.nye.progtech.torpedo.service.map.MapGenerator;
import hu.nye.progtech.torpedo.service.utility.MapUtility;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {
    @Bean(initMethod = "start")
    Controller gameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtility mapUtil) {
        return new Controller(gameState, gameStepPerformer, mapUtil);
    }

    @Bean
    GameState gameState() {
        MapGenerator mapGenerator = new MapGenerator();

        char[][] map = mapGenerator.PlaceShips();
        char[][] map2 = mapGenerator.PlaceShips();
        boolean[][] isShootable = mapGenerator.ShootablePlaces();
        boolean[][] isShootable2 = mapGenerator.ShootablePlaces();
        MapVO playerMap = new MapVO(10, 10, map, isShootable);
        MapVO aiMap = new MapVO(10, 10, map2, isShootable2);
        return new GameState(playerMap, aiMap, false);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    AttackPerformer attackPerformer() {
        return new AttackPerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }
}
