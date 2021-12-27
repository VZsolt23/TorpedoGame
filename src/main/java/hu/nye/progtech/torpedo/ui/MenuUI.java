package hu.nye.progtech.torpedo.ui;

/**
 * Class used to print usable command list.
 */
public class MenuUI {
    /**
     * Command list.
     */
    public void menuOptions() {
        System.out.println("-----Torpedo game-----\n" +
                "print - Display maps\n" +
                "fire - Attack at position (fire 2 3)\n" +
                "save - Save game\n" +
                "load - Load game\n" +
                "exit - Quit game");
    }
}
