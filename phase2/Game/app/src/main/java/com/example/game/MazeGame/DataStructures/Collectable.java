package com.example.game.MazeGame.DataStructures;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/** Represents all collectable score affecting items in a maze. */
public class Collectable extends Observable implements Observer {

  private HashMap<String, Integer> collectables;

  public Collectable(HashMap<String, Integer> collectables) {
    this.collectables = collectables;
  }

  @Override
  /**
   * Collectable is observing the Player object. Compares the player's most recent position with the
   * position of all the collectables of the maze. Removes collectable from maze if so and send its
   * value to Score to update it.
   */
  public void update(Observable observable, Object o) {
    String pos = getStringPos(((Player) observable).getPos());
    if (collectables.containsKey(pos)) {
      setChanged();
      notifyObservers(collectables.get(pos));
      collectables.remove(pos);
    }
  }

  /**
   * Returns a formatted string representation of a [x,y] position. Format of the string is "%y,%x"
   * because of how the player's position is interpreted in the maze.
   *
   * @param arr - an x,y position represented as a 2D array
   */
  private String getStringPos(int[] arr) {
    return String.format("%d,%d", arr[1], arr[0]);
  }
}
