package com.example.game.MazeGame;

/** Class containing updated information about the current game state. */
public class NewGameState {

  private Cell[][] grid;
  private int score;

  private int numSteps;

  public NewGameState(Cell[][] grid, int score, int numSteps) {
    this.grid = grid;
    this.score = score;
    this.numSteps = numSteps;
  }

  /** Getter for grid. */
  Cell[][] getGrid() {
    return grid;
  }

  /** Getter for score. */
  int getScore() {
    return score;
  }

  /** Getter for numSteps. */
  int getNumSteps() {
    return numSteps;
  }
}
