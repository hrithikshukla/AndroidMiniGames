package com.example.game.TilesGame;

import android.content.res.Resources;

/** A tile. */
abstract class TileManager implements Tile {

  /** The width of a 4X4 tile. */
  private static int width4By4 = Resources.getSystem().getDisplayMetrics().widthPixels / 4;

  /** The height of a 4X4 tile. */
  private static int height4By4 = Resources.getSystem().getDisplayMetrics().heightPixels / 4;

  /** The width of a 5X5 tile. */
  private static int width5By5 = Resources.getSystem().getDisplayMetrics().widthPixels / 5;

  /** The height of a 4X4 tile. */
  private static int height5By5 = Resources.getSystem().getDisplayMetrics().heightPixels / 5;

  /** The x-coordinate of this tile. */
  private int x;

  /** The y-coordinate of this tile. */
  private int y;

  /** A boolean representing whether this tile has been touched. */
  private boolean touch = false;

  /**
   * Construct a tile at location (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  TileManager(int x, int y) {

    this.x = x;
    this.y = y;
  }

  /** Get the width of a 4X4 tile. */
  static int getWidth4By4() {
    return width4By4;
  }

  /** Get the height of a 4X4 tile. */
  static int getHeight4By4() {
    return height4By4;
  }

  /** Get the width of a 5X5 tile. */
  static int getWidth5By5() {
    return width5By5;
  }

  /** Get the height of a 5X5 tile. */
  static int getHeight5By5() {
    return height5By5;
  }

  /** Get the x-coordinate of this tile. */
  public int getX() {
    return x;
  }

  /** Set the x-coordinate of this tile. */
  public void setX(int x) {
    this.x = x;
  }

  /** Get the y-coordinate of this tile. */
  public int getY() {
    return y;
  }

  /** Set the y-coordinate of this tile. */
  public void setY(int y) {
    this.y = y;
  }

  /** Return whether this tile has been touched. */
  public boolean isTouch() {
    return touch;
  }

  /** Set whether this tile has been touched. */
  public void setTouch(boolean touch) {
    this.touch = touch;
  }

  /**
   * Move the tile speed units in the y-direction.
   *
   * @param speed: the number of units to move this tile by.
   */
  public void move(int speed) {
    y += speed;
  }
}
