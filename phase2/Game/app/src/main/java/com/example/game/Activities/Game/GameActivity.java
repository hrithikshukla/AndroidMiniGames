package com.example.game.Activities.Game;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public abstract class GameActivity extends AppCompatActivity {

  private String username;
  private int score;

  protected void switchToGameOverActivity(Context context) {
    Intent intent = new Intent(context, GameOverActivity.class);
//    usr.getUserData().setPrefs(null);
    intent.putExtra("USERNAME", username);
    intent.putExtra("GAME_SCORE", score);
    startActivity(intent);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

}
