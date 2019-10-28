package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MazeGameLauncher extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.maze_game_launch);
  }

  /** Called when the user taps the 'PLAY' button */
  public void startMazeGame(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGame.GameActivity.class);
    startActivity(intent);
  }
}
