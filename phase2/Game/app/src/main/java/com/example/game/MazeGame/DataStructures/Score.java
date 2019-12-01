package com.example.game.MazeGame.DataStructures;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents the player's current score in the Maze Game. Score is calculated based on the number
 * of steps the player has taken and any collectible items that were picked up. Score observes
 * Collectible, and is updated whenever a collectible item is acquired by the player.
 */
public class Score implements Observer {

  /** Current score. Score must be non-negative. */
  private int score;

  /**
   * Create a Score object with startingScore as the starting score.
   *
   * @param startingScore - starting score
   */
  public Score(int startingScore) {
    this.score = startingScore;
  }

  /** Decrement current score by 1. */
  void decrementScore() {
    addScore(-1);
  }

  /**
   * Getter for score
   *
   * @return - the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Updates the score whenever a collectible item is picked up.
   *
   * @param observable - Collectible object
   * @param o - integer corresponding to the collectible that the player picked up
   */
  @Override
  public void update(Observable observable, Object o) {
    int val = ((Integer) o);
    addScore(val);
  }

  /**
   * Increases the score by val.
   *
   * @param val - value the score is increased by
   */
  private void addScore(int val) {
    score = Math.max(score + val, 0);
  }
}
