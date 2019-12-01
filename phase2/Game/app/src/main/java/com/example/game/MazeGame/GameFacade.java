package com.example.game.MazeGame;

import android.util.Pair;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.Player;

import java.util.Observable;

/**
 * A facade class of the game that contains an instance of maze and player i.e the model of our game
 */
public class GameFacade extends Observable {

  // Player in the game
  private Player player;
  // Maze in the game
  private Maze maze;

  // Assume player init position is in a valid position in the map
  GameFacade(Player player, Maze maze) {
    this.player = player;
    this.maze = maze;
  }

  Player getPlayer() {
    return player;
  }

  Maze getMaze() {
    return maze;
  }

  // updates player positoin
  void update(Pair<Integer, Integer> movement_vector) {
    // movement vector from user input
    int xDisplacement = movement_vector.first;
    int yDisplacement = movement_vector.second;
    player.displace(xDisplacement, yDisplacement);
    update();
  }

  // Notify classes that are observing this(InputView, OutputView)
  void update() {
    setChanged();
    notifyObservers();
  }

  private Cell[][] getCellRepresentation() {
    // Must use a deep copy to prevent aliasing when replacing a Cell Tile with a
    // Player Cell Tile.
    return maze.getGridDeepCopy();
  }

}
