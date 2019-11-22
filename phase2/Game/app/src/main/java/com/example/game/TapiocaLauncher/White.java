package com.example.game.TapiocaLauncher;

class White extends Ball {
    White(int x, int y, int radius, int hp) {
        super(x, y, radius, hp);
    }

    @Override
    void onHit() {
        // Causes speed to reset thus increasing
    }
}
