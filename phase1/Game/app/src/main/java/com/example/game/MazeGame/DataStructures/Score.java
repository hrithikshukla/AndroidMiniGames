package com.example.game.MazeGame.DataStructures;

import com.example.game.MazeGame.DataStructures.Collectable;

/**
 * Class representing the player's current score. Score is currently calculated based on the number
 * of steps the player has taken.
 */
public class Score {

  // Current score.
  private int score;

  public Score(int startingScore) {
    this.score = startingScore;
  }

  /** Decrement current score. */
  void decrementScore() {
    score = Math.max(0, score - 1);
  }

  /**
   * Multiply score by the factor of the collectable.
   *
   * @param collectable a score affecting item
   */
  public void multiplyScore(Collectable collectable) {
    score *= collectable.getMultiplier();
  }

  /** Getter for score. */
  public int getScore() {
    return score;
  }
}
