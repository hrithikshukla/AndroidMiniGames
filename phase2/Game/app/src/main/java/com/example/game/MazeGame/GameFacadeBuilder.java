package com.example.game.MazeGame;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Collectible;
import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.Player;
import com.example.game.MazeGame.DataStructures.Score;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/** Constructs a GameFacade object of the maze and sets up observer/observable relationships. */
public class GameFacadeBuilder implements Builder {

  /** Finished GameFacade object to be returned */
  private GameFacade gameFacade;

  /** Attributes required for construction of the Player/Maze/Score objects of the GameFacade */
  private int startX, startY, startingScore, mazeWidth, mazeHeight;

  /**
   * Creates a GameFacadeBuilder object with the given attributes.
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

  /**
   * Builds the GameFacade object by constructing the necessary components i.e. Score, Maze, and
   * Player and setting up observers.
   */
  @Override
  public void build() {
    Score score = new Score(startingScore);
    Maze maze = new Maze(mazeWidth, mazeHeight, startX, startY);
    Player player = new Player(startX, startY, score);

    // Build Collectibles object and insert collectible item tiles into the maze.
    HashMap<String, Integer> collectibles = buildCollectibles(maze.getGridDeepCopy(), 5, 10, 4);
    Collectible collectible = new Collectible(collectibles);
    maze.addCollectibles(collectibles.keySet());

    // Assign necessary observers.
    player.addObserver(maze);
    player.addObserver(collectible);
    collectible.addObserver(score);

    gameFacade = new GameFacade(player, maze);
  }

  /**
   * Returns the constructed GameFacade object.
   *
   * @return a constructed GameFacade object
   */
  GameFacade getGameFacade() {
    return gameFacade;
  }

  /**
   * Randomly generates collectible items in the maze with chance percentage for each empty square,
   * and assigned a random value between minVal and maxVal.
   *
   * @param grid - the 2D cell reperesentation of the maze
   * @param minVal - the minimum score value of a collectible
   * @param maxVal - the maximum score value of a collectible
   * @param chance - the chance the a collectible item is generated at a square
   * @return - a HashMap where the key is the position of the item as a String, and the value is the
   *     value of the item at that position.
   */
  private HashMap<String, Integer> buildCollectibles(
      Cell[][] grid, int minVal, int maxVal, int chance) {
    HashMap<String, Integer> tmp = new HashMap<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == Cell.FLOOR && ThreadLocalRandom.current().nextInt(1, 100 + 1) <= chance) {
          tmp.put(
              String.format("%d,%d", i, j),
              ThreadLocalRandom.current().nextInt(minVal, maxVal + 1));
        }
      }
    }
    return tmp;
  }
}
