package com.example.game.Activities.main.GameLauncher;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.MainActivity;
import com.example.game.Activities.main.ThemeManager;
import com.example.game.DataBase.UserRepository;
import com.example.game.R;

public class TapiocaGameLauncher extends AppCompatActivity {
  // The username of the user
  String username;
  // The userRepository database
  UserRepository userRepository;

  /** @param savedInstanceState: the save state of the application. */
  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // initialize username and userRepository
    username = getIntent().getStringExtra("USERNAME");
    userRepository = new UserRepository(this, username);

    // Set the theme
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(
        TapiocaGameLauncher.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));

    // Set the view of this activity to the correct one
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tapioca_game_launch);

    // Set the play button's actions
    findViewById(R.id.play)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Intent intent =
                    new Intent(
                        TapiocaGameLauncher.this,
                        com.example.game.TapiocaLauncher.TapiocaGameActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
              }
            });

    // Display the high score of the player
    TextView highScore = findViewById(R.id.highScoreText);
    highScore.setText(
        getString(R.string.highScore) + userRepository.getUserHighScore(username, "TAPIOCA_GAME"));
  }

  /** Called when the user taps the 'EXIT' button */
  public void exitToMenu(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("USERNAME", username);
    startActivity(intent);
  }

  /** @param view: the current view. */
  public void displayInstructions(View view) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this, 16974545);
    AlertDialog dialog =
        builder
            .setMessage(R.string.instructions_tapioca)
            .setTitle(R.string.instructions)
            .setNeutralButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {}
                })
            .create();
    dialog.show();
  }
}
