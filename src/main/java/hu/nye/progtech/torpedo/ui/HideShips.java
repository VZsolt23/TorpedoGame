package hu.nye.progtech.torpedo.ui;

import java.util.Arrays;

public class HideShips {
    public void shipHider(char[][] map){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(map[i][j] == '0'){
                    map[i][j] = '~';
                }
            }
        }

        Arrays.stream(map)
                .forEach(n -> System.out.println(Arrays.toString(n).replace(",", "")));
    }
}
