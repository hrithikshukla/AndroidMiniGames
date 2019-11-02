package com.example.game.TilesGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.Save.User;
import com.example.game.ThemeManager;

public class GameFinish extends AppCompatActivity {
  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    usr = (User) getIntent().getSerializableExtra("UserObject");
    String username = usr.getUsername();
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(GameFinish.this, mSettings.getInt(username + "theme", 0));

    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.tiles_game_end);

    // Get the Intent that started this activity and extract the score.
    Intent intent = getIntent();
    String gameScore = intent.getStringExtra("GAME_SCORE");

    // Capture the layout's TextView that displays the game score and set the score as its text.
    TextView textViewScore = findViewById(R.id.tilesGameScore);
    textViewScore.setText(gameScore);

    // Capture the layout's TextView that displays the high score and set the high score from
    // saved in SharedPreferences as its text.
    TextView textViewHighScore = findViewById(R.id.highScoreText);
    SharedPreferences prefs = getSharedPreferences("highScores", MODE_PRIVATE);
    String highScoreText =
        getString(R.string.highScore) + prefs.getInt(usr.getUsername() + "tileshighscore", 0);
    textViewHighScore.setText(highScoreText);
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitToMenu(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGameLauncher.class);
    usr.getUserData().setPrefs(null);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }

  /** Called when the user taps the 'TRY AGAIN' button */
  public void restartTilesGame(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGame.TileGameActivity.class);
    usr.getUserData().setPrefs(null);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }
}
