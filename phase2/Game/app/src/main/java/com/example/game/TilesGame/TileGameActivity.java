package com.example.game.TilesGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.ThemeManager;
import com.example.game.DataBase.UserRepository;
import com.example.game.DataBase.UserScores;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.SECONDS;

/** An activity class of the Tiles game. */
public class TileGameActivity extends AppCompatActivity {

  /** The type of board in this game. */
  String boardType;
  /** The user's username. */
  private String username;
  /** A user repository. */
  private UserRepository userRepository;
  /** The start time of this game. */
  private LocalTime startTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    username = getIntent().getStringExtra("USERNAME");
    userRepository = new UserRepository(this, username);
    boardType = (String) getIntent().getSerializableExtra("BoardType");
    startTime = LocalTime.now();
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    GameView.setGameActivity(this);
    setThemeColors();
    setContentView(new GameView(this)); // Instantiate new GameView.
  }

  /** Get the type of tiles board in this game. */
  String getBoardType() {
    return boardType;
  }

  /** Get user's username. */
  String getUsername() {
    return username;
  }

  /** Called when the user loses and the game ends. */
  public void endTilesGame(GameView gameView) {
    Intent intent = new Intent(this, GameFinish.class);
    int newScore =
        getNewScore(gameView); // Get the score of the game in the game board of gameView.
    // Send the score of the game to be displayed.
    LocalTime endTime = LocalTime.now();
    int timeTaken = (int) startTime.until(endTime, SECONDS);
    UserScores userScores;
    if (boardType.equals("5By5")) {
      userScores = new UserScores(username, newScore, "TILES_GAME_5", timeTaken);
      userRepository.updateUserAmount((int) Math.ceil(newScore * 0.2));
    } else if (boardType.equals("Invert")) {
      userScores = new UserScores(username, newScore, "TILES_GAME_INVERT", timeTaken);
      userRepository.updateUserAmount((int) Math.ceil(newScore * 0.3));
    } else {
      userScores = new UserScores(username, newScore, "TILES_GAME_4", timeTaken);
      userRepository.updateUserAmount((int) Math.ceil(newScore * 0.1));
    }
    userRepository.addUserScore(userScores);
    String message = ((Integer) newScore).toString();
    intent.putExtra("GAME_SCORE", message);
    intent.putExtra("USERNAME", username);
    intent.putExtra("BoardType", boardType);
    startActivity(intent);
  }

  /** Get the newest game score. */
  private Integer getNewScore(GameView gameView) {
    return gameView
        .getBoard()
        .getScore(); // Get the score of the game in the game board of gameView.
  }

  /** Set the user's theme colours into shared preferences to be retrieved and applied in game. */
  void setThemeColors() {
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.addThemeColors(this, mSettings, username);
  }
}
