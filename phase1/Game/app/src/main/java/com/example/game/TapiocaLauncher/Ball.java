package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

// Taopioca that is either brown or red.
class Ball {

  // The top left coordinates of the ball
  private int x, y;
  // The width, height, radius of the ball
  private int width, height, radius;
  // The centre coordinates of the ball, used for collision detection
  int centreX, centreY;
  // The bitmap of this ball
  private Bitmap orientation1;
  private Resources res;
  // Whether this ball has been hit once or not
  private boolean hit = false;
  // The number of hits to break this tapioca
  private int hp;

  Ball(int x, int y, Resources res, int hp) {
    if (hp == 1) {
      orientation1 = BitmapFactory.decodeResource(res, R.drawable.brown);
    } else if (hp == 2) {
      orientation1 = BitmapFactory.decodeResource(res, R.drawable.red);
    }
    width = orientation1.getWidth() / 2; // 157
    height = orientation1.getHeight() / 2; // 136

    orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
    this.x = x;
    this.y = y;
    radius = width / 2;
    centreX = x + radius;
    centreY = y + radius;
    this.hp = hp;
    this.res = res;
  }

  Bitmap getBall() {
    return orientation1;
  }

  int getHp() {
    return hp;
  }

  void setHp(int hp) {
    this.hp = hp;
    if (hp == 1) {
      orientation1 = BitmapFactory.decodeResource(res, R.drawable.brown);
    } else if (hp == 2) {
      orientation1 = BitmapFactory.decodeResource(res, R.drawable.red);
    }
    width = orientation1.getWidth() / 2; // 157
    height = orientation1.getHeight() / 2; // 136

    orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
    radius = width / 2;
    centreX = x + radius;
    centreY = y + radius;
  }

  boolean isHit() {
    return hit;
  }

  void setHit(boolean hit) {
    this.hit = hit;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
