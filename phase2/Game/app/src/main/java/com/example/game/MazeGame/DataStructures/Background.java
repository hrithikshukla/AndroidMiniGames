package com.example.game.MazeGame.DataStructures;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

// A black background for the maze game
public class Background {

  // Top left corner coordinate of a bit map
  private final int x = 0, y = 0;

  // Bitmap of our background png
  private Bitmap background;

  public Background(int screenX, int screenY, Resources res) {
    this.background = BitmapFactory.decodeResource(res, R.drawable.background);
    background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Bitmap getBackground() {
    return background;
  }
}
