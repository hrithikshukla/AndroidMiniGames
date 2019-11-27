package com.example.game.Activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
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
        arrow.setOnTouchListener( // Swipe back to main screen
                new OnSwipeTouchListener(ShopActivity.this) {
                    @Override
                    public void onSwipeLeft() {
                        // your actions
                        Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        // Find and add images to images list and set ID tags
        ImageView worm = findViewById(R.id.char_worm);
        worm.setTag(R.id.id, R.drawable.char_worm);
        images.add(worm);
        ImageView bird = findViewById(R.id.char_bird);
        bird.setTag(R.id.id, R.drawable.char_bird_blue);
        images.add(bird);
        ImageView spider = findViewById(R.id.char_spider);
        spider.setTag(R.id.id, R.drawable.char_spider);
        images.add(spider);
        ImageView dog = findViewById(R.id.char_dog);
        dog.setTag(R.id.id, R.drawable.char_dog);
        images.add(dog);
        ImageView tree = findViewById(R.id.char_tree);
        tree.setTag(R.id.id, R.drawable.char_tree);
        images.add(tree);
        ImageView astro = findViewById(R.id.char_astro);
        astro.setTag(R.id.id, R.drawable.char_astro);
        images.add(astro);
        ImageView alien = findViewById(R.id.char_alien);
        alien.setTag(R.id.id, R.drawable.char_alien_dark);
        images.add(alien);
        ImageView monster = findViewById(R.id.char_monster);
        monster.setTag(R.id.id, R.drawable.char_monster_red);
        images.add(monster);
        ImageView bat = findViewById(R.id.char_bat);
        bat.setTag(R.id.id, R.drawable.char_bat);
        images.add(bat);
        ImageView king = findViewById(R.id.char_king);
        king.setTag(R.id.id, R.drawable.char_king);
        images.add(king);
        ImageView summoner = findViewById(R.id.char_summoner);
        summoner.setTag(R.id.id, R.drawable.char_summoner);
        images.add(summoner);
        ImageView viking1 = findViewById(R.id.char_viking1);
        viking1.setTag(R.id.id, R.drawable.char_viking_1);
        images.add(viking1);
        ImageView viking2 = findViewById(R.id.char_viking2);
        viking2.setTag(R.id.id, R.drawable.char_viking_2);
        images.add(viking2);
        ImageView viking3 = findViewById(R.id.char_viking3);
        viking3.setTag(R.id.id, R.drawable.char_viking_3);
        images.add(viking3);
        ImageView wizard = findViewById(R.id.char_wizard);
        viking3.setTag(R.id.id, R.drawable.char_wizard);
        images.add(wizard);
        ImageView archer = findViewById(R.id.char_archer);
        archer.setTag(R.id.id, R.drawable.char_archer);
        images.add(archer);
        ImageView knight = findViewById(R.id.char_knight);
        knight.setTag(R.id.id, R.drawable.char_knight);
        images.add(knight);
        ImageView samurai = findViewById(R.id.char_samurai);
        samurai.setTag(R.id.id, R.drawable.char_samurai);
        images.add(samurai);
        ImageView shogun = findViewById(R.id.char_shogun);
        shogun.setTag(R.id.id, R.drawable.char_shogun);
        images.add(shogun);

        // Grey out the characters that are not owned and add click viewers for characters
        for (final ImageView img : images) {
            grayOut(img);
            img.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Set fragment to clickable
                            FrameLayout frameLayout = findViewById(R.id.frame);
                            frameLayout.setClickable(true);
                            // Open a fragment
                            int image = (int) img.getTag(R.id.id);
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.frame, new SpriteFragment(image))
                                    .addToBackStack(null).commit();
                        }
                    });
        }

    }

    // Code from https://gist.github.com/nisrulz/3078eaa6357d6f5c0051
    private void grayOut(ImageView img) {
        // if grayed
        if (img.getTag().equals("grayed")) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            img.setColorFilter(filter);
        } else {
            img.setColorFilter(null);
            img.setTag("");
        }
    }
}
