package com.example.game.TapiocaLauncher;

import java.util.ArrayList;
import java.util.List;

class BoardManager {

    private TapiocaFactory tapiocaFactory;

    BoardManager() {
        this.tapiocaFactory = new TapiocaFactory();
    }

    List<Ball> getLevel(int num) {
        switch (num) {
            case 1:
                return generateLevel1();
            case 2:
                return generateRandomTriangle();
            default:
                return generateRandomLevel();
        }
    }

    private List<Ball> generateLevel1() {
        List<Ball> balls = new ArrayList<>();
        // 6 rows and 6 columns of tapioca with 1 HP
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                balls.add(this.tapiocaFactory.makeBrown(50 + (170 * i), 50 + (140 * j), 41));
            }
        }
        return balls;
    }

    private List<Ball> generateRandomLevel() {
        List<Ball> balls = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                balls.add(this.tapiocaFactory.makeRandom(50 + (170 * i), 50 + (140 * j), 41));
            }
        }
        return balls;
    }

    private List<Ball> generateRandomTriangle() {
        List<Ball> balls = new ArrayList<>();
        for (int j = 0; j < 7; j++) {
            for (int i = 6; i > j; i--) {
                balls.add(this.tapiocaFactory.makeRandom(50 * (2 * j + 1) + (170 * (6 - i)), 50 + (140 * j), 41));
            }
        }
        return balls;
    }

}
