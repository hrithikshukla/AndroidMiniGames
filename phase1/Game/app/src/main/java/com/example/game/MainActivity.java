package com.example.game;

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

import com.example.game.Save.User;

import java.util.Locale;

import static java.util.Locale.setDefault;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

  User usr;

  @SuppressLint("ClickableViewAccessibility")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme.
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(MainActivity.this, mSettings.getInt("theme", -1));

    super.onCreate(savedInstanceState);
    loadLocale();
    setContentView(R.layout.activity_main);
    usr = (User) getIntent().getSerializableExtra("UserObject");

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
    arrow.setOnTouchListener(
        new OnSwipeTouchListener(MainActivity.this) {
          @Override
          public void onSwipeLeft() {
            // your actions
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            putUser(intent);
            startActivity(intent);
          }
        });
  }

  private void showChangeLanguageDialog() {
    final String[] languages = {"Français", "中文", "Deutsche", "English"};
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
              putUser(intent);
              startActivity(intent);
              usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
              finish();
              overridePendingTransition(0, 0);
            } else if (which == 1) {
              // Chinese
              setLocale("zh");
              Intent intent = getIntent();
              putUser(intent);
              startActivity(intent);
              usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
              finish();
              overridePendingTransition(0, 0);
            } else if (which == 2) {
              // German
              setLocale("de");
              Intent intent = getIntent();
              putUser(intent);
              startActivity(intent);
              usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
              finish();
              overridePendingTransition(0, 0);
            } else if (which == 3) {
              // English
              setLocale("en");
              Intent intent = getIntent();
              putUser(intent);
              startActivity(intent);
              usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
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
    final SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();

    final String[] themes = {"Default", "Green/Purple", "Orange/Teal", "Blue/Pink"};
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
              ThemeManager.changeToTheme(MainActivity.this, ThemeManager.THEME_DEFAULT);
              // Add the theme's int to SharedPreferences
              editor.putInt("theme", ThemeManager.THEME_DEFAULT);
              // Apply the save
              editor.apply();
              overridePendingTransition(0, 0);
            } else if (which == 1) {
              // Green/Purple
              ThemeManager.changeToTheme(MainActivity.this, ThemeManager.THEME_GP);
              // Add the theme's int to SharedPreferences
              editor.putInt("theme", ThemeManager.THEME_GP);
              // Apply the save
              editor.apply();
              overridePendingTransition(0, 0);
            } else if (which == 2) {
              // Orange/Teal
              ThemeManager.changeToTheme(MainActivity.this, ThemeManager.THEME_OT);
              // Add the theme's int to SharedPreferences
              editor.putInt("theme", ThemeManager.THEME_OT);
              // Apply the save
              editor.apply();
              overridePendingTransition(0, 0);
            } else if (which == 3) {
              // Orange/Teal
              ThemeManager.changeToTheme(MainActivity.this, ThemeManager.THEME_BP);
              // Add the theme's int to SharedPreferences
              editor.putInt("theme", ThemeManager.THEME_BP);
              // Apply the save
              editor.apply();
              overridePendingTransition(0, 0);
            }

            dialog.dismiss();
          }
        });
    AlertDialog mDialog = mBuilder.create();
    // Show alert dialog
    mDialog.show();
  }

  /** Called when the user taps the 'MAZE' button */
  public void goToMazeGame(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGameLauncher.class);
    putUser(intent);
    startActivity(intent);
    usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
  }

  /** Called when the user taps the 'TAPIOCA LAUNCHER' button */
  public void goToTapiocaLauncher(View view) {
    Intent intent = new Intent(this, com.example.game.TapiocaGameLauncher.class);
    putUser(intent);
    startActivity(intent);
    usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
  }

  /** Called when the user taps the 'TILES' button */
  public void goToTilesGame(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGameLauncher.class);
    intent.putExtra("UserObject", usr);
    putUser(intent);
    startActivity(intent);
    usr.getUserData().setPrefs(getSharedPreferences("highScores", MODE_PRIVATE));
  }

  private void putUser(Intent intent) {
    usr.getUserData().setPrefs(null);
    intent.putExtra("UserObject", usr);
  }
}
