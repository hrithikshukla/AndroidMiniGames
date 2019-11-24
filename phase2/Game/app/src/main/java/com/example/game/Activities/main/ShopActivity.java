package com.example.game.Activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.game.R;

import java.util.ArrayList;
import java.util.List;

// https://opengameart.org/content/2d-complete-characters credit for sprites

public class ShopActivity extends AppCompatActivity {

    // List of images to display
    private List<ImageView> images = new ArrayList<>();

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

        // Find and add images to images list
        ImageView worm = findViewById(R.id.char_worm);
        images.add(worm);
        ImageView bird = findViewById(R.id.char_bird);
        images.add(bird);
        ImageView spider = findViewById(R.id.char_spider);
        images.add(spider);
        ImageView dog = findViewById(R.id.char_dog);
        images.add(dog);
        ImageView tree = findViewById(R.id.char_tree);
        images.add(tree);
        ImageView astro = findViewById(R.id.char_astro);
        images.add(astro);
        ImageView alien = findViewById(R.id.char_alien);
        images.add(alien);
        ImageView monster = findViewById(R.id.char_monster);
        images.add(monster);
        ImageView bat = findViewById(R.id.char_bat);
        images.add(bat);
        ImageView king = findViewById(R.id.char_king);
        images.add(king);
        ImageView summoner = findViewById(R.id.char_summoner);
        images.add(summoner);
        ImageView viking1 = findViewById(R.id.char_viking1);
        images.add(viking1);
        ImageView viking2 = findViewById(R.id.char_viking2);
        images.add(viking2);
        ImageView viking3 = findViewById(R.id.char_viking3);
        images.add(viking3);
        ImageView wizard = findViewById(R.id.char_wizard);
        images.add(wizard);
        ImageView archer = findViewById(R.id.char_archer);
        images.add(archer);
        ImageView knight = findViewById(R.id.char_knight);
        images.add(knight);
        ImageView samurai = findViewById(R.id.char_samurai);
        images.add(samurai);
        ImageView shogun = findViewById(R.id.char_shogun);
        images.add(shogun);

        for (ImageView img : images) {
            grayOut(img);
        }

    }

    // Code from https://stackoverflow.com/a/25454597/10322608
    public void grayOut(ImageView img) {
        // if not grayed
        if (img.getTag() == "grayed") {
            img.setColorFilter(Color.argb(150, 200, 200, 200));
            img.setTag("grayed");
        } else {
            img.setColorFilter(null);
            img.setTag("");
        }
    }

}
