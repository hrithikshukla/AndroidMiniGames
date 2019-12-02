package com.example.game.Activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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
    private List<Integer> ownedChars;
  private String username;

  // Number of coins the user has


  @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
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
      worm.setTag(R.id.num, R.drawable.char_worm);
    worm.setTag(R.id.price, 21);
      worm.setTag(R.id.id, R.id.char_worm);
    images.add(worm);
    ImageView bird = findViewById(R.id.char_bird);
      bird.setTag(R.id.num, R.drawable.char_bird_blue);
    bird.setTag(R.id.price, 42);
      bird.setTag(R.id.id, R.id.char_bird);
    images.add(bird);
    ImageView spider = findViewById(R.id.char_spider);
      spider.setTag(R.id.num, R.drawable.char_spider);
    spider.setTag(R.id.price, 50);
      spider.setTag(R.id.id, R.id.char_spider);
    images.add(spider);
    ImageView dog = findViewById(R.id.char_dog);
      dog.setTag(R.id.num, R.drawable.char_dog);
    dog.setTag(R.id.price, 69);
      dog.setTag(R.id.id, R.id.char_dog);
    images.add(dog);
    ImageView tree = findViewById(R.id.char_tree);
      tree.setTag(R.id.num, R.drawable.char_tree);
    tree.setTag(R.id.price, 80);
      tree.setTag(R.id.id, R.id.char_tree);
    images.add(tree);
    ImageView astro = findViewById(R.id.char_astro);
    astro.setTag(R.id.num, R.drawable.char_astro);
    astro.setTag(R.id.price, 80);
    astro.setTag(R.id.id, R.id.char_astro);
    images.add(astro);
    ImageView alien = findViewById(R.id.char_alien);
      alien.setTag(R.id.num, R.drawable.char_alien_dark);
    alien.setTag(R.id.price, 90);
    alien.setTag(R.id.id, R.id.char_alien);
    images.add(alien);
    ImageView monster = findViewById(R.id.char_monster);
      monster.setTag(R.id.num, R.drawable.char_monster_red);
    monster.setTag(R.id.price, 100);
      monster.setTag(R.id.id, R.id.char_monster);
    images.add(monster);
    ImageView bat = findViewById(R.id.char_bat);
      bat.setTag(R.id.num, R.drawable.char_bat);
    bat.setTag(R.id.price, 150);
      bat.setTag(R.id.id, R.id.char_bat);
    images.add(bat);
    ImageView king = findViewById(R.id.char_king);
      king.setTag(R.id.num, R.drawable.char_king);
    king.setTag(R.id.price, 151);
      king.setTag(R.id.id, R.id.char_king);
    images.add(king);
    ImageView summoner = findViewById(R.id.char_summoner);
      summoner.setTag(R.id.num, R.drawable.char_summoner);
    summoner.setTag(R.id.price, 1000);
      summoner.setTag(R.id.id, R.id.char_summoner);
    images.add(summoner);
    ImageView viking1 = findViewById(R.id.char_viking1);
      viking1.setTag(R.id.num, R.drawable.char_viking_1);
    viking1.setTag(R.id.price, 200);
      viking1.setTag(R.id.id, R.id.char_viking1);
    images.add(viking1);
    ImageView viking2 = findViewById(R.id.char_viking2);
      viking2.setTag(R.id.num, R.drawable.char_viking_2);
    viking2.setTag(R.id.price, 200);
      viking2.setTag(R.id.id, R.id.char_viking2);
    images.add(viking2);
    ImageView viking3 = findViewById(R.id.char_viking3);
      viking3.setTag(R.id.num, R.drawable.char_viking_3);
    viking3.setTag(R.id.price, 200);
      viking3.setTag(R.id.id, R.id.char_viking3);
    images.add(viking3);
    ImageView wizard = findViewById(R.id.char_wizard);
    wizard.setTag(R.id.price, 300);
      wizard.setTag(R.id.num, R.drawable.char_wizard);
      wizard.setTag(R.id.id, R.id.char_wizard);
    images.add(wizard);
    ImageView archer = findViewById(R.id.char_archer);
      archer.setTag(R.id.num, R.drawable.char_archer);
    archer.setTag(R.id.price, 300);
      archer.setTag(R.id.id, R.id.char_archer);
    images.add(archer);
    ImageView knight = findViewById(R.id.char_knight);
      knight.setTag(R.id.num, R.drawable.char_knight);
    knight.setTag(R.id.price, 350);
      knight.setTag(R.id.id, R.id.char_knight);
    images.add(knight);
    ImageView samurai = findViewById(R.id.char_samurai);
      samurai.setTag(R.id.num, R.drawable.char_samurai);
    samurai.setTag(R.id.price, 420);
      samurai.setTag(R.id.id, R.id.char_samurai);
    images.add(samurai);
    ImageView shogun = findViewById(R.id.char_shogun);
      shogun.setTag(R.id.num, R.drawable.char_shogun);
    shogun.setTag(R.id.price, 450);
      shogun.setTag(R.id.id, R.id.char_shogun);
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
                                  (int) img.getTag(R.id.num),
                                  (int) img.getTag(R.id.price),
                                  (int) img.getTag(R.id.id),
                                  username))
                  .addToBackStack(null)
                  .commit();
            }
          });
    }

      TextView userCoins = findViewById(R.id.userAmount);
      int userAmount = uR.getUserAmount();

      userCoins.setText(userCoins.getText() + ": " + userAmount);

    // Number of characters the user owns
    TextView numChars = findViewById(R.id.numChars);
    numChars.setText(numChars.getText().toString() + ownedChars.size());

  }

  @Override
  public void onBackPressed() {
    if (onBackPressedListener != null) onBackPressedListener.doBack();
    else super.onBackPressed();
  }

  // Code from https://gist.github.com/nisrulz/3078eaa6357d6f5c0051
  private void grayOut(ImageView img) {
//     if not owned grey them out
//     Don't mind the horrible time complexity
    if (ownedChars == null || !ownedChars.contains(img.getTag(R.id.id))) {
          ColorMatrix matrix = new ColorMatrix();
          matrix.setSaturation(0);
          ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
          img.setColorFilter(filter);

    } else { // no filter
        img.setColorFilter(null);
    }
  }

  public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
    this.onBackPressedListener = onBackPressedListener;
  }
}
