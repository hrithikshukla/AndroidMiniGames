package com.example.game.TapiocaLauncher;

import java.util.List;
import java.util.Observable;

/**
 * A GameFacade which represents the Model of the MVC by holding the Launcher and Ball objects, and
 * other stats related to the game
 */
public class GameFacade extends Observable {
  /** The launcher ball the player launches */
  private Launcher launcher;
  /** A list of balls that the user wants to destroy */
  private List<Ball> balls;
  /** the current score */
  private int score;
  /** the current level */
  private int level = 1;
  /** The current number of shots left */
  private int shots = 10;
  /** If the game is over or not */
  private boolean gameOver = false;
  /** Generates the board design */
  private BoardManager boardManager;

  /**
   * Creates a gameFacade instance with provided arguments
   *
   * @param launcher - Launcher object the model contains
   * @param balls - List of balls the model contains
   */
  GameFacade(Launcher launcher, List<Ball> balls) {

    this.launcher = launcher;
    this.balls = balls;
    this.score = 0;
    boardManager = new BoardManager();
  }

  /** Class is observed by VisualView and GameActivity which get notified if this class changes */
  void update() {
    setChanged();
    notifyObservers(this);
  }

  /** Getters and Setters */
  Launcher getLauncher() {
    return launcher;
  }

  List<Ball> getBalls() {
    return balls;
  }

  void setBalls(int level) {
    balls = boardManager.getLevel(level);
  }

  int getLevel() {
    return level;
  }

  void setLevel(int level) {
    this.level = level;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  boolean isGameOver() {
    return gameOver;
  }

  void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  int getShots() {
    return shots;
  }

  void setShots(int shots) {
    this.shots = shots;
  }
}
