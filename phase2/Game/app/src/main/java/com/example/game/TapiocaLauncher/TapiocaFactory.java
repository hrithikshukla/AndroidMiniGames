package com.example.game.TapiocaLauncher;

import java.util.Random;

class TapiocaFactory {
    private Random rand;

    TapiocaFactory() {
        rand = new Random();
    }

    Brown makeBrown(int x, int y, int radius) {
        return new Brown(x, y, radius, 1);
    }

    Red makeRed(int x, int y, int radius) {
        return new Red(x, y, radius, 2);
    }

    White makeWhite(int x, int y, int radius) {
        return new White(x, y, radius, 1);
    }

    Ball makeRandom(int x, int y, int radius) {
        int num = rand.nextInt(36);
        switch (num) {
            case 0:
            case 1:
            case 2:
            case 3:
                return new White(x, y, radius, 1);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return new Red(x, y, radius, 2);
            default:
                return new Brown(x, y, radius, 1);
        }
    }
}
