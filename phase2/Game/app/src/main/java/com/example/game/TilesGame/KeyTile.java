package com.example.game.TilesGame;

/** A key tile. */
class KeyTile extends TileManager {

  /** A boolean to represent whether this tile has been missed on a board. */
  private boolean missed = false;

  /**
   * Construct a key tile at location (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  KeyTile(int x, int y) {
    super(x, y);
  }

  /** Set this tile to have been missed on a board. */
  void setMissedTrue() {
    this.missed = true;
  }

  /** Return whether this tile has been missed on a board. */
  boolean isMissed() {
    return this.missed;
  }
}
