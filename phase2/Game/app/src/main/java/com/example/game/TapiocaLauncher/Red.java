package com.example.game.TapiocaLauncher;

class Red extends Ball {
    Red(int x, int y, int radius, int hp) {
        super(x, y, radius, hp);
    }

    @Override
    void onHit() {
        // Makes the ball turn into a brown one
    }
}
