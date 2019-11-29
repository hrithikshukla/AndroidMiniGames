package com.example.game.MazeGame.DataStructures;

import java.util.Observable;
import java.util.Observer;

/**
 * Class representing the player's current score. Score is currently calculated based on the number
 * of steps the player has taken.
 */
public class Score implements Observer {

  // Current score.
  private int score;

  public Score(int startingScore) {
    this.score = startingScore;
  }

  /** Decrement current score. */
  void decrementScore() {
    score = Math.max(0, score - 1);
  }

  private void addScore(int val) {
    score += val;
  }

  /** Getter for score. */
  public int getScore() {
    return score;
  }

  @Override
  /**
   * Score is observing a Collectable object, which passes an integer corresponding to the value of
   * a collectable in the maze when notifying its observers.
   */
  public void update(Observable observable, Object o) {
    int val = ((Integer) o);
    addScore(val);
  }
}
