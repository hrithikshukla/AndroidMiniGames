package com.example.game.TilesGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

class Board5By5 extends BoardManager {

  /** The width of a tile. */
  private int tileWidth = TileManager.getWidth5By5();

  /** The height of a tile. */
  private int tileHeight = TileManager.getHeight5By5();

  /** The width of this board. */
  private int boardWidth = 5 * tileWidth;

  /** The height of this board. */
  private int boardHeight = 5 * tileHeight;

  /** Construct a 5X5 board manager. */
  Board5By5(Context context) {
    super(context);
  }

  /** Create the starting items in this board manager. */
  @Override
  public void createBoardItems() {
    // Add a hidden row of tiles above board to appear when game starts.
    tileBoard.add(new ArrayList<Tile>());

    // Add six arrays to tileBoard to represent the six onscreen rows of tiles.
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());
    tileBoard.add(new ArrayList<Tile>());

    Random ran =
        new Random(); // Use a random variable to randomize location of the key tile in each row.

    // Fill first six rows with both danger tiles and key tiles.
    for (int i = 0; i < 6; i++) {
      ArrayList<Tile> tileRow = tileBoard.get(i);
      Integer keyTileIndex = ran.nextInt(5);
      for (int j = 0; j < 5; j++) {
        if (keyTileIndex.equals(j)) {
          tileRow.add(tileFactory.getKeyTile(j * tileWidth, (i - 2) * tileHeight));
        } else {
          tileRow.add(tileFactory.getDangerTile(j * tileWidth, (i - 2) * tileHeight));
        }
      }
    }
    // Fill last row with danger tiles.
    ArrayList<Tile> tileRow = tileBoard.get(6);
    for (int j = 0; j < 5; j++) {
      tileRow.add(tileFactory.getDangerTile(j * tileWidth, 4 * tileHeight));
    }
  }

  /**
   * Mark the tile at location (x, y) as touched.
   *
   * @param x: the x-coordinate of the touched tile.
   * @param y: the y-coordinate of the touched tile.
   */
  @Override
  public void touchTile(float x, float y) {
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        if ((tile.getX() <= x && x <= (tile.getX() + tileWidth))
            && (tile.getY() <= y
                && y <= (tile.getY() + tileHeight))) { // If this tile is the touched tile
          if (!tile.isTouch()) { // If tile has not already been touched.
            tile.setTouch(true);
            if (tile instanceof KeyTile) {
              scoreManager.addScore("tiles"); // Increment score by one (only if tile is a KeyTile).
            }
          }
        }
      }
    }
  }

  /**
   * Populate top of board with a new row of tiles once the bottom row of tiles has passed the
   * bottom of the board. *
   */
  @Override
  void populate() {
    if (tileBoard.get(6).get(0).getY()
        >= boardHeight) { // If the last row of tiles has passed the bottom.
      // Move the first six rows of tiles one spot to the right in tileBoard array.
      for (int i = 6; i > 0; i--) {
        tileBoard.set(i, tileBoard.get(i - 1));
      }

      // Add a new row of tiles to the top of the board.
      ArrayList<Tile> newTileRow = new ArrayList<>();
      tileBoard.set(0, newTileRow);
      int newTileY = tileBoard.get(1).get(0).getY() - tileHeight;

      // Use a random variable to randomize location of the key tile in new row.
      Random ran = new Random();
      Integer keyTileIndex = ran.nextInt(5);

      // Fill new row with both danger tiles and key tiles.
      for (int j = 0; j < 5; j++) {
        if (keyTileIndex.equals(j)) {
          newTileRow.add(tileFactory.getKeyTile(j * tileWidth, newTileY));
        } else {
          newTileRow.add(tileFactory.getDangerTile(j * tileWidth, newTileY));
        }
      }
    }
  }
}
