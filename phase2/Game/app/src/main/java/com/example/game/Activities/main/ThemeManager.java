package com.example.game.Activities.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.game.R;
import com.example.game.TilesGame.TileGameActivity;

/** A theme manager to handle the changing of themes in the user interface. */
public class ThemeManager {
  static final int LIGHT = 0;
  static final int DARK = 1;
  static final int THEME_DEFAULT = 0;
  static final int THEME_GP = 1;
  static final int THEME_OT = 2;
  static final int THEME_BP = 3;

  public static int getDARK() {
    return DARK;
  }

  /** Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
  static void changeToTheme(Activity activity, int mode, int theme, String username) {
    switch (mode) {
      default:
      case LIGHT:
        setThemeLight(activity, theme);
        break;
      case DARK:
        setThemeDark(activity, theme);
        break;
    }
    //    usr.getUserData().setPrefs(null);
    Intent intent = new Intent(activity, activity.getClass());
    intent.putExtra("USERNAME", username);
    activity.finish();
    activity.startActivity(intent);
  }

  /**
   * Set the theme of the activity, according to the configuration.
   *
   * @param activity: the activity being changed.
   */
  public static void setTheme(Activity activity, int mode, int theme) {
    switch (mode) {
      default:
      case LIGHT:
        setThemeLight(activity, theme);
        break;
      case DARK:
        setThemeDark(activity, theme);
        break;
    }
  }

  /**
   * Set the theme of the activity in light mode, according to the configuration.
   *
   * @param activity: the activity being changed.
   */
  private static void setThemeLight(Activity activity, int theme) {
    switch (theme) {
      default:
      case THEME_DEFAULT:
        activity.setTheme(R.style.LightDefaultTheme);
        break;
      case THEME_GP:
        activity.setTheme(R.style.LightGPTheme);
        break;
      case THEME_OT:
        activity.setTheme(R.style.LightOTTheme);
        break;
      case THEME_BP:
        activity.setTheme(R.style.LightBPTheme);
        break;
    }
  }

  /**
   * Set the theme of the activity in dark mode, according to the configuration.
   *
   * @param activity: the activity being changed.
   */
  private static void setThemeDark(Activity activity, int theme) {
    switch (theme) {
      default:
      case THEME_DEFAULT:
        activity.setTheme(R.style.DarkDefaultTheme);
        break;
      case THEME_GP:
        activity.setTheme(R.style.DarkGPTheme);
        break;
      case THEME_OT:
        activity.setTheme(R.style.DarkOTTheme);
        break;
      case THEME_BP:
        activity.setTheme(R.style.DarkBPTheme);
        break;
    }
  }

  public static void addThemeColors(
      TileGameActivity activity, SharedPreferences mSettings, String username) {
    int mode = mSettings.getInt(username + "mode", 0);
    int theme = mSettings.getInt(username + "theme", 0);
    SharedPreferences.Editor editor = mSettings.edit();
    switch (mode) {
      default:
      case LIGHT:
        editor.putInt(
            username + "colorDangerTile",
            activity.getResources().getColor(R.color.lightBackground));
        editor.putInt(
            username + "colorKeyTile", activity.getResources().getColor(R.color.darkBackground));
        editor.apply();
        addThemeLight(activity, editor, username, theme);
        break;
      case DARK:
        editor.putInt(
            username + "colorDangerTile", activity.getResources().getColor(R.color.darkBackground));
        editor.putInt(
            username + "colorKeyTile", activity.getResources().getColor(R.color.lightBackground));
        editor.apply();
        addThemeDark(activity, editor, username, theme);
        break;
    }
  }

  private static void addThemeDark(
      TileGameActivity activity, SharedPreferences.Editor editor, String username, int theme) {
    switch (theme) {
      default:
      case THEME_DEFAULT:
        editor.putInt(
            username + "colorTouch", activity.getResources().getColor(R.color.defaultAccent));
        editor.putInt(username + "colorLose", activity.getResources().getColor(R.color.lightRed));
        editor.apply();
        break;
      case THEME_GP:
        editor.putInt(username + "colorTouch", activity.getResources().getColor(R.color.gpPrimary));
        editor.putInt(username + "colorLose", activity.getResources().getColor(R.color.gpAccent));
        editor.apply();
        break;
      case THEME_OT:
        editor.putInt(username + "colorTouch", activity.getResources().getColor(R.color.otPrimary));
        editor.putInt(username + "colorLose", activity.getResources().getColor(R.color.otAccent));
        editor.apply();
        break;
      case THEME_BP:
        editor.putInt(username + "colorTouch", activity.getResources().getColor(R.color.bpAccent));
        editor.putInt(username + "colorLose", activity.getResources().getColor(R.color.bpPrimary));
        editor.apply();
        break;
    }
  }

  private static void addThemeLight(
      TileGameActivity activity, SharedPreferences.Editor editor, String username, int theme) {
    switch (theme) {
      default:
      case THEME_DEFAULT:
        editor.putInt(
            username + "colorTouch", activity.getResources().getColor(R.color.defaultSecondary));
        editor.putInt(username + "colorLose", activity.getResources().getColor(R.color.darkRed));
        editor.apply();
        break;
      case THEME_GP:
        editor.putInt(
            username + "colorTouch", activity.getResources().getColor(R.color.gpPrimaryDark));
        editor.putInt(
            username + "colorLose", activity.getResources().getColor(R.color.gpSecondary));
        editor.apply();
        break;
      case THEME_OT:
        editor.putInt(
            username + "colorTouch", activity.getResources().getColor(R.color.otPrimaryDark));
        editor.putInt(
            username + "colorLose", activity.getResources().getColor(R.color.otSecondary));
        editor.apply();
        break;
      case THEME_BP:
        editor.putInt(
            username + "colorTouch", activity.getResources().getColor(R.color.bpSecondary));
        editor.putInt(
            username + "colorLose", activity.getResources().getColor(R.color.bpPrimaryDark));
        editor.apply();
        break;
    }
  }
}
