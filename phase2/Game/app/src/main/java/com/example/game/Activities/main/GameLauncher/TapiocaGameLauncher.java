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
import com.example.game.Save.User;
public class TapiocaGameLauncher extends AppCompatActivity {
  User usr;
  String username;
    UserRepository uR;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the theme.
//    usr = (User) getIntent().getSerializableExtra("UserObject");
        username = getIntent().getStringExtra("USERNAME");
        uR = new UserRepository(this, username);

        SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
        ThemeManager.setTheme(
                TapiocaGameLauncher.this,
                mSettings.getInt(username + "mode", 0),
                mSettings.getInt(username + "theme", 0));

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
                                intent.putExtra("USERNAME", username);
                                startActivity(intent);
                            }
                        });


        TextView highScoretxt = findViewById(R.id.highScoreText);

        highScoretxt.setText(getString(R.string.highScore) + uR.getUserHighScore(username, "TAPIOCA_GAME"));
    }
  /*
   */
    /* Called when the user taps the 'PLAY' button */
  /*
  public void startTapiocaLauncher(View view) {
      Intent intent = new Intent(this, com.example.game.TapiocaLauncher.GameActivity.class);
      startActivity(intent);
  }*/

  /** Called when the user taps the 'EXIT' button */
  public void exitToMenu(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("USERNAME", username);
//    usr.getUserData().setPrefs(null);
    startActivity(intent);
  }

  public void displayInstructions(View view) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this,   16974545);
    builder.setMessage(R.string.instructions_tapioca);
    builder.setTitle(R.string.instructions);
    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();

  }
}
