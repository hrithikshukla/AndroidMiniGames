package com.example.game.MazeGame.DataStructures;

/** Represents the collectable score affecting items in a maze. */
public class Collectable extends Item {

  /** Represents the score multiplier of this collectable. */
  private int multiplier;

  /**
   * Constructs a collectable at the given coordinates with the given score multiplier.
   *
   * @param x - x coordinate of the collectable
   * @param y - y coordinate of the collectable
   * @param multiplier - score multiplier of the collectable
   */
  public Collectable(int x, int y, int multiplier) {
    super(x, y);
    this.multiplier = multiplier;
  }

  /** Getter for the score multiplier. */
  int getMultiplier() {
    return multiplier;
  }
}
