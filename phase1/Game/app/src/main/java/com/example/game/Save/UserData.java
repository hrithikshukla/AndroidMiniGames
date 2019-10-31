package com.example.game.Save;

import java.io.Serializable;

/** Class that stores a User's information about our game e.g. high scores. */
class UserData implements Serializable {

  private int mazeHighScore;
  private int tapiocaHighScore;
  private int tilesHighScore;

  /** Initalize UserData as a clean slate. */
  public UserData() {
    this(0, 0, 0);
  }

  /** Initalize UserData with given values. */
  public UserData(int mazeHighScore, int tapiocaHighScore, int tilesHighScore) {
    this.mazeHighScore = mazeHighScore;
    this.tapiocaHighScore = tapiocaHighScore;
    this.tilesHighScore = tilesHighScore;
  }

  /** Getter for the high score of the Maze game. */
  public int getMazeHighScore() {
    return mazeHighScore;
  }

  /** Setter for the high score of the Maze game. */
  public void setMazeHighScore(int mazeHighScore) {
    this.mazeHighScore = mazeHighScore;
  }

  /** Getter for the high score of the Tapioca Launcher game. */
  public int getTapiocaHighScore() {
    return tapiocaHighScore;
  }

  /** Setter for the high score of the Tapioca Launcher game. */
  public void setTapiocaHighScore(int tapiocaHighScore) {
    this.tapiocaHighScore = tapiocaHighScore;
  }

  /** Getter for the high score of the Tiles game. */
  public int getTilesHighScore() {
    return tilesHighScore;
  }

  /** Setter for the high score of the Tiles game. */
  public void setTilesHighScore(int tilesHighScore) {
    this.tilesHighScore = tilesHighScore;
  }
}
