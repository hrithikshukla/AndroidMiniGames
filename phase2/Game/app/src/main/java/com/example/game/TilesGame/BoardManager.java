package com.example.game.TilesGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.game.ScoreManager;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

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

//  /** The score of this game. */
//  private Integer score = 0;

  /** A boolean representing whether the game has started. */
  private boolean gameStart = false;

  /** A boolean representing whether the game has ended. */
  private boolean gameEnd = false;

  private ScoreManager scoreManager;

  /** Construct a board manager. */
  BoardManager(Context context) {
    scoreManager = new ScoreManager(context.getSharedPreferences("highScores", MODE_PRIVATE));
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public int getScore() {
    return scoreManager.getScore();
  }

  boolean isGameStart() {
    return gameStart;
  }

  void setGameStart(boolean gameStart) {
    this.gameStart = gameStart;
  }

  boolean isGameEnd() {
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
      // Populate top of board with new tiles and remove tiles that have passed bottom of board.
      populate();
    }
  }

  /** Draw the items in a board. */
  void draw(Canvas canvas) {
    // Draw tiles.
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        tile.draw(canvas);
      }
    }
    // Draw score.
    Paint paint = new Paint();
    paint.setTypeface(Typeface.DEFAULT_BOLD);
    paint.setTextSize(80);
    paint.setColor(Color.MAGENTA);
    canvas.drawText(scoreManager.getScore() + "", (2 * tileWidth - 35), 150, paint);
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
            && (tile.getY() <= y && y <= (tile.getY() + tileHeight))) // If this tile was touched
        if (!tile.isTouch()) { // If tile has not already been touched.
            tile.setTouch(true);
            if (tile instanceof KeyTile) {
              scoreManager.addScore("tiles"); // Increment score by one (only of tile is a KeyTile).
            }
          }
      }
    }
  }

  /** Check if the game is going to end. * */
  private boolean doesGameEnd() {
    for (ArrayList<Tile> tileRow : tileBoard) {
      for (Tile tile : tileRow) {
        if (tile instanceof DangerTile && tile.touch) { // Check if a danger tile has been touched.
          return true;
        }
        if (tile instanceof KeyTile && !tile.touch && tile.getY() >= boardHeight - 80) {
          ((KeyTile) tile).setMissed(true);
          return true;
        }
      }
    }
    return false;
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
