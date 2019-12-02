package com.example.game.MazeGame.DataStructures;

import android.util.Pair;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/** Represents the maze of the game. */
public class Maze implements Observer {
  /** Cell object representation of the maze */
  private Cell[][] grid;

  /** Width of the maze. */
  private int width;

  /** Height of the maze. */
  private int height;

  /** Coordinates of the exit; x coordinate is stored in exit[0], y exit[1]. */
  private int[] exit;

  /** X and Y position of the Player. Represented in the Maze as [playerPosY][PlayerPosX]. */
  private int playerPosX, playerPosY;

  /**
   * Constructs a Maze object with given height and width. Player is inserted into the maze at
   * playerStartX and playerStartY.
   *
   * @param width - width of maze has to be geq 7 has to be odd; number of cells in x direction;
   *     columns
   * @param height - height of maze has to be geq 7 has to be odd; number of cells in y direction;
   * @param playerStartX - the x starting position of the player
   * @param playerStartY - the y starting position of the player
   */
  public Maze(int width, int height, int playerStartX, int playerStartY) {
    this.width = width;
    this.height = height;
    this.playerPosX = playerStartX;
    this.playerPosY = playerStartY;
    grid = new Cell[height][width];
    generateMaze();
    grid[playerPosY][playerPosX] =
            Cell.PLAYER; // Set player's starting position after generating the maze.
  }

  /**
   * Getter for the width of the maze.
   *
   * @return - maze width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Getter for the height of the maze.
   *
   * @return - maze height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Returns the cell at the given coordinate.
   *
   * @param x - the x coordinate
   * @param y - the y coordinate
   * @return - cell at the coordinate in the maze
   * @throws IndexOutOfBoundsException - if coordinate exceeds the maze's dimensions
   */
  public Cell getCell(int x, int y) throws IndexOutOfBoundsException {
    return grid[y][x];
  }

  /** Creates the maze. */
  private void generateMaze() {
    initNodes();
    mst();
    generateExit();
  }

  /**
   * Returns a String representation of the maze.
   *
   * @return string representation of the maze.
   */
  @Override
  @NonNull
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (grid[i][j] == Cell.WALL) {
          s.append("| ");
        } else if (grid[i][j] == Cell.FLOOR) {
          s.append("* ");
        }
      }
      s.append("\n");
    }

    return s.toString();
  }

  /**
   * Returns a deep copy of the grid representing the maze.
   *
   * @return deep copy of the grid representation of the maze
   */
  public Cell[][] getGridDeepCopy() {
    Cell[][] tmp = new Cell[height][width];
    for (int i = 0; i < height; i++) {
      tmp[i] = grid[i].clone();
    }
    return tmp;
  }

  /** Initialize the floor and wall "nodes" of the maze. */
  private void initNodes() {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (row % 2 == 1 && col % 2 == 1) {
          grid[row][col] = Cell.FLOOR;
        } else {
          grid[row][col] = Cell.WALL;
        }
      }
    }
  }

  /** Generates the maze using prim's algorithim for MST (minimum spanning tree). */
  private void mst() {
    // pick a random point on the map that is Cell.FLOOR
    int row = ((((int) (Math.random() * (height - 2))) / 2) * 2) + 1;
    int col = ((((int) (Math.random() * (width - 2))) / 2) * 2) + 1;
    Pair<Integer, Integer> randomCell = new Pair<>(row, col);

    // Find the neighbours of this cell; this store coordinates as keys
    ArrayList<Pair<Integer, Integer>> neighboursUnprocessed = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> processed = new ArrayList<>();
    processed.add(randomCell);
    addNeighbours(randomCell, neighboursUnprocessed, processed);

    while (!neighboursUnprocessed.isEmpty()) {
      int randomNeighbourIndex = (int) (Math.random() * (neighboursUnprocessed.size() - 1));
      // Pick a random neighbour that has not been processed
      Pair<Integer, Integer> randomNeighbourUnprocessed =
              neighboursUnprocessed.get(randomNeighbourIndex);
      // get the neighbours of random neighbour that have been processed
      ArrayList<Pair<Integer, Integer>> pickedNodes =
              getPickedNodes(randomNeighbourUnprocessed, processed);
      int randomProcessedNodeIndex = (int) (Math.random() * (pickedNodes.size() - 1));
      // pick a random neighbour that has been processed
      Pair<Integer, Integer> randomProcessedNode = pickedNodes.get(randomProcessedNodeIndex);
      // Make the cell between these two nodes a floor
      int randomProcessedNodeRow = randomProcessedNode.first;
      int randomProcessedNodeCol = randomProcessedNode.second;
      int randomNeighbourUnprocessedRow = randomNeighbourUnprocessed.first;
      int randomNeighbourUnprocessedCol = randomNeighbourUnprocessed.second;

      grid[(randomProcessedNodeRow + randomNeighbourUnprocessedRow) / 2][
              (randomProcessedNodeCol + randomNeighbourUnprocessedCol) / 2] =
              Cell.FLOOR;

      // Add the random neighbour we picked into processed and remove it from neighboursUnprocessed
      processed.add(randomNeighbourUnprocessed);
      neighboursUnprocessed.remove(randomNeighbourUnprocessed);
      addNeighbours(randomNeighbourUnprocessed, neighboursUnprocessed, processed);
    }
  }

  /** Returns the neighbours of a node where it has already been examined by the algorithm */
  private ArrayList<Pair<Integer, Integer>> getPickedNodes(
          Pair<Integer, Integer> neighbourNode, ArrayList<Pair<Integer, Integer>> processed) {
    ArrayList<Pair<Integer, Integer>> neighbours = getValidNeighbours(neighbourNode);
    ArrayList<Pair<Integer, Integer>> picked = new ArrayList<>();
    for (Pair<Integer, Integer> neighbour : neighbours) {
      if (processed.contains(neighbour)) {
        picked.add(neighbour);
      }
    }

    return picked;
  }

  /**
   * The neighbours of a given node that have not been processed by the algorithm
   *
   * @param cellCoordinate cell coordinate that is being currently processed
   * @param neighboursUnprocessed Array list to mutate to add in the neighbours that have not been
   *     processed
   * @param processed the neighbours of the cell that have been processed
   */
  private void addNeighbours(
          Pair<Integer, Integer> cellCoordinate,
          ArrayList<Pair<Integer, Integer>> neighboursUnprocessed,
          ArrayList<Pair<Integer, Integer>> processed) {
    ArrayList<Pair<Integer, Integer>> neighbours = getValidNeighbours(cellCoordinate);

    for (Pair<Integer, Integer> neighbour : neighbours) {
      if (!neighboursUnprocessed.contains(neighbour) && !processed.contains(neighbour)) {
        neighboursUnprocessed.add(neighbour);
      }
    }
  }

  /**
   * Returns the possible neighbouring nodes of a given cell.
   *
   * @param cellCoordinate the cell coordinate of the cell that is being processed
   */
  private ArrayList<Pair<Integer, Integer>> getValidNeighbours(
          Pair<Integer, Integer> cellCoordinate) {
    int row = cellCoordinate.first;
    int col = cellCoordinate.second;
    Pair<Integer, Integer> leftCellCoordinate,
            rightCellCoordinate,
            topCellCoordinate,
            bottomCellCoordinate;
    ArrayList<Pair<Integer, Integer>> validNeighbours = new ArrayList<>();
    if (col - 2 > 0) {
      leftCellCoordinate = new Pair<>(row, col - 2);
      validNeighbours.add(leftCellCoordinate);
    }
    if (col + 2 < width) {
      rightCellCoordinate = new Pair<>(row, col + 2);
      validNeighbours.add(rightCellCoordinate);
    }
    if (row - 2 > 0) {
      topCellCoordinate = new Pair<>(row - 2, col);
      validNeighbours.add(topCellCoordinate);
    }
    if (row + 2 < height) {
      bottomCellCoordinate = new Pair<>(row + 2, col);
      validNeighbours.add(bottomCellCoordinate);
    }
    return validNeighbours;
  }

  /** Generates an exit in the top left corner, top right corner, or bottom right corner. */
  private void generateExit() {
    String[] choices = new String[] {"topLeftCorner", "topRightCorner", "bottomRightCorner"};
    // Pick a random corner.
    Collections.shuffle(Arrays.asList(choices));
    String exit = choices[0];
    switch (exit) {
      case "topLeftCorner":
        grid[0][1] = Cell.EXIT;
        this.exit = new int[] {0, 1};
        break;
      case "topRightCorner":
        grid[0][width - 2] = Cell.EXIT;
        this.exit = new int[] {0, width - 2};
        break;
      case "bottomRightCorner":
        grid[height - 1][width - 2] = Cell.EXIT;
        this.exit = new int[] {height - 1, width - 2};
        break;
    }
  }

  /**
   * Returns whether the player has escaped the maze.
   *
   * @return - whether player is at the exit
   */
  public boolean hasEscaped() {
    return (playerPosY == exit[0]) && (playerPosX == exit[1]);
  }

  /** Process the player's new position in the maze and update the model accordingly. */
  @Override
  public void update(Observable observable, Object o) {
    // Remove the player's tile from their previous s position in the maze
    grid[playerPosY][playerPosX] = Cell.FLOOR;
    playerPosX = ((Player) observable).getPos()[0];
    playerPosY = ((Player) observable).getPos()[1];
    // Set the player's tile in their updated position in the maze
    grid[playerPosY][playerPosX] = hasEscaped() ? Cell.PLAYER_AT_EXIT : Cell.PLAYER;
  }

  /**
   * Insert the items of the given set of collectibles into the maze representation. Format of an
   * element of the set: "%d,%d" where the first and second %d's are the row/column number of the
   * maze respectively.
   *
   * @param collectiblesPos - set of collectible positions, each formatted as described above
   */
  public void addCollectibles(Set<String> collectiblesPos) {
    String[] tmp;
    for (String s : collectiblesPos) {
      tmp = s.split(",");
      int row = Integer.parseInt(tmp[0]);
      int col = Integer.parseInt(tmp[1]);
      grid[row][col] = Cell.COLLECTIBLE;
    }
  }
}