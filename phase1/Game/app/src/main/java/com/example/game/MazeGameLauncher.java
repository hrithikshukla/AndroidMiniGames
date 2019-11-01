package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class MazeGameLauncher extends AppCompatActivity {

  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.maze_game_launch);
    usr = (User) getIntent().getSerializableExtra("UserObject");
  }

  /** Called when the user taps the 'PLAY' button */
  public void startMazeGame(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGame.MazeGameActivity.class);
    intent.putExtra("MazeGameLauncher", usr);
    startActivity(intent);
  }
}
