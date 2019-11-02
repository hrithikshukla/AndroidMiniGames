package com.example.game.MazeGame.DataStructures;

import java.util.ArrayList;

/** Represents the player in a maze. */
public class Player {

  /** Position of the player. */
  private int posX;

  private int posY;

  /** Number of steps the player has taken. */
  private int numSteps;

  /** Powerups the player currently is affected by. */
  private ArrayList<Powerup> powerups;

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
    this.powerups = new ArrayList<>();
  }

  /** Getter for the powerups player is affected by. */
  public ArrayList<Powerup> getPowerups() {
    return powerups;
  }

  /**
   * Adds the given powerup to the list of powerups affecting the player.
   *
   * @param pu - new powerup that player is affected by
   */
  public void addPowerup(Powerup pu) {
    powerups.add(pu);
  }

  /**
   * Removes the given powerup from the list of powerups affecting the player.
   *
   * @param pu - existing powerup that player is affected by
   */
  public void removePowerup(Powerup pu) {
    if (isAffectedBy(pu)) {
      powerups.remove(pu);
    }
  }

  /**
   * Returns if player is currently affected by the given powerup.
   *
   * @param pu - powerup to be checked
   */
  private boolean isAffectedBy(Powerup pu) {
    return powerups.contains(pu);
  }

  public void displace(int x, int y) {
    posX += x;
    posY += y;
    incrementStep();
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

  /**
   * Return whether Player is at the given coordinates.
   *
   * @param x - x coordinate in question
   * @param y - y coordinate in question
   */
  public boolean isAt(int x, int y) {
    return (posX == x) && (posY == y);
  }
}
