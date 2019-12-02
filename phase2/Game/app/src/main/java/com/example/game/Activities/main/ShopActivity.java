package com.example.game.Activities.main;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

import java.util.List;

// https://opengameart.org/content/2d-complete-characters credit for sprites
// The shop activity that displays all the charactes owned and not owned by the character, as well
// As the user's coins
@SuppressWarnings("SuspiciousMethodCalls")
public class ShopActivity extends AppCompatActivity {

  // Checks if back button is pressed
  protected OnBackPressedListener onBackPressedListener;
  // Characters owned by the user sorted by imageId
  private List<Integer> ownedChars;
  // Username of the user
  private String username;

  /** @param savedInstanceState the save state of the application */
  @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    // get the username from the previous activity
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

    // Set swipe listener for arrows
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

    // Retieve list of images to display
    ImageListCreator imageListCreator = new ImageListCreator(this);
    List<ImageView> images = imageListCreator.setImageList().getImageList();

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

    // Set user coins and user character textViews
    TextView userCoins = findViewById(R.id.userAmount);
    userCoins.setText(userCoins.getText() + ": " + uR.getUserAmount());
    TextView numChars = findViewById(R.id.numChars);
    numChars.setText(numChars.getText().toString() + ownedChars.size());
  }

  @Override
  public void onBackPressed() {
    if (onBackPressedListener != null) onBackPressedListener.doBack();
    else super.onBackPressed();
  }

  /** @param img the image to be grayed out */
  // Code from https://gist.github.com/nisrulz/3078eaa6357d6f5c0051
  private void grayOut(ImageView img) {
    //     if not owned grey them out
    //     Don't mind the horrible time complexity
    if (ownedChars == null || !ownedChars.contains(img.getTag(R.id.id))) {
      ColorMatrix matrix = new ColorMatrix();
      matrix.setSaturation(0);
      ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
      img.setColorFilter(filter);

    } else {
      img.setColorFilter(null);
    }
  }

  /** @param onBackPressedListener the new onBackPressedListener object */
  public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
    this.onBackPressedListener = onBackPressedListener;
  }
}
