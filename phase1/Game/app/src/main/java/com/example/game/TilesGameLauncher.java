package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class TilesGameLauncher extends AppCompatActivity {

  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    usr = (User) getIntent().getSerializableExtra("MainActivity");
    setContentView(R.layout.tiles_game_launch);
  }

  /** Called when the user taps the 'PLAY' button */
  public void startTilesGame(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGame.GameActivity.class);
    intent.putExtra("TilesGameLauncher", usr);
    startActivity(intent);
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitTilesGame(View view) {
    Intent intent = new Intent(this, com.example.game.MainActivity.class);
    startActivity(intent);
  }
}
