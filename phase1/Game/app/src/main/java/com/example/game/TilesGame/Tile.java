package com.example.game.TilesGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A tile. */
abstract class Tile {

  /** The width of a tile. */
  static int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;

  /** The height of a tile. */
  static int height = Resources.getSystem().getDisplayMetrics().heightPixels / 4;

  /** The x-coordinate of this tile. */
  int x;

  /** The y-coordinate of this tile. */
  int y;

  /** A boolean representing whether this tile has been touched. */
  boolean touch = false;

  Paint paintRect = new Paint();

  /**
   * Construct a tile at location (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  Tile(int x, int y) {

    this.x = x;
    this.y = y;
  }

  public static int getWidth() {
    return width;
  }

  public static int getHeight() {
    return height;
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

  public boolean isTouch() {
    return touch;
  }

  void setTouch(boolean touch) {
    this.touch = touch;
  }

  /**
   * Move the tile speed units in the y-direction.
   *
   * @param speed: the speed at which the tile moves.
   */
  void move(int speed) {
    y += speed;
  }

  void draw(Canvas canvas) {
    // Draw border of tile.
    paintRect.setStyle(Paint.Style.STROKE);
    paintRect.setColor(Color.rgb(200, 200, 200)); // LIGHT GRAY
    canvas.drawRect(x, y, x + width, y + height, paintRect);
  }
}
