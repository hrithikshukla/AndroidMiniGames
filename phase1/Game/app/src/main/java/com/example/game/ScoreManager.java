package com.example.game;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager{

    private SharedPreferences prefs;
    private int score;

    public ScoreManager(SharedPreferences prefs) {
        this.prefs = prefs;
        score = 0;
    }

    private void saveIfHighScore() {
        if (prefs.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }
    }

    public void setScore(int score) {
        this.score = score;
        saveIfHighScore();
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
        saveIfHighScore();
    }
}
