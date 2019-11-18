package com.example.game;

import android.app.Activity;
import android.content.Intent;

import com.example.game.Save.User;

/** A theme manager to handle the changing of themes in the user interface. */
public class ThemeManager {
  static final int THEME_DEFAULT = 0;
  static final int THEME_GP = 1;
  static final int THEME_OT = 2;
  static final int THEME_BP = 3;

  /** Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
  static void changeToTheme(Activity activity, int theme, User usr) {
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
  public static void setTheme(Activity activity, int theme) {
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
}
