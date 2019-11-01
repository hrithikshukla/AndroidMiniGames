package com.example.game.TilesGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class TileGameActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
    Integer gameScore =
        gameView
            .getBoardManager()
            .getScore(); // Get the score of the game in the game board of gameView.
    // Send the score of the game to be displayed.
    String message = gameScore.toString();
    intent.putExtra("GAME_SCORE", message);
    startActivity(intent);
  }
}
