package hu.nye.progtech.torpedo;

import hu.nye.progtech.torpedo.service.ingame.InGameProcess;
import hu.nye.progtech.torpedo.service.ingame.InputHandler;
import hu.nye.progtech.torpedo.ui.MenuUI;

public class Main {

    public static void main(String[] args) {

        InGameProcess ingameProcess = new InGameProcess();
        InputHandler inputHandler = new InputHandler();
        MenuUI menuUI = new MenuUI();
        String choice = "";
        int i = 0;

        while (i == 0) {
            menuUI.MenuOptions();
            choice = inputHandler.InputReader();
            switch (choice.toLowerCase()) {
                case "play":
                    ingameProcess.InGame();
                    break;
                case "difficulty":
                    //menuUI.Difficulty();
                    break;
                case "quit":
                    i = 1;
                    break;
                default:
                    System.out.println("Wrong command! Try again!");
            }
        }
    }
}
