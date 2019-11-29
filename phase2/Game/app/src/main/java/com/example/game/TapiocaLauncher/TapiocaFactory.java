package com.example.game.TapiocaLauncher;

import java.util.Random;

class TapiocaFactory {
    private Random rand;

    TapiocaFactory() {
        rand = new Random();
    }

    Ball makeBrown(int x, int y, int radius) {
        return new Ball(x, y, radius, 1, "reg");
    }

    Ball makeRed(int x, int y, int radius) {
        return new Ball(x, y, radius, 2, "reg");
    }

    Ball makeWhite(int x, int y, int radius) {
        return new Ball(x, y, radius, 1, "speedboost");
    }

    Ball makeRandom(int x, int y, int radius) {
        int num = rand.nextInt(36);
        switch (num) {
            case 0:
            case 1:
            case 2:
                return makeWhite(x, y, radius);
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return makeRed(x, y, radius);
            default:
                return makeBrown(x, y, radius);
        }
    }
}
