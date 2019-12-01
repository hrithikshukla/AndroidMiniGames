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
import com.example.game.R;
import com.example.game.Save.User;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TileGameActivity extends AppCompatActivity {

  private String username;
  private UserRepository ur;
  private LocalTime startime;
  String boardType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    username = getIntent().getStringExtra("USERNAME");
    ur = new UserRepository(this, username);
    boardType = (String) getIntent().getSerializableExtra("BoardType");
    startime = LocalTime.now();
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    GameView.setGameActivity(this);
    setThemeColors();
    setContentView(new GameView(this)); // Instantiates new GameView.
  }

  String getBoardType() {
    return boardType;
  }

  String getUsername() {
    return username;
  }

  /** Called when the user loses and the game ends. */
  public void endTilesGame(GameView gameView) {
    Intent intent = new Intent(this, GameFinish.class);
    Integer newScore =
        getNewScore(gameView); // Get the score of the game in the game board of gameView.
    // Send the score of the game to be displayed.
    LocalTime endtime = LocalTime.now();
    int timetaken = (int) startime.until(endtime, SECONDS);
    UserScores u;
    if (boardType.equals("5By5")) {
      u = new UserScores(username, newScore, "TILES_GAME_5", timetaken);
    } else if (boardType.equals("Invert")) {
      u = new UserScores(username, newScore, "TILES_GAME_INVERT", timetaken);
    } else {
      u = new UserScores(username, newScore, "TILES_GAME_4", timetaken);
    }
    ur.addUserScore(u);
    //    updateHighScore(newScore);
    String message = newScore.toString();
    intent.putExtra("GAME_SCORE", message);
    //    usr.getUserData().setPrefs(null);
    intent.putExtra("USERNAME", username);
    intent.putExtra("BoardType", boardType);
    startActivity(intent);
  }

  /** Get the newest game score. */
  private Integer getNewScore(GameView gameView) {
    Integer newScore =
        gameView.getBoard().getScore(); // Get the score of the game in the game board of gameView.
    return newScore;
  }

  void setThemeColors() {
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.addThemeColors(this, mSettings, username);
  }

  /** Update the high score for Tiles game for usr. */
  //  public void updateHighScore(int newScore) {
  //    SharedPreferences sharedPreferences = getSharedPreferences("highScores", MODE_PRIVATE);
  //    SharedPreferences.Editor editor = sharedPreferences.edit();
  //    int currentHighScore = sharedPreferences.getInt(usr.getUsername() + "tileshighscore", 0);
  //    if (currentHighScore < newScore) {
  //      // Add the high score to SharedPreferences
  //      editor.putInt(usr.getUsername() + "tileshighscore", newScore);
  //      // Apply the save
  //      editor.apply();
  //    }
  //  }
}
