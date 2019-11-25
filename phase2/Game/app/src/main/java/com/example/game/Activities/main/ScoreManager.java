package com.example.game.Activities.main;

import android.content.SharedPreferences;

public class ScoreManager {

    private SharedPreferences prefs;
    private int score;

    public ScoreManager(SharedPreferences prefs) {
        this.prefs = prefs;
        score = 0;
    }

    private void saveIfHighScore(String game) {
        if (prefs.getInt(game + "highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(game + "highscore", score);
            editor.apply();
        }
    }

    public void setScore(int score, String game) {
        this.score = score;
        saveIfHighScore(game);
    }

    public int getScore() {
        return score;
    }

    public void addScore(String game) {
        score++;
        saveIfHighScore(game);
    }
}
