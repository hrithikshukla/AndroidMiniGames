package com.example.game.MazeGame;

import android.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

// TODO: return a Cell 2d array representation of game; method name getGrid()

public class GameFacade extends Observable {

  private Player player;
  private Maze maze;

  private Map<Movement, Pair<Integer, Integer>> movementMap = new HashMap<>();

  // Assume player init position is in a valid position in the map
  public GameFacade(Player player, Maze maze) {
    this.player = player;
    this.maze = maze;
  }

  Player getPlayer() {
    return player;
  }

  Maze getMaze() {
    return maze;
  }

  void update(Pair<Integer, Integer> movement_vector) {
    // moves player
    int xDisplacement = movement_vector.first;
    int yDisplacement = movement_vector.second;
    player.displace(xDisplacement, yDisplacement);
    update();
  }

  void update() {
    setChanged();
    // Need to supply an updated representation of the new Maze and score to be drawn.
    NewGameState newGameState =
        new NewGameState(getCellRepresentation(), player.getScore(), player.getNumSteps());
    notifyObservers(newGameState);
  }

  private Cell[][] getCellRepresentation() {
    // Must use a deep copy to prevent aliasing when replacing a Cell Tile with a
    // Player Cell Tile.
    Cell[][] representation = maze.getGridDeepCopy();

    // Insert Player Tile into the Maze. Two cases: when Player is at the exit and when they aren't.
    if (hasPlayerEscaped()) {
      representation[player.getPos()[1]][player.getPos()[0]] = Cell.PLAYER_AT_EXIT;
    } else {
      representation[player.getPos()[1]][player.getPos()[0]] = Cell.PLAYER;
    }

    return representation;
  }

  /**
   * Return whether the player is at the Maze exit.
   */
  boolean hasPlayerEscaped() {
    int[] exit = maze.getExit();
    return getPlayer().isAt(exit[0], exit[1]);
  }
}
