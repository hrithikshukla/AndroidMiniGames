package com.example.game.TilesGame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

class BoardInvert extends BoardManager {

  /** Construct an invert board manager. */
  BoardInvert(Context context) {
    super(context);
  }

  /** Update the items in a board. */
  @Override
  public void update() {
    if (gameStart) { // No changes to items on board will occur if game has not started.
      // Check if the game will end this turn.
      if (doesGameEnd()) {
        gameEnd = true;
        return; // No changes to items on board will occur if game has ended.
      }
      // Move all the tiles on this board, incrementing the speed by 50 every 15 points.
      int increment = Math.floorDiv(scoreManager.getScore(), 15);
      for (ArrayList<Tile> tileRow : tileBoard) {
        for (Tile tile : tileRow) {
          tile.move(100 + (increment * 50));
        }
      }
      // Invert the first 4 rows in board (random chance of occurrence).
      Random rand = new Random(); // Create a random variable.
      if (rand.nextInt(15) < 1) { // Invert the board (1/15 probability of occurrence).
        invertBoard();
      }
      // Populate top of board with new tiles and remove tiles that have passed bottom of board.
      populate();
    }
  }

  /** Invert the tiles on this board. */
  private void invertBoard() {
    for (int i = 0; i < 4; i++) { // For first three rows in board:
      ArrayList<Tile> tileRow = tileBoard.get(i); // Get row.
      for (int j = 0; j < 4; j++) {
        Tile thisTile = tileRow.get(j);
        if (!thisTile.isTouch()) { // If tile has not been touched:
          // Invert tile.
          Tile newTile = invertTile(thisTile);
          tileRow.set(j, newTile);
        }
      }
    }
  }

  /** Return an inverted tile of thisTile. */
  private Tile invertTile(Tile thisTile) {
    if (thisTile instanceof KeyTile) { // If thisTile is KeyTile:
      // Return a new DangerTile at the location of thisTile
      return tileFactory.getDangerTile(thisTile.getX(), thisTile.getY());
    } else { // If thisTile is DangerTile:
      // Return a new KeyTile at the location of thisTile
      return tileFactory.getKeyTile(thisTile.getX(), thisTile.getY());
    }
  }
}
