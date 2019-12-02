package com.example.game.Activities.main.GameLauncher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.MainActivity;
import com.example.game.Activities.main.ThemeManager;
import com.example.game.MazeGame.DataStructures.Sprites;
import com.example.game.R;

public class MazeGameLauncher extends AppCompatActivity {

  String username;
  Sprites sprite = Sprites.AT; // Default sprite

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    username = getIntent().getStringExtra("USERNAME");
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(
        MazeGameLauncher.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));

    super.onCreate(savedInstanceState);
    setContentView(R.layout.maze_game_launch);
  }

  /** Called when the user taps the 'PLAY EASY' button */
  public void startMazeGameEasy(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGame.MazeGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("SPRITE", sprite);
    intent.putExtra("DIMENSIONS", new int[] {15, 15});
    intent.putExtra("DIFFICULTY", "EASY");
    intent.putExtra("SCORE", 40);
    startActivity(intent);
  }

  /** Called when the user taps the 'PLAY MEDIUM' button */
  public void startMazeGameMedium(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGame.MazeGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("SPRITE", sprite);
    intent.putExtra("DIMENSIONS", new int[] {37, 17});
    intent.putExtra("DIFFICULTY", "MEDIUM");
    intent.putExtra("SCORE", 75);
    startActivity(intent);
  }

  /** Called when the user taps the 'PLAY HARD' button */
  public void startMazeGameHard(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGame.MazeGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("SPRITE", sprite);
    intent.putExtra("DIMENSIONS", new int[] {41, 21});
    intent.putExtra("DIFFICULTY", "HARD");
    intent.putExtra("SCORE", 125);
    startActivity(intent);
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitMazeGame(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("USERNAME", username);
    startActivity(intent);
  }

  /** Sets the sprite used in the maze game. */
  public void setSprite(View view) {

    final String[] sprites = new String[] {"@", "#", "$", "%"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder
        .setTitle(R.string.pick_sprite)
        .setItems(
            sprites,
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                  case 0:
                    sprite = Sprites.AT;
                    break;
                  case 1:
                    sprite = Sprites.HASHTAG;
                    break;
                  case 2:
                    sprite = Sprites.DOLLAR;
                    break;
                  case 3:
                    sprite = Sprites.PERCENT;
                    break;
                }
              }
            });

    AlertDialog dialog = builder.create();
    dialog.show();
  }
}
