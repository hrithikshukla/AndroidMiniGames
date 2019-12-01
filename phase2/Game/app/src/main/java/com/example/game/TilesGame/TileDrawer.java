package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.R;

class TileDrawer {

  /** The width of a tile to be drawn. */
  private int tileWidth;

  /** The height of a tile to be drawn. */
  private int tileHeight;

  private int colorDangerTile;
  private int colorKeyTile;
  private int colorTouch;
  private int colorLose;

  private Paint paintRect = new Paint();

  /** Construct a tile drawer. */
  TileDrawer(int tileWidth, int tileHeight) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
  }

  /**
   * Draw the tile onto canvas.
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

  /** A helper method to draw a key tile. */
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

  /** A helper method to draw a danger tile. */
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

  /** A helper method to draw a tile border. */
  private void drawBorder(Canvas canvas, Tile tile) {
    // Set brush for border of tile.
    paintRect.setStyle(Paint.Style.STROKE);
    paintRect.setColor(Color.rgb(200, 200, 200)); // LIGHT GRAY
    // Draw the border of tile.
    canvas.drawRect(
        tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
  }

  void setColors(int colorDangerTile, int colorKeyTile, int colorTouch, int colorLose) {
    this.colorDangerTile = colorDangerTile;
    this.colorKeyTile = colorKeyTile;
    this.colorTouch = colorTouch;
    this.colorLose = colorLose;
  }
}
