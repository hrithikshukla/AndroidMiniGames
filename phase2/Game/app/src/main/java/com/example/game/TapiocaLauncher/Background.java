package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

/** Background image of the Tapioca game */
class Background {

  /** Coordinates to represent the top left of the background */
  private int x, y;

  /** The bitmap image of the background */
  private Bitmap background;

  /**
   * Creates the background image based on the arguments
   *
   * @param screenX - x-size of screen
   * @param screenY - y-size of screen
   * @param res -image to display
   */
  Background(int screenX, int screenY, Resources res) {
    x = 0;
    y = 0;
    background = BitmapFactory.decodeResource(res, R.drawable.milktea);
    background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
  }

  /** Getters and Setters */
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  Bitmap getBackground() {
    return background;
  }
}
