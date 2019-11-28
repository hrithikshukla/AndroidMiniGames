package com.example.game.MazeGame.DataStructures;

import java.util.Observable;

/** Represents the player in a maze. */
public class Player extends Observable {

  /** Position of the player. */
  private int posX;

  private int posY;

  /** Number of steps the player has taken. */
  private int numSteps;

  /** Player's score. */
  private Score score;

  /**
   * Initializes the player at the given coordinates in the maze.
   *
   * @param x - starting x coordinate
   * @param y - starting y coordinate
   */
  public Player(int x, int y, Score score) {
    this.posX = x;
    this.posY = y;
    this.numSteps = 0;
    this.score = score;
  }

  /**
   * Move the player in given direction.
   *
   * @param x - displacement in the x-direction
   * @param y - displacement in the y-direction
   */
  public void displace(int x, int y) {
    posX += x;
    posY += y;
    incrementStep();
    setChanged();
    notifyObservers(); // Update player position in the maze
  }

  /** Increments the player's numbers of steps by 1. */
  private void incrementStep() {
    numSteps += 1;
    score.decrementScore();
  }

  /** Returns the current position of the player. */
  public int[] getPos() {
    return new int[] {posX, posY};
  }

  /** Returns the player's current score. */
  public int getScore() {
    return score.getScore();
  }

  /** Getter for number of steps. */
  public int getNumSteps() {
    return numSteps;
  }
}
