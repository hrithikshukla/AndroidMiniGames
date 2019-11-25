package com.example.game.TilesGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.game.Activities.main.ScoreManager;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/** An abstract board manager class that should not be instantiated. * */
abstract class BoardManager extends ClassLoader {

  /** The width of a tile. (Default is 4X4) */
  int tileWidth = Tile.getWidth4By4();

  /** The height of a tile. (Default is 4X4) */
  int tileHeight = Tile.getHeight4By4();

  /** The width of this board. (Default is 4X4 board) */
  private int boardWidth = 4 * tileWidth;

  /** The height of this board. (Default is 4X4 board) */
  private int boardHeight = 4 * tileHeight;

  /** A list of all tiles on this board. */
  ArrayList<ArrayList<Tile>> tileBoard = new ArrayList<>();

  /** A boolean representing whether the game has started. */
  private boolean gameStart = false;

  /** A boolean representing whether the game has ended. */
  private boolean gameEnd = false;

  ScoreManager scoreManager;

  /** Construct a board manager. */
  BoardManager(Context context) {
    scoreManager = new ScoreManager(context.getSharedPreferences("highScores", MODE_PRIVATE));
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
  abstract void createBoardItems();

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
        tile.draw4By4(canvas);
      }
    }
    // Draw score.
    drawScore(canvas);
  }

  /** Draw the score. */
  void drawScore(Canvas canvas) {
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
  abstract void populate();
}
