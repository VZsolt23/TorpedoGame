package hu.nye.progtech.torpedo.service.map;

import java.util.Random;

public class MapGenerator {

    CoordinateGenerator coordinate = new CoordinateGenerator();

    public char[][] PlaceShips() {
        int[][] mapTerv = new int[10][10];
        char[][] map = new char[10][10];
        Random vertical = new Random();
        int x, y;
        boolean isVertical, isFree;

        for (int i = 5; i > 0; i--) {
            x = coordinate.GenerateCoordinate();
            y = coordinate.GenerateCoordinate();
            isVertical = vertical.nextBoolean();

            if (isVertical) {
                if (y + i > 10) {
                    y -= i;
                }
            } else if (x + i > 10) {
                x -= i;
            }

            isFree = true;
            if (isVertical) {
                for (int v = y; v < y + i; v++) {
                    if (mapTerv[v][x] != 0) {
                        isFree = false;
                        break;
                    }
                }
            } else {
                for (int h = x; h < x + i; h++) {
                    if (mapTerv[y][h] != 0) {
                        isFree = false;
                        break;
                    }
                }
            }

            if (!isFree) {
                i++;
                continue;
            }

            if (isVertical) {
                for (int h = Math.max(0, x - 1); h < Math.min(10, x + 2); h++) {
                    for (int v = Math.max(0, y - 1); v < Math.min(10, y + i + 1); v++) {
                        mapTerv[v][h] = 9;
                    }
                }
            } else {
                for (int v = Math.max(0, y - 1); v < Math.min(10, y + 2); v++) {
                    for (int h = Math.max(0, x - 1); h < Math.min(10, x + i + 1); h++) {
                        mapTerv[v][h] = 9;
                    }
                }
            }

            for (int j = 0; j < i; j++) {
                mapTerv[y][x] = i;
                if (isVertical) {
                    y++;
                } else {
                    x++;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = mapTerv[i][j] == 0 || mapTerv[i][j] == 9 ? '~' : '0';
            }
        }

        return map;
    }

    public boolean[][] ShootablePlaces() {
        boolean[][] isShootable = new boolean[10][10];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                isShootable[i][j] = true;
            }
        }
        return isShootable;
    }
}
