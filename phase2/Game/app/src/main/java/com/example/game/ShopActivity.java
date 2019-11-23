package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

// https://opengameart.org/content/2d-complete-characters credit for sprites

public class ShopActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ImageView arrow = findViewById(R.id.toMain);

        // Code based on https://stackoverflow.com/a/24256106/10322608
        arrow.setOnTouchListener(
                new OnSwipeTouchListener(ShopActivity.this) {
                    @Override
                    public void onSwipeLeft() {
                        // your actions
                        Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
