package com.example.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class TapiocaGameLauncher extends AppCompatActivity {
  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    usr = (User) getIntent().getSerializableExtra("UserObject");
    String username = usr.getUsername();
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(TapiocaGameLauncher.this, mSettings.getInt(username + "theme", 0));

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
                        com.example.game.TapiocaLauncher.TapiocaGameActivity.class);
                intent.putExtra("UserObject", usr);
                startActivity(intent);
              }
            });

    TextView highScoretxt = findViewById(R.id.highScoreText);

    SharedPreferences prefs = getSharedPreferences("highScores", MODE_PRIVATE);
    highScoretxt.setText(
        getString(R.string.highScore) + prefs.getInt(usr.getUsername() + "tapiocahighscore", 0));
  }
  /*
   */
  /** Called when the user taps the 'PLAY' button */
  /*
  public void startTapiocaLauncher(View view) {
      Intent intent = new Intent(this, com.example.game.TapiocaLauncher.GameActivity.class);
      startActivity(intent);
  }*/

  /** Called when the user taps the 'EXIT' button */
  public void exitToMenu(View view) {
    Intent intent = new Intent(this, com.example.game.MainActivity.class);
    intent.putExtra("UserObject", usr);
    usr.getUserData().setPrefs(null);
    startActivity(intent);
  }
}
