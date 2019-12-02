package com.example.game.Activities.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

public class StatsActivity extends AppCompatActivity {
  String username;
  UserRepository uR;

  @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    // Set the theme.
    username = getIntent().getStringExtra("USERNAME");
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    uR = new UserRepository(this, username);

    ThemeManager.setTheme(
        StatsActivity.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stats);

    // Swipe to go back to MainActivity
    ImageView arrow = findViewById(R.id.ArrowLeft);
    arrow.setOnTouchListener(
        new OnSwipeTouchListener(StatsActivity.this) {
          @Override
          public void onSwipeRight() {
            // your actions
            Intent intent = new Intent(StatsActivity.this, MainActivity.class);
            //            usr.getUserData().setPrefs(null);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
          }
        });

    // Button to reset statistics
    Button reset = findViewById(R.id.Reset);
    reset.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            uR.resetUserStatistics(username);
            Intent intent = new Intent(getIntent());
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
          }
        });

    setHighScores();
    setBestTime();
    setAverageTime();
  }

  /** Sets the high scores per game for statistics. */
  private void setHighScores() {

    TextView tapiocaScore = findViewById(R.id.TapiocaScore);
    TextView mazeEasyScore = findViewById(R.id.MazeEasyScore);
    TextView mazeMediumScore = findViewById(R.id.MazeMediumScore);
    TextView mazeHardScore = findViewById(R.id.MazeHardScore);
    TextView tiles4Score = findViewById(R.id.Tiles4Score);
    TextView tiles5Score = findViewById(R.id.Tiles5Score);
    TextView tilesInvertScore = findViewById(R.id.TilesInvertScore);

    // Retrieve high scores
    int tapiocaHighScore = uR.getUserHighScore(username, "TAPIOCA_GAME");
    int mazeEasyHighScore = uR.getUserHighScore(username, "MAZE_GAME_EASY");
    int mazeMediumHighScore = uR.getUserHighScore(username, "MAZE_GAME_MEDIUM");
    int mazeHardHighScore = uR.getUserHighScore(username, "MAZE_GAME_HARD");
    int tiles4HighScore = uR.getUserHighScore(username, "TILES_GAME_4");
    int tiles5HighScore = uR.getUserHighScore(username, "TILES_GAME_5");
    int tilesInvertHighScore = uR.getUserHighScore(username, "TILES_GAME_INVERT");

    // Set textViews to high scores
    tapiocaScore.setText(getString(R.string.TapiocaScore) + tapiocaHighScore);
    mazeEasyScore.setText(getString(R.string.MazeEasyScore) + mazeEasyHighScore);
    mazeMediumScore.setText(getString(R.string.MazeMediumScore) + mazeMediumHighScore);
    mazeHardScore.setText(getString(R.string.MazeHardScore) + mazeHardHighScore);
    tiles4Score.setText(getString(R.string.Tiles4Score) + tiles4HighScore);
    tiles5Score.setText(getString(R.string.Tiles5Score) + tiles5HighScore);
    tilesInvertScore.setText(getString(R.string.TilesInvertScore) + tilesInvertHighScore);
  }

  /** Sets the best time per game for statistics. */
  public void setBestTime() {

    TextView tapiocaTime = findViewById(R.id.TapiocaTime);
    TextView mazeEasyTime = findViewById(R.id.MazeEasyTime);
    TextView mazeMediumTime = findViewById(R.id.MazeMediumTime);
    TextView mazeHardTime = findViewById(R.id.MazeHardTime);
    TextView tiles4Time = findViewById(R.id.Tiles4Time);
    TextView tile5Time = findViewById(R.id.Tiles5Time);
    TextView tilesInverTime = findViewById(R.id.TilesInvertTime);

    // Retrieve best times. Best time for maze and tapioca launcher will be the minimum time to
    // finish them, while best time for the tiles game will be the maximum time playing it.
    int tapiocaMinTime = uR.getUserMinTime("TAPIOCA_GAME");
    int minTimeMazeEasy = uR.getUserMinTime("MAZE_GAME_EASY");
    int minTimeMazeMedium = uR.getUserMinTime("MAZE_GAME_MEDIUM");
    int minTimeMazeHard = uR.getUserMinTime("MAZE_GAME_HARD");
    int maxTimeTiles4 = uR.getUserMaxTime("TILES_GAME_4");
    int maxTimeTiles5 = uR.getUserMaxTime("TILES_GAME_5");
    int maxTimeTilesInvert = uR.getUserMaxTime("TILES_GAME_INVERT");

    // Set textViews to best time
    tapiocaTime.setText(getString(R.string.TapiocaBestTime) + tapiocaMinTime);
    mazeEasyTime.setText(getString(R.string.MazeEasyTime) + minTimeMazeEasy);
    mazeMediumTime.setText(getString(R.string.MazeMediumTime) + minTimeMazeMedium);
    mazeHardTime.setText(getString(R.string.MazeHardTime) + minTimeMazeHard);
    tiles4Time.setText(getString(R.string.Tiles4Time) + maxTimeTiles4);
    tile5Time.setText(getString(R.string.Tiles5Time) + maxTimeTiles5);
    tilesInverTime.setText(getString(R.string.TilesInvertTime) + maxTimeTilesInvert);
  }

  /** Sets the average time per game for statistics. */
  private void setAverageTime() {

    TextView tapiocaAvgTime = findViewById(R.id.TapiocaAvgTime);
    TextView mazeHardAvgTime = findViewById(R.id.MazeHardAvgTime);
    TextView tilesInvertAvgTime = findViewById(R.id.TilesInvertAvgTime);

    // Retrieve average times
    int avgTimeTapioca = uR.getUserAvgTime("TAPIOCA_GAME");
    int avgTimeMazeHard = uR.getUserAvgTime("MAZE_GAME_HARD");
    int avgTimeTilesInvert = uR.getUserAvgTime("TILES_GAME_INVERT");

    // Set textViews to average time
    tapiocaAvgTime.setText(getString(R.string.TapiocaAvgTime) + avgTimeTapioca);
    mazeHardAvgTime.setText(getString(R.string.MazeHardAvgTime) + avgTimeMazeHard);
    tilesInvertAvgTime.setText(getString(R.string.TilesInvertAvgTime) + avgTimeTilesInvert);
  }
}
