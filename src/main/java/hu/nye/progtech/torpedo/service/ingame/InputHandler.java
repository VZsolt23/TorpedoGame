package hu.nye.progtech.torpedo.service.ingame;

import java.util.Scanner;

public class InputHandler {
    public String InputReader() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        return choice;
    }
}
