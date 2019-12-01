package com.example.game.MazeGame;

import android.util.Pair;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Movement;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The controller of our game, handles user inputs and handles game logic. Observes InputView to
 * respond to any inputs made by the player to move.
 */
class GameController implements Observer {

  /** Model of the maze game */
  private GameFacade gameFacade;

  /** Maps movement enum to a valid x,y vector */
  private Map<Movement, Pair<Integer, Integer>> movementMap = new HashMap<>();

  /**
   * Initializes a GameController object with the given model of the maze.
   *
   * @param gameFacade - model of the maze
   */
  GameController(GameFacade gameFacade) {
    this.gameFacade = gameFacade;
    buildMovementMap();
  }

  /**
   * Update the model with the given movement if the player hasn't escaped.
   *
   * @param mov - a movement enum
   */
  private void updateModel(Movement mov) {
    if (!gameFacade.getMaze().hasEscaped()) {
      Pair<Integer, Integer> movement_vector = movementMap.get(mov);
      // Don't want to update score if player isn't moving.
      if (mov != Movement.AFK && boundaryCheck(movement_vector)) {
        gameFacade.update(movement_vector);
      }
    }
  }

  /**
   * Check if player's movement is valid; ie doesn't go off the map or hit a wall.
   *
   * @param p - displacement of the player
   * @return - whether the player's movement is allowed
   */
  private boolean boundaryCheck(Pair<Integer, Integer> p) {
    // displacement
    int xDisplacement = p.first;
    int yDisplacement = p.second;

    // player's current coordinates
    int xPlayer = gameFacade.getPlayer().getPos()[0];
    int yPlayer = gameFacade.getPlayer().getPos()[1];

    // player's new position if valid
    int xNew = xDisplacement + xPlayer;
    int yNew = yDisplacement + yPlayer;

    boolean xCoord = !(xNew > gameFacade.getMaze().getWidth() || xNew < 0);
    boolean yCoord = !(yNew > gameFacade.getMaze().getHeight() || yNew < 0);

    // Check if new position is a wall; if it's not a wall then movement is valid
    boolean is_wall = false;
    if (xCoord && yCoord) {
      Cell c = gameFacade.getMaze().getCell(xNew, yNew);
      is_wall = (c == Cell.WALL);
    }

    return xCoord && yCoord && !is_wall;
  }

  /** Convert movement enums into vectors used to update the player's position. */
  private void buildMovementMap() {
    movementMap.put(Movement.AFK, new Pair<>(0, 0));
    movementMap.put(Movement.DOWN, new Pair<>(0, 1));
    movementMap.put(Movement.UP, new Pair<>(0, -1));
    movementMap.put(Movement.LEFT, new Pair<>(-1, 0));
    movementMap.put(Movement.RIGHT, new Pair<>(1, 0));
  }

  /**
   * Updates the player's position in the model given the movement enum.
   *
   * @param o - movement enum corresponding with the user input.
   */
  @Override
  public void update(Observable observable, Object o) {
    updateModel((Movement) o);
  }
}
