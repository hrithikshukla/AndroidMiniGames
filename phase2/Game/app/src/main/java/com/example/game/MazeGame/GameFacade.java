package com.example.game.MazeGame;

import android.util.Pair;

import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.Player;

import java.util.Observable;

/**
 * A facade class of the game that contains an instance of maze and player i.e the model of our game
 * Notifies its observers whenever the controller modifies and changes the model.
 */
class GameFacade extends Observable {

  /** Player in the game */
  private Player player;
  /** Maze in the game */
  private Maze maze;

  /**
   * Initialize a GameFacade object with the given Player and Maze objects.
   *
   * @param player - player of the game
   * @param maze - maze of the game
   */
  GameFacade(Player player, Maze maze) {
    this.player = player;
    this.maze = maze;
  }

  /**
   * Getter for the player
   *
   * @return Player object
   */
  Player getPlayer() {
    return player;
  }

  /**
   * Getter for the maze
   *
   * @return Maze object
   */
  Maze getMaze() {
    return maze;
  }

  /**
   * Updates the player's position given the movement vector.
   *
   * @param movement_vector - vector representing the player's motion
   */
  void update(Pair<Integer, Integer> movement_vector) {
    int xDisplacement = movement_vector.first;
    int yDisplacement = movement_vector.second;
    player.displace(xDisplacement, yDisplacement);
    update();
  }

  /** Notify observers that the model has been modified. */
  void update() {
    setChanged();
    notifyObservers();
  }
}
