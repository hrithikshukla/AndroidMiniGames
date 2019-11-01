package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.Save.Stats;
import com.example.game.Save.User;

public class StatsActivity extends AppCompatActivity {
    User usr;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        this.usr = (User) getIntent().getSerializableExtra("UserObject");

        ImageView arrow = findViewById(R.id.ArrowLeft);
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

    }
}
