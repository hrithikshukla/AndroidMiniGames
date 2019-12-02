package com.example.game.Activities.Game;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

/** Base class for maze and tapioca game activity */
public abstract class GameActivity extends AppCompatActivity {

  private String username;
  private int score;

  /**
   * @param context indicates if it's maze or tapioca put username and game score to game over
   *     activity in an intent
   */
  protected void switchToGameOverActivity(Context context) {
    Intent intent = new Intent(context, GameOverActivity.class);
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