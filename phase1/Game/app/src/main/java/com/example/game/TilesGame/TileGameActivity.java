package com.example.game.TilesGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class TileGameActivity extends AppCompatActivity {
  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    usr = (User) getIntent().getSerializableExtra("UserObject");
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    GameView.setGameActivity(this);
    setContentView(new GameView(this)); // Instantiates new GameView.
  }

  /** Called when the user loses and the game ends. */
  public void endTilesGame(GameView gameView) {
    Intent intent = new Intent(this, GameFinish.class);
    Integer newScore =
        getNewScore(gameView); // Get the score of the game in the game board of gameView.
    // Send the score of the game to be displayed.
    updateHighScore(newScore);
    String message = newScore.toString();
    intent.putExtra("GAME_SCORE", message);
    usr.getUserData().setPrefs(null);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }

  /** Get the newest game score. */
  private Integer getNewScore(GameView gameView) {
    Integer newScore =
        gameView
            .getBoardManager()
            .getScore(); // Get the score of the game in the game board of gameView.
    return newScore;
  }

  /** Update the high score for Tiles game for usr. */
  public void updateHighScore(int newScore) {
    SharedPreferences sharedPreferences = getSharedPreferences("highScores", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    int currentHighScore = sharedPreferences.getInt("tileshighscore", 0);
    if (currentHighScore < newScore) {
      // Add the high score to SharedPreferences
      editor.putInt("tileshighscore", newScore);
      // Apply the save
      editor.apply();
      usr.getUserData().updateScores();
    }
  }
}
