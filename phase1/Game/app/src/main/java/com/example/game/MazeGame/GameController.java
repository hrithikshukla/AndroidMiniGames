package com.example.game.MazeGame;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
  private GameFacade gameFacade;

  private Map<Movement, Pair<Integer, Integer>> movementMap = new HashMap<>();

  public GameController(GameFacade gameFacade) {
    this.gameFacade = gameFacade;
    buildMovementMap();
  }

  public void updateModel(Movement mov) {
    // Only update the model if the game isn't over i.e. the player hasn't escaped.
    if (!gameFacade.hasPlayerEscaped()){
      Pair<Integer, Integer> movement_vector = movementMap.get(mov);
      if (boundaryCheck(movement_vector)) {
        gameFacade.update(movement_vector);
      }
    }
  }

  // Check if players movement is valid; ie doesn't go off the map or hit a wall
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

    boolean is_wall = false;
    if (xCoord && yCoord) {
      Cell c = gameFacade.getMaze().getCell(yNew, xNew);
      is_wall = (c == Cell.WALL);
    }

    return xCoord && yCoord && !is_wall;
  }

  private void buildMovementMap() {
    movementMap.put(Movement.AFK, new Pair<>(0, 0));
    movementMap.put(Movement.DOWN, new Pair<>(0, 1));
    movementMap.put(Movement.UP, new Pair<>(0, -1));
    movementMap.put(Movement.LEFT, new Pair<>(-1, 0));
    movementMap.put(Movement.RIGHT, new Pair<>(1, 0));
  }

    /**
     *
     * @param o - direction of movement corresponding with the user input.
     */
  @Override
  public void update(Observable observable, Object o) {
    updateModel((Movement) o);
  }
}
