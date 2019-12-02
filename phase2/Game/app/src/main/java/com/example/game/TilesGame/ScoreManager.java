package com.example.game.TilesGame;

import android.content.SharedPreferences;

public class ScoreManager {

  private SharedPreferences prefs;
  private int score;

  ScoreManager(SharedPreferences prefs) {
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

  void setScore(int score, String game) {
    this.score = score;
    saveIfHighScore(game);
  }

  public int getScore() {
    return score;
  }

  void addScore(String game) {
    score++;
    saveIfHighScore(game);
  }
}
