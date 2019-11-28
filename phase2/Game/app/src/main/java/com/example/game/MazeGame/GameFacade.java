package com.example.game.MazeGame;

import android.util.Pair;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.NewGameState;
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
    // Need to supply an updated representation of the new Maze and score to be drawn.
    NewGameState newGameState =
        new NewGameState(
            getCellRepresentation(), player.getScore(), player.getNumSteps(), isGameOver());
    notifyObservers(newGameState);
  }

  private Cell[][] getCellRepresentation() {
    // Must use a deep copy to prevent aliasing when replacing a Cell Tile with a
    // Player Cell Tile.
    Cell[][] representation = maze.getGridDeepCopy();

    // Insert Player Tile into the Maze. Two cases: when Player is at the exit and when they aren't.
    if (isGameOver()) {
      representation[player.getPos()[1]][player.getPos()[0]] = Cell.PLAYER_AT_EXIT;
    } else {
      representation[player.getPos()[1]][player.getPos()[0]] = Cell.PLAYER;
    }

    return representation;
  }

  /** Return whether the player is at the Maze exit. */
  boolean isGameOver() {
    int[] exit = maze.getExit();
    return getPlayer().isAt(exit[0], exit[1]);
  }
}
