package com.example.game.MazeGame;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// TODO: fix get cell method, code smells looks awkward
/** Represents the maze of the game. */
public class Maze {
  private Cell[][] grid;
  private int width;
  private int height;

  private int[] exit;

  /**
   * @param width: width of maze has to be geq 7 has to be odd; number of cells in x direction;
   *     columns
   * @param height: height of maze has to be geq 7 has to be odd; number of cells in y direction;
   *     rows
   */
  public Maze(int width, int height) {
    this.width = width;
    this.height = height;
    grid = new Cell[height][width];
    generateMaze();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  Cell getCell(int y, int x) {
    try {
      return grid[y][x];
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    return grid[y][x];
  }

  void generateMaze() {
    initNodes();
    mst();
    generateExit();
  }

  @Override
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

  Cell[][] getGrid() {
    return grid;
  }

  // initialize the floor and wall "nodes" of the maze

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

  /** Returns a deep copy of the grid representing the maze. */
  Cell[][] getGridDeepCopy() {
    Cell[][] tmp = new Cell[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        tmp[i][j] = grid[i][j];
      }
    }
    return tmp;
  }

  // prim's algorithm for MST(minimum spanning tree)
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
        this.exit = new int[] {1, 0};
        break;
      case "topRightCorner":
        grid[0][width - 2] = Cell.EXIT;
        this.exit = new int[] {width - 2, 0};
        break;
      case "bottomRightCorner":
        grid[height - 1][width- 2] = Cell.EXIT;
        this.exit = new int[] {width - 2, height - 1};
        break;
    }
  }

  /** Getter for exit. */
  int[] getExit() {
    return exit;
  }
}
