package com.example.game.MazeGame.DataStructures;

import java.util.Observable;

/**
 * Represents the player in a maze. Whenever Player moves it notifies its observers of its new
 * position.
 */
public class Player extends Observable {

  /** Position of the player. */
  private int posX, posY;

  /** Number of steps the player has taken. */
  private int numSteps;

  /** Player's score. */
  private Score score;

  /**
   * Creates a new Player object with given starting coordinates and score in the maze.
   *
   * @param x - starting x coordinate
   * @param y - starting y coordinate
   * @param score - Score object associated with the player
   */
  public Player(int x, int y, Score score) {
    this.posX = x;
    this.posY = y;
    this.numSteps = 0;
    this.score = score;
  }

  /**
   * Move the player in given direction and notify its observers of this displacement.
   *
   * @param x - displacement in the x-direction
   * @param y - displacement in the y-direction
   */
  public void displace(int x, int y) {
    posX += x;
    posY += y;
    incrementStep();
    setChanged();
    notifyObservers();
  }

  /** Increments the player's numbers of steps and decrease their score by 1 . */
  private void incrementStep() {
    numSteps += 1;
    score.decrementScore();
  }

  /**
   * Returns the current position of the player.
   *
   * @return - position of the player as a 2D array in the format of [x,y]
   */
  public int[] getPos() {
    return new int[] {posX, posY};
  }

  /**
   * Returns the player's current score.
   *
   * @return - current score of the player
   */
  public int getScore() {
    return score.getScore();
  }

  /**
   * Getter for number of steps.
   *
   * @return - current number of steps taken by the player
   */
  public int getNumSteps() {
    return numSteps;
  }
}
