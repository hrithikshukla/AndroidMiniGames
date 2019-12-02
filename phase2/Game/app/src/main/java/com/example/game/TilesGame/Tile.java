package com.example.game.TilesGame;

/**
 * An abstract tile manager class that implements the Tile interface and that should not be
 * instantiated. *
 */
interface Tile {

  /** Get the x-coordinate of this tile. */
  int getX();

  /** Get the y-coordinate of this tile. */
  int getY();

  /**
   * Move this tile by speed units in the y-direction.
   *
   * @param speed: the number of units to move this tile by.
   */
  void move(int speed);

  /** Return whether this tile has been touched. */
  boolean isTouch();

  /** Set whether this tile has been touched. */
  void setTouch(boolean touch);
}
