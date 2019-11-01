package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TapiocaGameLauncher extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(TapiocaGameLauncher.this, mSettings.getInt("theme", -1));

    super.onCreate(savedInstanceState);

    setContentView(R.layout.tapioca_game_launch);

    findViewById(R.id.play)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Intent intent =
                    new Intent(
                        TapiocaGameLauncher.this,
                        com.example.game.TapiocaLauncher.GameActivity.class);
                startActivity(intent);
              }
            });

    TextView highScoretxt = findViewById(R.id.highScoreText);

    SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
    highScoretxt.setText(getString(R.string.highScore) + prefs.getInt("highscore", 0));
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitTapiocaGame(View view) {
    Intent intent = new Intent(this, com.example.game.MainActivity.class);
    startActivity(intent);
  }
}
