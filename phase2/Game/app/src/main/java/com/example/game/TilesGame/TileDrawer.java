package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A tile drawer. */
class TileDrawer {

  /** The width to draw a tile. */
  private int tileWidth;

  /** The height to draw a tile. */
  private int tileHeight;

  /** The integer representation of the colour of a danger tile. */
  private int colorDangerTile;

  /** The integer representation of the colour of a key tile. */
  private int colorKeyTile;

  /** The integer representation of the colour of a visited key tile. */
  private int colorTouch;

  /** The integer representation of the colour of a tile that ends the game. */
  private int colorLose;

  /** Paint to draw a tile. */
  private Paint paintRect = new Paint();

  /**
   * Construct a tile drawer.
   *
   * @param tileWidth: the width to draw a tile.
   * @param tileHeight: the height to draw a tile.
   */
  TileDrawer(int tileWidth, int tileHeight) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
  }

  /**
   * Draw tile onto canvas.
   *
   * @param canvas: the canvas to draw on.
   * @param tile: the tile to draw.
   */
  void draw(Canvas canvas, Tile tile) {
    if (tile instanceof KeyTile) {
      drawKeyTile(canvas, (KeyTile) tile);
    } else {
      drawDangerTile(canvas, (DangerTile) tile);
    }
  }

  /**
   * Draw key tile onto canvas.
   *
   * @param canvas: the canvas to draw on.
   * @param tile: the key tile to draw.
   */
  private void drawKeyTile(Canvas canvas, KeyTile tile) {
    // Set brush for fill of tile.
    paintRect.setStyle(Paint.Style.FILL);
    if (tile.isMissed()) { // If tile has been missed (has left the board):
      paintRect.setColor(colorLose);
    } else if (!tile.isTouch()) { // If tile has not been touched (has not left the board):
      paintRect.setColor(colorKeyTile);
    } else { // If tile has been touched:
      paintRect.setColor(colorTouch);
    }
    // Draw the fill of tile.
    canvas.drawRect(
        tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
    // Draw the border of tile.
    drawBorder(canvas, tile);
  }

  /**
   * Draw danger tile onto canvas.
   *
   * @param canvas: the canvas to draw on.
   * @param tile: the danger tile to draw.
   */
  private void drawDangerTile(Canvas canvas, DangerTile tile) {
    // Set brush for fill of tile.
    paintRect.setStyle(Paint.Style.FILL);
    if (!tile.isTouch()) { // If tile has not been touched:
      paintRect.setColor(colorDangerTile);
    } else { // If tile has been touched:
      paintRect.setColor(colorLose);
    }
    // Draw the fill of tile.
    canvas.drawRect(
        tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
    // Draw the border of tile.
    drawBorder(canvas, tile);
  }

  /**
   * Draw tile's border onto canvas.
   *
   * @param canvas: the canvas to draw on.
   * @param tile: the tile of the border to draw.
   */
  private void drawBorder(Canvas canvas, Tile tile) {
    // Set brush for border of tile.
    paintRect.setStyle(Paint.Style.STROKE);
    paintRect.setColor(Color.rgb(200, 200, 200)); // LIGHT GRAY
    // Draw the border of tile.
    canvas.drawRect(
        tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
  }

  /**
   * Set the colors of this tile drawer.
   *
   * @param colorDangerTile: the integer representation of the colour of a danger tile.
   * @param colorKeyTile: the integer representation of the colour of a key tile.
   * @param colorTouch: the integer representation of the colour of a visited key tile.
   * @param colorLose: the integer representation of the colour of a tile that ends the game.
   */
  void setColors(int colorDangerTile, int colorKeyTile, int colorTouch, int colorLose) {
    this.colorDangerTile = colorDangerTile;
    this.colorKeyTile = colorKeyTile;
    this.colorTouch = colorTouch;
    this.colorLose = colorLose;
  }
}
