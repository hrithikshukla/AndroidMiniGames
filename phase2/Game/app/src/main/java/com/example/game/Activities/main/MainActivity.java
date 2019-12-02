package com.example.game.Activities.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.game.Activities.main.GameLauncher.MazeGameLauncher;
import com.example.game.Activities.main.GameLauncher.TapiocaGameLauncher;
import com.example.game.Activities.main.GameLauncher.TilesGameLauncher;
import com.example.game.R;

import java.util.Locale;

import static java.util.Locale.setDefault;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

  boolean isSettingsMenu = false;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Get the user.
    String username = getIntent().getStringExtra("USERNAME");
    // Set the theme.
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(
        MainActivity.this,
        mSettings.getInt(username + "mode", 0),
        mSettings.getInt(username + "theme", 0));
    // Determine settings menu visibility.
    if (getIntent().getSerializableExtra("SettingsMenu") != null) {
      isSettingsMenu = (boolean) getIntent().getSerializableExtra("SettingsMenu");
    }

    super.onCreate(savedInstanceState);
    loadLocale();
    setContentView(R.layout.activity_main);

    // If settings menu is visible:
    if (isSettingsMenu) {
      // Set the settings menu to be visible
      View settingsView = findViewById(R.id.settingsView);
      settingsView.setVisibility(View.VISIBLE);
      // Set the game buttons to be hidden
      Button buttonMaze = findViewById(R.id.buttonMaze);
      Button buttonLauncher = findViewById(R.id.buttonTapiocaLauncher);
      Button buttonTiles = findViewById(R.id.buttonTiles);
      buttonMaze.setVisibility(View.INVISIBLE);
      buttonLauncher.setVisibility(View.INVISIBLE);
      buttonTiles.setVisibility(View.INVISIBLE);
    }

    // Set toggle of light/dark mode to match user preference.
    ToggleButton toggleMode = findViewById(R.id.toggleThemeMode);
    if ((mSettings.getInt(username + "mode", 0)) == ThemeManager.DARK) {
      toggleMode.toggle();
    }

    Button changeLang = findViewById(R.id.changeMyLang);
    changeLang.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            // Show alert dialog to display languages
            showChangeLanguageDialog();
          }
        });
    ImageView arrow = findViewById(R.id.ArrowRight);

    // Code based on https://stackoverflow.com/a/24256106/10322608
    arrow.setOnTouchListener(
        new OnSwipeTouchListener(MainActivity.this) {
          @Override
          public void onSwipeLeft() {
            // your actions
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            putUserName(intent);
            startActivity(intent);
          }
        });

    ImageView shop = findViewById(R.id.shopArrow);
    shop.setOnTouchListener(
        new OnSwipeTouchListener(MainActivity.this) {
          @Override
          public void onSwipeRight() {
            // your actions
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            putUserName(intent);
            startActivity(intent);
          }
        });
  }

  // Code based on https://www.youtube.com/watch?v=zILw5eV9QBQ. I liked the video so I can use it.
  private void showChangeLanguageDialog() {
    final String[] languages = {"Français", "中文", "عربى", "English"};
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
    mBuilder.setTitle("Choose Language...");
    mBuilder.setSingleChoiceItems(
        languages,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {
              // French
              setLocale("fr");
              Intent intent = getIntent();
              putUserName(intent);
              putSettingsMenu(intent);
              startActivity(intent);
              finish();
              overridePendingTransition(0, 0);
            } else if (which == 1) {
              // Chinese
              setLocale("zh");
              Intent intent = getIntent();
              putUserName(intent);
              putSettingsMenu(intent);
              startActivity(intent);
              finish();
              overridePendingTransition(0, 0);
            } else if (which == 2) {
              // English
              setLocale("ar");
              Intent intent = getIntent();
              putUserName(intent);
              putSettingsMenu(intent);
              startActivity(intent);
              finish();
              overridePendingTransition(0, 0);

            } else if (which == 3) {
              // English
              setLocale("en");
              Intent intent = getIntent();
              putUserName(intent);
              putSettingsMenu(intent);
              startActivity(intent);
              finish();
              overridePendingTransition(0, 0);
            }

            dialog.dismiss();
          }
        });
    AlertDialog mDialog = mBuilder.create();
    // Show alert dialog
    mDialog.show();
  }

  private void setLocale(String language) {
    Locale locale = new Locale(language);
    setDefault(locale);
    Configuration config = new Configuration();
    config.setLocale(locale);
    getBaseContext()
        .getResources()
        .updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    // Save data to preferences
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("language", language);
    editor.apply();
  }

  // Load language saved in shared preferences
  public void loadLocale() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    String language = sharedPreferences.getString("language", "");
    setLocale(language);
  }

  public void showChangeThemeDialog(View view) {
    // Setup the SharedPreferences
    final SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);
    final SharedPreferences.Editor editor = mSettings.edit();
    final String username = getIntent().getStringExtra("USERNAME");

    final String[] themes = {
      getString(R.string.colourDefault),
      getString(R.string.colourGreenPurple),
      getString(R.string.colourOrangeTeal),
      getString(R.string.colourBluePink)
    };
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
    mBuilder.setTitle("Choose Theme...");
    mBuilder.setSingleChoiceItems(
        themes,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {
              // Default
              ThemeManager.changeToTheme(
                  MainActivity.this,
                  mSettings.getInt(username + "mode", 0),
                  ThemeManager.THEME_DEFAULT,
                  username);
              // Add the theme's int to SharedPreferences
              editor.putInt(username + "theme", ThemeManager.THEME_DEFAULT);
              // Apply the save
              editor.apply();
            } else if (which == 1) {
              // Green/Purple
              ThemeManager.changeToTheme(
                  MainActivity.this,
                  mSettings.getInt(username + "mode", 0),
                  ThemeManager.THEME_GP,
                  username);
              // Add the theme's int to SharedPreferences
              editor.putInt(username + "theme", ThemeManager.THEME_GP);
              // Apply the save
              editor.apply();
            } else if (which == 2) {
              // Orange/Teal
              ThemeManager.changeToTheme(
                  MainActivity.this,
                  mSettings.getInt(username + "mode", 0),
                  ThemeManager.THEME_OT,
                  username);
              // Add the theme's int to SharedPreferences
              editor.putInt(username + "theme", ThemeManager.THEME_OT);
              // Apply the save
              editor.apply();
            } else if (which == 3) {
              // Orange/Teal
              ThemeManager.changeToTheme(
                  MainActivity.this,
                  mSettings.getInt(username + "mode", 0),
                  ThemeManager.THEME_BP,
                  username);
              // Add the theme's int to SharedPreferences
              editor.putInt(username + "theme", ThemeManager.THEME_BP);
              // Apply the save
              editor.apply();
            }
            // Start MainActivity with new theme.
            Intent intent = getIntent();
            putUserName(intent);
            putSettingsMenu(intent);
            startActivity(intent);
            overridePendingTransition(0, 0); // Remove new Activity transition.

            dialog.dismiss();
          }
        });
    AlertDialog mDialog = mBuilder.create();
    // Show alert dialog
    mDialog.show();
  }

  /** Called when the user toggles the 'LIGHT'/'DARK' button in the settings menu */
  public void toggleThemeMode(View view) {
    // Setup the SharedPreferences
    final SharedPreferences mSettings = getSharedPreferences("Settings", MODE_PRIVATE);
    final SharedPreferences.Editor editor = mSettings.edit();
    final String username = getIntent().getStringExtra("USERNAME");

    ToggleButton toggleMode = findViewById(R.id.toggleThemeMode);
    if (mSettings.getInt(username + "mode", 0) == ThemeManager.LIGHT) { // If user is on light mode
      // Add dark mode's int to SharedPreferences
      editor.putInt(username + "mode", ThemeManager.DARK);
      ThemeManager.changeToTheme(
          MainActivity.this, ThemeManager.DARK, mSettings.getInt(username + "theme", 0), username);
      // Apply the save
      editor.apply();
    } else { // If user is on dark mode
      // Add light mode's int to SharedPreferences
      editor.putInt(username + "mode", ThemeManager.LIGHT);
      ThemeManager.changeToTheme(
          MainActivity.this, ThemeManager.LIGHT, mSettings.getInt(username + "theme", 0), username);
      // Apply the save
      editor.apply();
    }
    toggleMode.toggle(); // Toggle button mode
    // Start MainActivity with new mode.
    Intent intent = getIntent();
    putUserName(intent);
    putSettingsMenu(intent);
    startActivity(intent);
    overridePendingTransition(0, 0); // Remove new Activity transition.
  }

  /** Called when the user taps the 'MAZE' button */
  public void goToMazeGame(View view) {
    Intent intent = new Intent(this, MazeGameLauncher.class);
    putUserName(intent);
    startActivity(intent);
  }

  /** Called when the user taps the 'TAPIOCA LAUNCHER' button */
  public void goToTapiocaLauncher(View view) {
    Intent intent = new Intent(this, TapiocaGameLauncher.class);
    putUserName(intent);
    startActivity(intent);
  }

  /** Called when the user taps the 'TILES' button */
  public void goToTilesGame(View view) {
    Intent intent = new Intent(this, TilesGameLauncher.class);
    //    intent.putExtra("UserObject", usr);
    putUserName(intent);
    startActivity(intent);
  }

  /** Called when the user taps the Settings button; makes the settings menu visible. */
  public void openSettings(View view) {
    // Set the settings menu to be visible
    View settingsView = findViewById(R.id.settingsView);
    settingsView.setVisibility(View.VISIBLE);
    isSettingsMenu = true;
    // Set the game buttons to be hidden
    Button buttonMaze = findViewById(R.id.buttonMaze);
    Button buttonLauncher = findViewById(R.id.buttonTapiocaLauncher);
    Button buttonTiles = findViewById(R.id.buttonTiles);
    buttonMaze.setVisibility(View.INVISIBLE);
    buttonLauncher.setVisibility(View.INVISIBLE);
    buttonTiles.setVisibility(View.INVISIBLE);
  }

  /** Called when the user taps the 'x' button; makes the settings menu invisible. */
  public void closeSettings(View view) {
    // Set the settings menu to be hidden
    View settingsView = findViewById(R.id.settingsView);
    settingsView.setVisibility(View.INVISIBLE);
    isSettingsMenu = false;
    // Set the game buttons to be visible
    Button buttonMaze = findViewById(R.id.buttonMaze);
    Button buttonLauncher = findViewById(R.id.buttonTapiocaLauncher);
    Button buttonTiles = findViewById(R.id.buttonTiles);
    buttonMaze.setVisibility(View.VISIBLE);
    buttonLauncher.setVisibility(View.VISIBLE);
    buttonTiles.setVisibility(View.VISIBLE);
  }

  private void putUserName(Intent intent) {
    String userName = getIntent().getStringExtra("USERNAME");
    intent.putExtra("USERNAME", userName);
  }

  private void putSettingsMenu(Intent intent) {
    intent.putExtra("SettingsMenu", isSettingsMenu);
  }
}
