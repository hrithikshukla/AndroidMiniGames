package com.example.game.Activities.main;

import android.app.Activity;
import android.content.Intent;

import com.example.game.R;
import com.example.game.Save.User;

/** A theme manager to handle the changing of themes in the user interface. */
public class ThemeManager {
  static final int LIGHT = 0;
  static final int DARK = 1;
  static final int THEME_DEFAULT = 0;
  static final int THEME_GP = 1;
  static final int THEME_OT = 2;
  static final int THEME_BP = 3;

  /** Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
  static void changeToTheme(Activity activity, int mode, int theme, User usr) {
    switch (mode) {
      default:
      case LIGHT:
        setThemeLight(activity, theme);
        break;
      case DARK:
        setThemeDark(activity, theme);
        break;
    }
    usr.getUserData().setPrefs(null);
    Intent intent = new Intent(activity, activity.getClass());
    intent.putExtra("UserObject", usr);
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
}
