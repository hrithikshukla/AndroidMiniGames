package com.example.game.Activities.Game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.MainActivity;
import com.example.game.Activities.main.ThemeManager;
import com.example.game.R;
import com.example.game.Save.User;

public class GameOverActivity extends AppCompatActivity {
    User usr;
    Button goToHomePage;
    String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Set the theme.
//    usr = (User) getIntent().getSerializableExtra("UserObject");
        username = getIntent().getStringExtra("USERNAME");
        SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
        ThemeManager.setTheme(
                GameOverActivity.this,
                mSettings.getInt(username + "mode", 0),
                mSettings.getInt(username + "theme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_activity);

        goToHomePage = (Button) findViewById(R.id.homepagebutton);

        // Get the Intent that started this activity and extract the score.
        Intent intent = getIntent();
        String gameScore = Integer.toString(intent.getIntExtra("GAME_SCORE", 0));
        String time = Integer.toString(intent.getIntExtra("TIME", 0));

        // Capture the layout's TextView that displays the game score and set the score as its text.
        TextView textViewScore = findViewById(R.id.gameScore);
        textViewScore.setText(gameScore);

    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }
}
