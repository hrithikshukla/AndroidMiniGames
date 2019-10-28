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
  private int boardWidth;

  /** The height of this board. */
  private int boardHeight;

  /** A list of all tiles on this board. */
  private ArrayList<ArrayList<Tile>> tileBoard = new ArrayList<>();

  /**
   * Construct a board manager with board dimensions width and height.
   *
   * @param width: the width of this board.
   * @param height: the height of this board.
   */
  BoardManager(int width, int height) {
    this.boardWidth = width;
    this.boardHeight = height;
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  /** Create the starting items in a board. */
  void createBoardItems() {
    // Add five arrays to tileBoard to represent the five onscreen rows of tiles.
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());

    Random ran = new Random(); // Use a random variable to randomize the key tile in each row.

    for (int i = 0; i < 5; i++) {
      ArrayList<Tile> tileRow = tileBoard.get(i);
      Integer keyTileIndex = ran.nextInt(4);
      for (int j = 0; j < 4; j++) {
        if (keyTileIndex.equals(j)) {
          tileRow.add(new KeyTile(j * tileWidth, (i - 1) * tileHeight));
        } else {
          tileRow.add(new DangerTile(j * tileWidth, (i - 1) * tileHeight));
        }
      }
    }
  }

  /** Update the items in a board. */
  void update() {
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        tile.move(30);
      }
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
}
