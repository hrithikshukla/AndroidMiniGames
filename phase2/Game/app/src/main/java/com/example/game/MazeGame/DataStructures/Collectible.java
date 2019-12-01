package com.example.game.MazeGame.DataStructures;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents collectible score affecting items in the Maze Game. Collectible observes Player to see
 * if the player has picked up a collectible i.e. player's position is at a collectible's position.
 * Whenever Collectible changes i.e. removing a collectible item from play, it notifies its
 * observers of the position of this removed item.
 */
public class Collectible extends Observable implements Observer {

  /**
   * HashMap of collectible items. Key is a string representation of a position in the maze, and the
   * value is the value of the collectible at that position.
   */
  private HashMap<String, Integer> collectibles;

  /**
   * Creates a new Collectible object with given collectible items.
   *
   * @param collectibles - collectible items in the maze
   */
  public Collectible(HashMap<String, Integer> collectibles) {
    this.collectibles = collectibles;
  }

  /**
   * Removes the collectible item if the player has picked one up and notifies Collectible's
   * observers of the removed item's position.
   *
   * @param observable - Player object
   */
  @Override
  public void update(Observable observable, Object o) {
    String pos = getStringPos(((Player) observable).getPos());
    if (collectibles.containsKey(pos)) {
      setChanged();
      notifyObservers(collectibles.get(pos));
      collectibles.remove(pos);
    }
  }

  /**
   * Returns a formatted string representation of a given position as it appears in the maze. Format
   * of the string is "%y,%x" because of how the position is interpreted in the maze.
   *
   * @param arr - an x,y position represented as a 2D array
   */
  private String getStringPos(int[] arr) {
    return String.format("%d,%d", arr[1], arr[0]);
  }
}
