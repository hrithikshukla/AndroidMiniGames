package com.example.game.Activities.main.GameLauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.MainActivity;
import com.example.game.Activities.main.ThemeManager;
import com.example.game.R;

public class TilesGameLauncher extends AppCompatActivity {
  String username;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    username = getIntent().getStringExtra("USERNAME");
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(
        TilesGameLauncher.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));

    super.onCreate(savedInstanceState);
    setContentView(R.layout.tiles_game_launch);

    // Capture the layout's TextView that displays the instructions and set the text according to
    // the user's light/dark mode.
    TextView textViewInstructions = findViewById(R.id.instructionsTiles);
    if (mSettings.getInt(username + "mode", 0) == ThemeManager.getDARK()) {
      textViewInstructions.setText(R.string.instructions_tiles_dark);
    }
  }

  /** Called when the user taps the '4 X 4' button */
  public void startTiles4By4(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGame.TileGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("BoardType", "4By4");
    startActivity(intent);
  }

  /** Called when the user taps the '5 X 5' button */
  public void startTiles5By5(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGame.TileGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("BoardType", "5By5");
    startActivity(intent);
  }

  /** Called when the user taps the '5 X 5' button */
  public void startTilesInvert(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGame.TileGameActivity.class);
    intent.putExtra("USERNAME", username);
    intent.putExtra("BoardType", "Invert");
    startActivity(intent);
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitTilesGame(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("USERNAME", username);
    startActivity(intent);
  }
}
