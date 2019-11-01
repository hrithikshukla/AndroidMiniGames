package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.game.R;
import com.example.game.ScoreManager;

import java.util.List;

// Tapioca that the user launches to clear the stage
class Launcher {

  // Used to animate the ball and move it
  private boolean isMoving = false;
  // The top left coordinates of the ball
  private int x, y;
  // The width, height, radius of the ball
  private int width, height, radius;
  // Used to animate the ball
  private int turnCounter = 0;
  // The bitmaps of the ball to animate the ball's spinning
  private Bitmap orientation1, orientation2, orientation3, orientation4;
  // The screen length and width to calculate if ball is out of bounds
  private int screenX, screenY;
  private double speedX, speedY;
  private double gravityX, gravityY;

  Launcher(int screenX, int screenY, Resources res, ScoreManager scoreManager) {
    orientation1 = BitmapFactory.decodeResource(res, R.drawable.tapioca1);
    orientation2 = BitmapFactory.decodeResource(res, R.drawable.tapioca2);
    orientation3 = BitmapFactory.decodeResource(res, R.drawable.tapioca3);
    orientation4 = BitmapFactory.decodeResource(res, R.drawable.tapioca4);

    // Scales the bitmap down
    width = orientation1.getWidth() / 2;
    height = orientation1.getHeight() / 2;
    orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
    orientation2 = Bitmap.createScaledBitmap(orientation2, width, height, false);
    orientation3 = Bitmap.createScaledBitmap(orientation3, width, height, false);
    orientation4 = Bitmap.createScaledBitmap(orientation4, width, height, false);

    // Start the ball halfway horizontally in the screen further down
    y = 1850;
    x = screenX / 2 - width/2;
    this.screenX = screenX;
    this.screenY = screenY;
    radius = width / 2;
  }

  // Returns the bitmap of this tapioca, whether it is spinning or not
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

   void move() {
    x += speedX;
    y += speedY;

    if (x < 0) {
      x = 0;
      speedX = -speedX;
    } else if (x + width > screenX) {
      x = screenX - width;
      speedX = -speedX;
    }
    if (y < 0) {
      y = 0;
      speedY = -speedY;
    } else if (y + height > screenY) {
      y = screenY - height;
      speedY = -speedY;
    }

   speedX = decrement(speedX, gravityX);
   speedY = decrement(speedY, gravityY);
  }


//  void update(List<Ball> balls) {
//    if (isMoving) {
//      move();
//      speedX = decrement(speedX, gravityX);
//      speedY = decrement(speedY, gravityY);
//      if (speedX == 0 && speedY == 0) {
//        isMoving = false;
//      }
//    }
//    if (!isMoving
//        && count != 60) { // resets ball after 1 second of non-movement by counting 60 frames
//      count++;
//    }
//    if (count == 60) {
//      count = 0;
//      y = 1850;
//      x = screenX / 2;
//      readyToLaunch = true;
//      for (int i = 0; i < balls.size(); i++) {
//        if (balls.get(i).isHit()) {
//          balls.get(i).setHit(false);
//        }
//      }
//    }
//  }

  private double decrement(double speed, double gravity) { // reduces speed by gravity amount
    // Log.d("", "decremented " + speed);
    if (speed >= 0) {
      speed -= gravity;
      if (speed < 0) {
        speed = 0;
      }
    } else {
      speed += gravity;
      if (speed > 0) {
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

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  int getCentreX() {
    return this.x + radius;
  }

  int getCentreY() {
    return this.y + radius;
  }

  public int getRadius() {
    return radius;
  }

  public double getSpeedX() {
    return speedX;
  }

  public void setSpeedX(double speedX) {
    this.speedX = speedX;
  }

  public double getSpeedY() {
    return speedY;
  }

  public void setSpeedY(double speedY) {
    this.speedY = speedY;
  }

  public void setGravityX(double gravityX) {
    this.gravityX = gravityX;
  }

  public void setGravityY(double gravityY) {
    this.gravityY = gravityY;
  }
}
