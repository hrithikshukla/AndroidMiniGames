package com.example.game.MazeGame.DataStructures;

import com.example.game.MazeGame.DataStructures.Item;

/** Represents a powerup item in a maze. */
public class Powerup extends Item {

  /** Remaining time the powerup lasts for. */
  private int duration;

  /** Effect of the powerup. */
  private String effect;

  /**
   * Constructs a powerup at the given location.
   *
   * @param x - x position of the powerup
   * @param y - y position of the powerup
   * @param duration - initial duration of the powerup
   */
  public Powerup(int x, int y, int duration) {
    super(x, y);
    this.duration = duration;
  }

  /** Returns if the powerup is still active. */
  boolean hasExpired() {
    return duration == 0;
  }

  /** Decreases the duration of the powerup by one turn. */
  void decrementDur() {
    duration -= 1;
  }

  /** Gets the effect of the given powerup. */
  String getEffect() {
    return effect;
  }
}
