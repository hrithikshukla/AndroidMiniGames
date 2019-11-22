package com.example.game.TapiocaLauncher;

class TapiocaFactory {

    TapiocaFactory() {
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
}
