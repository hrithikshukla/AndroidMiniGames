package com.example.game.TilesGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A tile. */
abstract class Tile {

  /** The width of a 4X4 tile. */
  static int width4By4 = Resources.getSystem().getDisplayMetrics().widthPixels / 4;

  /** The height of a 4X4 tile. */
  static int height4By4 = Resources.getSystem().getDisplayMetrics().heightPixels / 4;

  /** The width of a 5X5 tile. */
  static int width5By5 = Resources.getSystem().getDisplayMetrics().widthPixels / 5;

  /** The height of a 4X4 tile. */
  static int height5By5 = Resources.getSystem().getDisplayMetrics().heightPixels / 5;

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

  public static int getWidth4By4() {
    return width4By4;
  }

  public static int getHeight4By4() {
    return height4By4;
  }

  public static int getWidth5By5() {
    return width5By5;
  }

  public static int getHeight5By5() {
    return height5By5;
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

  abstract void draw4By4(Canvas canvas);

  abstract void draw5By5(Canvas canvas);

  void draw4By4Border(Canvas canvas) {
    drawBorder(canvas);
    // Draw a 4X4 tile border.
    canvas.drawRect(x, y, x + width4By4, y + height4By4, paintRect);
  }

  void draw5By5Border(Canvas canvas) {
    drawBorder(canvas);
    // Draw a 4X4 tile border.
    canvas.drawRect(x, y, x + width5By5, y + height5By5, paintRect);
  }

  private void drawBorder(Canvas canvas) {
    // Set brush for border of tile.
    paintRect.setStyle(Paint.Style.STROKE);
    paintRect.setColor(Color.rgb(200, 200, 200)); // LIGHT GRAY
  }
}
