package com.example.game.MazeGame;

/**
 * Class containing updated information about the current game state.
 */
public class NewGameState {

  private Cell[][] grid;
  private int score;

  private int numSteps;

  private boolean gameOver;

  public NewGameState(Cell[][] grid, int score, int numSteps, boolean gameOver) {
    this.grid = grid;
    this.score = score;
    this.numSteps = numSteps;
    this.gameOver = gameOver;
  }

  /**
   * Getter for grid.
   */
  Cell[][] getGrid() {
    return grid;
  }

  /**
   * Getter for score.
   */
  int getScore() {
    return score;
  }

  /**
   * Getter for numSteps.
   */
  int getNumSteps() {
    return numSteps;
  }

  /**
   * Getter for isGameOver.
   */
  boolean isGameOver() {
    return gameOver;
  }
}
