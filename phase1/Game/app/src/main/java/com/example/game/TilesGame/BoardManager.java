package com.example.game.TilesGame;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

class BoardManager extends ClassLoader {

  /** The width of a tile. */
  private int tileWidth = Tile.getWidth();

  /** The height of a tile. */
  private int tileHeight = Tile.getHeight();

  /** The width of this board. */
  private int boardWidth = 4 * tileWidth;

  /** The height of this board. */
  private int boardHeight = 4 * tileHeight;

  /** A list of all tiles on this board. */
  private ArrayList<ArrayList<Tile>> tileBoard = new ArrayList<>();

  /** A boolean representing whether the game has started. */
  private boolean gameStart = false;

  /** A boolean representing whether the game has ended. */
  private boolean gameEnd = false;

  /** Construct a board manager. */
  BoardManager() {}

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public boolean isGameStart() {
    return gameStart;
  }

  public void setGameStart(boolean gameStart) {
    this.gameStart = gameStart;
  }

  public boolean isGameEnd() {
    return gameEnd;
  }

  /** Create the starting items in a board. */
  void createBoardItems() {
    // Add five arrays to tileBoard to represent the five onscreen rows of tiles.
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());

    Random ran = new Random(); // Use a random variable to randomize the key tile in each row.

    // Fill first five rows with both danger tiles and key tiles.
    for (int i = 0; i < 5; i++) {
      ArrayList<Tile> tileRow = tileBoard.get(i);
      Integer keyTileIndex = ran.nextInt(4);
      for (int j = 0; j < 4; j++) {
        if (keyTileIndex.equals(j)) {
          tileRow.add(new KeyTile(j * tileWidth, (i - 2) * tileHeight));
        } else {
          tileRow.add(new DangerTile(j * tileWidth, (i - 2) * tileHeight));
        }
      }
    }
    // Fill last row with danger tiles.
    ArrayList<Tile> tileRow = tileBoard.get(5);
    for (int j = 0; j < 4; j++) {
      tileRow.add(new DangerTile(j * tileWidth, 3 * tileHeight));
    }
  }

  /** Update the items in a board. */
  void update() {
    if (gameStart) {
      // Check if the game will end this turn.
      //  if (doesGameEnd()) {
      //    gameEnd = true;
      //    return;
      //  }
      // Move all the tiles on this board.
      for (ArrayList<Tile> tileRow : tileBoard) {
        for (Tile tile : tileRow) {
          tile.move(100);
        }
      }
      // Populate top of board with new tiles and remove tiles that have passed bottom of board.
      populate();
    }
  }

  /** Draw the items in a board. */
  void draw(Canvas canvas) {
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        tile.draw(canvas);
      }
    }
  }

  /**
   * Mark the tile at location (x, y) as touched.
   *
   * @param x: the x-coordinate of the touched tile.
   * @param y: the y-coordinate of the touched tile.
   */
  void touchTile(float x, float y) {
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        if ((tile.getX() <= x && x <= (tile.getX() + tileWidth))
            && (tile.getY() <= y && y <= (tile.getY() + tileHeight))) tile.setTouch(true);
      }
    }
  }

  /**
   * Populate top of board with a new row of tiles once the bottom row of tiles has passed the
   * bottom of the board. *
   */
  private void populate() {
    if (tileBoard.get(5).get(0).getY()
        >= boardHeight) { // If the last row of tiles has passed the bottom.
      // Move the first five rows of tiles one spot to the right in tileBoard array.
      for (int i = 5; i > 0; i--) {
        tileBoard.set(i, tileBoard.get(i - 1));
      }

      // Add a new row of tiles to the top of the board.
      ArrayList<Tile> newTileRow = new ArrayList<>();
      tileBoard.set(0, newTileRow);
      int newTileY = tileBoard.get(1).get(0).y - tileHeight;

      // Use a random variable to randomize the key tile in new row.
      Random ran = new Random();
      Integer keyTileIndex = ran.nextInt(4);

      // Fill new row with both danger tiles and key tiles.
      for (int j = 0; j < 4; j++) {
        if (keyTileIndex.equals(j)) {
          newTileRow.add(new KeyTile(j * tileWidth, newTileY));
        } else {
          newTileRow.add(new DangerTile(j * tileWidth, newTileY));
        }
      }
    }
  }
}
