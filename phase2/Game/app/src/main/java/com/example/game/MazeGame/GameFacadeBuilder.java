package com.example.game.MazeGame;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Collectable;
import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.Player;
import com.example.game.MazeGame.DataStructures.Score;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/** Class to construct a GameFacade object. */
public class GameFacadeBuilder implements Builder {

  // Result to be returned
  private GameFacade gameFacade;

  private int startX, startY, startingScore, mazeWidth, mazeHeight;

  /**
   * Constructor for GameFacadeBuilder.
   *
   * @param startX - starting x position of the player
   * @param startY - starting y position of the player
   * @param startingScore - starting score of the player
   * @param mazeWidth - width of the maze
   * @param mazeHeight - height of the maze
   */
  GameFacadeBuilder(int startX, int startY, int startingScore, int mazeWidth, int mazeHeight) {
    this.startX = startX;
    this.startY = startY;
    this.startingScore = startingScore;
    this.mazeWidth = mazeWidth;
    this.mazeHeight = mazeHeight;
  }

  @Override
  public void build() {
    Score score = new Score(startingScore);
    Maze maze = new Maze(mazeWidth, mazeHeight, startX, startY);
    Player player = new Player(startX, startY, score);

    // Build Collectables object and insert collectable item tiles into the maze.
    HashMap<String, Integer> collectablesHashMap = buildCollectablesHashMap(maze.getGridDeepCopy());
    Collectable collectable = new Collectable(collectablesHashMap);
    maze.addCollectable(collectablesHashMap.keySet());

    // Maze observes player to update the maze when the player moves.
    player.addObserver(maze);
    player.addObserver(collectable);
    collectable.addObserver(score);

    gameFacade = new GameFacade(player, maze);
  }

  /**
   * Randomly generates collectable items in the maze with random values.
   *
   * @param grid - the 2D cell reperesentation of the maze
   * @return - a HashMap where the key is the position of the collectable as a String, and the value
   *     is the value of the collectable item at that position.
   */
  private HashMap<String, Integer> buildCollectablesHashMap(Cell[][] grid) {
    HashMap<String, Integer> tmp = new HashMap<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        // 1 in 25 chance of generating a collectable at every empty square in the maze
        if (grid[i][j] == Cell.FLOOR && ThreadLocalRandom.current().nextInt(1, 25 + 1) == 1) {
          // Value of the collectable is between 5 and 10.
          tmp.put(String.format("%d,%d", i, j), ThreadLocalRandom.current().nextInt(5, 10 + 1));
        }
      }
    }
    return tmp;
  }

  /**
   * Returns the constructed GameFacade object.
   *
   * @return a constructed GameFacade object
   */
  GameFacade getGameFacade() {
    return gameFacade;
  }
}
