package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

class Launcher {

  boolean isMoving = false;
  int x, y, width, height, turnCounter = 0;
  int radius;
  int centreX, centreY;
  Bitmap orientation1, orientation2, orientation3, orientation4;
  int directionX, directionY;
  int screenX, screenY;

  Launcher(int screenX, int screenY, Resources res) {
    orientation1 = BitmapFactory.decodeResource(res, R.drawable.tapioca1);
    orientation2 = BitmapFactory.decodeResource(res, R.drawable.tapioca2);
    orientation3 = BitmapFactory.decodeResource(res, R.drawable.tapioca3);
    orientation4 = BitmapFactory.decodeResource(res, R.drawable.tapioca4);

    width = orientation1.getWidth() / 2;
    height = orientation1.getHeight() / 2;

    orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
    orientation2 = Bitmap.createScaledBitmap(orientation2, width, height, false);
    orientation3 = Bitmap.createScaledBitmap(orientation3, width, height, false);
    orientation4 = Bitmap.createScaledBitmap(orientation4, width, height, false);

    y = 1850;
    x = screenX / 2;
    this.screenX = screenX;
    this.screenY = screenY;
    radius = width / 2;
    centreX = this.x + radius;
    centreY = this.y + radius;
  }

  Bitmap getLauncher() {
    if (isMoving) {
      switch (turnCounter) {
        case 0:
          turnCounter++;
          return orientation1;
        case 1:
          turnCounter++;
          return orientation2;
        case 2:
          turnCounter++;
          return orientation3;
        case 3:
          turnCounter = 0;
          return orientation4;
        default:
          turnCounter = 0;
          return orientation1;
      }
    } else return orientation1;
  }

  void moveX(int speed) {
    if (isMoving) {
      if (x + width > screenX - 1) {
        speed = -speed;
      }
      if (x - width < 1) {
        speed = -speed;
      }
      x += speed;
    }
  }

  void moveY(int speed) {
    if (isMoving) {
      if (y + height > screenY - 1) {
        speed = -speed;
      }
      if (y - height < 1) {
        speed = -speed;
      }
      y += speed;
    }
  }
}

