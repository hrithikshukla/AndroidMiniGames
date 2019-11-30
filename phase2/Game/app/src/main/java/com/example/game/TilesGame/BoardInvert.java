package com.example.game.TilesGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

class BoardInvert extends BoardManager {

  /** Construct an invert board manager. */
  BoardInvert(Context context) {
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

  @Override
  /** Update the items in a board. */
  void update() {
    if (gameStart) {
      // Check if the game will end this turn.
      if (doesGameEnd()) {
        gameEnd = true;
        return;
      }
      // Move all the tiles on this board, incrementing the speed by 50 every 15 points.
      int increment = Math.floorDiv(scoreManager.getScore(), 15);
      for (ArrayList<Tile> tileRow : tileBoard) {
        for (Tile tile : tileRow) {
          tile.move(100 + (increment * 50));
        }
      }
      // Invert the first 4 rows in board randomly.
      Random rand = new Random(); // Create a random variable.
      if (rand.nextInt(15) < 1) { // Invert the board (1/15 probability of occurrence).
        invertBoard();
      }
      // Populate top of board with new tiles and remove tiles that have passed bottom of board.
      populate();
    }
  }

  private void invertBoard() {
    for (int i = 0; i < 4; i++) { // For first three rows in board:
      ArrayList<Tile> tileRow = tileBoard.get(i); // Get row.
      for (int j = 0; j < 4; j++) {
        Tile thisTile = tileRow.get(j);
        if (!thisTile.isTouch()) { // If tile has not been touched:
          Tile newTile = invertTile(thisTile);
          tileRow.set(j, newTile);
        }
      }
    }
  }

  private Tile invertTile(Tile thisTile) {
    if (thisTile instanceof KeyTile) { // If thisTile is KeyTile:
      // Return a new DangerTile at the location of thisTile
      return new DangerTile(thisTile.getX(), thisTile.getY());
    } else { // If thisTile is DangerTile:
      // Return a new KeyTile at the location of thisTile
      return new KeyTile(thisTile.getX(), thisTile.getY());
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
