package com.example.game.TilesGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

class Board4By4 extends BoardManager {

  /** The width of a tile. */
  int tileWidth = Tile.getWidth4By4();

  /** The height of a tile. */
  int tileHeight = Tile.getHeight4By4();

  /** The width of this board. */
  private int boardWidth = 4 * tileWidth;

  /** The height of this board. */
  private int boardHeight = 4 * tileHeight;

  /** Construct a 4X4 board manager. */
  Board4By4(Context context) {
    super(context);
  }

  /** Create the starting items in a board. */
  void createBoardItems() {
    // Add a hidden row of tiles above board to appear when game starts.
    tileBoard.add(new ArrayList<Tile>());

    // Add five arrays to tileBoard to represent the five onscreen rows of tiles.
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

  /**
   * Populate top of board with a new row of tiles once the bottom row of tiles has passed the
   * bottom of the board. *
   */
  void populate() {
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
