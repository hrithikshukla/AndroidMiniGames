package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.Save.Stats;
import com.example.game.Save.User;

public class StatsActivity extends AppCompatActivity {
    User usr;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        this.usr = (User) getIntent().getSerializableExtra("UserObject");
        if (usr != null) {
            usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
        }

        ImageView arrow = findViewById(R.id.ArrowLeft);
        // Code based on https://stackoverflow.com/a/24256106/10322608
        arrow.setOnTouchListener(new OnSwipeTouchListener(StatsActivity.this) {
            @Override
            public void onSwipeRight() {
                //your actions
                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                usr.getUserData().setPrefs(null);
                intent.putExtra("UserObject", usr);
                startActivity(intent);
            }
        });

        Button reset = findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr.getUserData().resetScores();
                Intent intent = new Intent(getIntent());
                usr.getUserData().setPrefs(null);
                intent.putExtra("UserObject", usr);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);

            }
        }
        );

        TextView tapiocaScore = findViewById(R.id.TapiocaScore);
        TextView mazeScore = findViewById(R.id.MazeScore);
        TextView tileScore = findViewById(R.id.TileScore);
        usr.getUserData().updateScores();
        tapiocaScore.setText(getString(R.string.TapiocaScore) + usr.getUserData().getTapiocaHighScore());
        mazeScore.setText(getString(R.string.MazeScore) +  usr.getUserData().getMazeHighScore());
        tileScore.setText(getString(R.string.TileScore) + usr.getUserData().getTilesHighScore());

        TextView tapiocaTime = findViewById(R.id.TapiocaTime);
        TextView mazeTime = findViewById(R.id.MazeTime);
        TextView tileTIme = findViewById(R.id.TileTime);
        tapiocaTime.setText(getString(R.string.TapiocaTime) + getString(R.string.NotAvailable));
        mazeTime.setText(getString(R.string.MazeTime) + getString(R.string.NotAvailable));
        tileTIme.setText(getString(R.string.TileTime) + getString(R.string.NotAvailable));
    }
}
