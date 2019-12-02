package com.example.game.TilesGame;

/** A tile factory. */
class TileFactory {

  /** Construct a tile factory. */
  TileFactory() {}

  /**
   * Create a key tile at (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  KeyTile getKeyTile(int x, int y) {
    return new KeyTile(x, y);
  }

  /**
   * Create a danger tile at (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  DangerTile getDangerTile(int x, int y) {
    return new DangerTile(x, y);
  }
}
