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
  // Whether this ball has been hit once or not
  private boolean hit = false;
  // The number of hits to break this tapioca
  private int hp;

  Ball(int x, int y, int radius, int hp) {

    this.x = x;
    this.y = y;
    this.radius = radius;
    this.hp = hp;

  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public boolean isHit() {
    return hit;
  }

  public void setHit(boolean hit) {
    this.hit = hit;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

}
