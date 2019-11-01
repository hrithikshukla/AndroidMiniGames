package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.Save.User;

public class TapiocaGameLauncher extends AppCompatActivity {

    User usr;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.tapioca_game_launch);

    usr = (User) getIntent().getSerializableExtra("MainActivity");

        findViewById(R.id.play)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Intent intent =
                    new Intent(
                        TapiocaGameLauncher.this,
                        com.example.game.TapiocaLauncher.GameActivity.class);
                  intent.putExtra("TapiiocaGameLauncher", usr);
                  startActivity(intent);
              }
            });

    TextView highScoretxt = findViewById(R.id.highScoreText);

    SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
    highScoretxt.setText(getString(R.string.highScore) + prefs.getInt("highscore", 0));
  }
  /*
   */
  /** Called when the user taps the 'PLAY' button */
  /*
  public void startTapiocaLauncher(View view) {
      Intent intent = new Intent(this, com.example.game.TapiocaLauncher.GameActivity.class);
      startActivity(intent);
  }*/
}
