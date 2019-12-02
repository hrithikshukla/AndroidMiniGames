package com.example.game.TilesGame;

import android.graphics.Canvas;

/** A board interface. */
interface Board {

  /** Get the score of this board. */
  int getScore();

  /** Return whether this board has started game. */
  boolean isGameStart();

  /** Set this board to start game. */
  void setGameStart(boolean gameStart);

  /** Return whether this board has ended game. */
  boolean isGameEnd();

  /** Create the starting items on this board. */
  void createBoardItems();

  /** Update this board. */
  void update();

  /**
   * Draw the items of this board on canvas.
   *
   * @param canvas: the canvas to draw board items on.
   */
  void draw(Canvas canvas);

  /**
   * Mark the tile at location (x, y) as touched.
   *
   * @param x: the x-coordinate of the touched tile.
   * @param y: the y-coordinate of the touched tile.
   */
  void touchTile(float x, float y);

  /**
   * Set the colors of this board.
   *
   * @param colorDangerTile: the integer representation of the colour of a danger tile.
   * @param colorKeyTile: the integer representation of the colour of a key tile.
   * @param colorTouch: the integer representation of the colour of a visited key tile.
   * @param colorLose: the integer representation of the colour of a tile that ends the game.
   */
  void setColors(int colorDangerTile, int colorKeyTile, int colorTouch, int colorLose);
}
