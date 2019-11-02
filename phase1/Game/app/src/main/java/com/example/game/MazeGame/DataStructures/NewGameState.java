package com.example.game.MazeGame.DataStructures;

import com.example.game.MazeGame.DataStructures.Cell;

/** Class containing updated information about the current game state. */
public class NewGameState {

  // Cell representation of the maze
  private Cell[][] grid;
  // Score of the player
  private int score;
  // Number of steps a player has taken
  private int numSteps;
  // Indicate whether the game has ended, i.e player has reached the exit
  private boolean gameOver;

  public NewGameState(Cell[][] grid, int score, int numSteps, boolean gameOver) {
    this.grid = grid;
    this.score = score;
    this.numSteps = numSteps;
    this.gameOver = gameOver;
  }

  /** Getter for grid. */
  public Cell[][] getGrid() {
    return grid;
  }

  /** Getter for score. */
  public int getScore() {
    return score;
  }

  /** Getter for numSteps. */
  public int getNumSteps() {
    return numSteps;
  }

  /** Getter for isGameOver. */
  public boolean isGameOver() {
    return gameOver;
  }
}
