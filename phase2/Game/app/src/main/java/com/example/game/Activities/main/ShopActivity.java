package com.example.game.Activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

import java.util.ArrayList;
import java.util.List;

// https://opengameart.org/content/2d-complete-characters credit for sprites

public class ShopActivity extends AppCompatActivity {

  // List of images to display
  private List<ImageView> images = new ArrayList<>();
  // Checks if back button is pressed
  protected OnBackPressedListener onBackPressedListener;
  private LiveData<List<Integer>> ownedChars;
  private String username;
  TextView userCoins;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    username = getIntent().getStringExtra("USERNAME");
    UserRepository uR = new UserRepository(this, username);

    // Set the theme.
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(
        ShopActivity.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));

    // Assign list to ownedChars here
    ownedChars = uR.getUserCollectibles();
    ownedChars.observe(this, new Observer<List<Integer>>() {
      @Override
      public void onChanged(List<Integer> integers) {
        // Put method what happens when what use own changes
      }
    });

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
            intent.putExtra("USERNAME", username);
            startActivity(intent);
          }
        });

    // Find and add images to images list and set ID tags and set character prices
    ImageView worm = findViewById(R.id.char_worm);
    worm.setTag(R.id.id, R.drawable.char_worm);
    worm.setTag(R.id.price, 21);
    images.add(worm);
    ImageView bird = findViewById(R.id.char_bird);
    bird.setTag(R.id.id, R.drawable.char_bird_blue);
    bird.setTag(R.id.price, 42);
    images.add(bird);
    ImageView spider = findViewById(R.id.char_spider);
    spider.setTag(R.id.id, R.drawable.char_spider);
    spider.setTag(R.id.price, 50);
    images.add(spider);
    ImageView dog = findViewById(R.id.char_dog);
    dog.setTag(R.id.id, R.drawable.char_dog);
    dog.setTag(R.id.price, 69);
    images.add(dog);
    ImageView tree = findViewById(R.id.char_tree);
    tree.setTag(R.id.id, R.drawable.char_tree);
    tree.setTag(R.id.price, 80);
    images.add(tree);
    ImageView astro = findViewById(R.id.char_astro);
    astro.setTag(R.id.id, R.drawable.char_astro);
    astro.setTag(R.id.price, 80);
    images.add(astro);
    ImageView alien = findViewById(R.id.char_alien);
    alien.setTag(R.id.id, R.drawable.char_alien_dark);
    alien.setTag(R.id.price, 90);
    images.add(alien);
    ImageView monster = findViewById(R.id.char_monster);
    monster.setTag(R.id.id, R.drawable.char_monster_red);
    monster.setTag(R.id.price, 100);
    images.add(monster);
    ImageView bat = findViewById(R.id.char_bat);
    bat.setTag(R.id.id, R.drawable.char_bat);
    bat.setTag(R.id.price, 150);
    images.add(bat);
    ImageView king = findViewById(R.id.char_king);
    king.setTag(R.id.id, R.drawable.char_king);
    king.setTag(R.id.price, 151);
    images.add(king);
    ImageView summoner = findViewById(R.id.char_summoner);
    summoner.setTag(R.id.id, R.drawable.char_summoner);
    summoner.setTag(R.id.price, 1000);
    images.add(summoner);
    ImageView viking1 = findViewById(R.id.char_viking1);
    viking1.setTag(R.id.id, R.drawable.char_viking_1);
    viking1.setTag(R.id.price, 200);
    images.add(viking1);
    ImageView viking2 = findViewById(R.id.char_viking2);
    viking2.setTag(R.id.id, R.drawable.char_viking_2);
    viking2.setTag(R.id.price, 200);
    images.add(viking2);
    ImageView viking3 = findViewById(R.id.char_viking3);
    viking3.setTag(R.id.id, R.drawable.char_viking_3);
    viking3.setTag(R.id.price, 200);
    images.add(viking3);
    ImageView wizard = findViewById(R.id.char_wizard);
    wizard.setTag(R.id.price, 300);
    wizard.setTag(R.id.id, R.drawable.char_wizard);
    images.add(wizard);
    ImageView archer = findViewById(R.id.char_archer);
    archer.setTag(R.id.id, R.drawable.char_archer);
    archer.setTag(R.id.price, 300);
    images.add(archer);
    ImageView knight = findViewById(R.id.char_knight);
    knight.setTag(R.id.id, R.drawable.char_knight);
    knight.setTag(R.id.price, 350);
    images.add(knight);
    ImageView samurai = findViewById(R.id.char_samurai);
    samurai.setTag(R.id.id, R.drawable.char_samurai);
    samurai.setTag(R.id.price, 420);
    images.add(samurai);
    ImageView shogun = findViewById(R.id.char_shogun);
    shogun.setTag(R.id.id, R.drawable.char_shogun);
    shogun.setTag(R.id.price, 450);
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
              FragmentManager fm = getSupportFragmentManager();
              FragmentTransaction fragmentTransaction = fm.beginTransaction();
              fragmentTransaction
                  .replace(
                      R.id.frame,
                      new SpriteFragment(
                          (int) img.getTag(R.id.id), (int) img.getTag(R.id.price), username))
                  .addToBackStack(null)
                  .commit();
            }
          });
    }

    userCoins = findViewById(R.id.userAmount);
    LiveData<Integer> userAmount = uR.getUserAmountTest();
    userAmount.observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(Integer integer) {
        userCoins.setText("Your coins: " + integer);

      }
    });
  }

  @Override
  public void onBackPressed() {
    if (onBackPressedListener != null) onBackPressedListener.doBack();
    else super.onBackPressed();
  }

  // Code from https://gist.github.com/nisrulz/3078eaa6357d6f5c0051
  private void grayOut(ImageView img) {
    //         if not owned grey them out
    // I'm only doing this because I can't think of another way to do it
    // Don't mind the horrible time complexity
    //        if (!ownedChars.contains(img.getId())) {
    //            ColorMatrix matrix = new ColorMatrix();
    //            matrix.setSaturation(0);
    //            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
    //            img.setColorFilter(filter);
    //        } else { // no filter
    //            img.setColorFilter(null);
    //            img.setTag("");
    //        }
  }

  public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
    this.onBackPressedListener = onBackPressedListener;
  }
}
