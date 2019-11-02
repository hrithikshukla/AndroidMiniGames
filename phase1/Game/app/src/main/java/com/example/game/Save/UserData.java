package com.example.game.Save;

import android.content.SharedPreferences;

import java.io.Serializable;

/** Class that stores a User's information about our game e.g. high scores. */
public class UserData implements Serializable {

  private int mazeHighScore;
  private int tapiocaHighScore;
  private int tilesHighScore;
  private String userName;
  private SharedPreferences sharedPreferences;

  /** Initalize UserData as a clean slate. */
  UserData() {
    this(0, 0, 0);
  }

  /** Initalize UserData with given values. */
  private UserData(int mazeHighScore, int tapiocaHighScore, int tilesHighScore) {
    this.mazeHighScore = mazeHighScore;
    this.tapiocaHighScore = tapiocaHighScore;
    this.tilesHighScore = tilesHighScore;
  }

  public void updateScores() {
    setMazeHighScore();
    setTapiocaHighScore();
    setTilesHighScore();
  }

  /** Getter for the high score of the Maze game. */
  public int getMazeHighScore() {
    return mazeHighScore;
  }

  /** Setter for the high score of the Maze game. */
  private void setMazeHighScore() {
    mazeHighScore = sharedPreferences.getInt(userName + "mazehighscore", 0);
  }

  /** Getter for the high score of the Tapioca Launcher game. */
  public int getTapiocaHighScore() {
    return tapiocaHighScore;
  }

  /** Setter for the high score of the Tapioca Launcher game. */
  private void setTapiocaHighScore() {
    this.tapiocaHighScore = sharedPreferences.getInt(userName + "tapiocahighscore", 0);
  }

  /** Getter for the high score of the Tiles game. */
  public int getTilesHighScore() {
    return tilesHighScore;
  }

  /** Setter for the high score of the Tiles game. */
  private void setTilesHighScore() {
    this.tilesHighScore = sharedPreferences.getInt(userName + "tileshighscore", 0);
  }

  public void setPrefs(SharedPreferences prefs) {
    this.sharedPreferences = prefs;
  }

  void setUserName (String name) {
    this.userName = name;
  }

  public void resetScores() {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(userName + "tapiocahighscore", 0);
    editor.putInt(userName + "mazehighscore", 0);
    editor.putInt(userName + "tileshighscore", 0);
    editor.apply();
  }
}
