package com.example.game.MazeGame;

import android.util.Pair;

import java.util.ArrayList;

/** Represents the maze of the game. */
public class Maze {
  private Cell[][] grid;
  private int width;
  private int height;

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

  void generateMaze() {
    initNodes();
    mst();
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

}
