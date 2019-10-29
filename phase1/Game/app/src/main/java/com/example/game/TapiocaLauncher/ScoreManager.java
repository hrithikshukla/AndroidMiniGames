package com.example.game.TapiocaLauncher;

import android.content.SharedPreferences;

class ScoreManager {
    private SharedPreferences prefs;
    private int score;

    ScoreManager(SharedPreferences prefs) {
        this.prefs = prefs;
        score = 0;
    }

    void setScore(int score) {
        this.score = score;
    }

    int getScore() {
        return score;
    }

    void addScore() {
        score++;
    }

    void saveIfHighScore() {
        if (prefs.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }
    }
}
