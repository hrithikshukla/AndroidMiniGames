package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;

import com.example.game.R;

class Launcher {

  boolean isMoving = false;
  boolean readyToLaunch = true;
  int x, y, width, height, turnCounter = 0;
  int radius;
  int centreX, centreY;
  int count = 0;


  Bitmap orientation1, orientation2, orientation3, orientation4;
  int screenX, screenY;
  double gravity, speedX, speedY;

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
    gravity = 5;
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

//  void moveX() {
//    if (isMoving) {
//      if (x + width > screenX - 1) {
//        x = screenX - width;
//        speedX = -speedX;
//      }
//      else if (x < 1) {
//        x = 1;
//        speedX = -speedX;
//      }
//        x += speedX;
//    }
//  }
//
//  void moveY() {
//    if (isMoving) {
//      if (y + height > screenY - 1) {
//        y = screenY - height;
//        speedY = -speedY;
//      }
//      else if (y < 1) {
//        y = 1;
//        speedY = -speedY;
//      }
//        y += speedY;
//    }
//  }

  void move() {
    x += speedX;
    y += speedY;

    if(x < 0) {
      x = 0;
      speedX = -speedX;
    } else if(x +width > screenX) {
        x = screenX - width;
        speedX = -speedX;
    }
    if(y < 0) {
      y = 0;
      speedY = - speedY;
    } else if(y + height > screenY) {
      y = screenY - height;
      speedY = - speedY;
    }

  }

  void moveBall(double startX, double startY, double endX, double endY) {
    isMoving = true;
    readyToLaunch = false;
    speedX =  Math.cos(Math.atan2(endY-startY, endX-startX))*300;
    speedY =  Math.sin(Math.atan2(endY-startY, endX-startX))*300;
  }

  void update() {
    if(isMoving) {
      move();
      speedX = decrement(speedX);
      speedY = decrement(speedY);
      if(speedX == 0 && speedY == 0) {
        isMoving = false;
      }
    }
    if (!isMoving && count != 60){ //resets ball after 1 second of non-movement by counting 60 frames
      count++;
    }
    if(count == 60) {
      count = 0;
      y = 1850;
      x = screenX / 2;
      readyToLaunch = true;
    }
  }

  double decrement(double speed) { //reduces speed by gravity amount
    //Log.d("", "decremented " + speed);
    if (speed >= 0 ) {
      speed -= gravity;
      if(speed < 0 ) {
        speed = 0;
      }
    }
    else {
      speed += gravity;
      if(speed > 0 ) {
        speed = 0;
      }
    }
    return speed;
  }


  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean isReadyToLaunch() {
    return readyToLaunch;
  }
}

