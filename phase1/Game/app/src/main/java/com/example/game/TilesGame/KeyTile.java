package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A key tile. */
class KeyTile extends Tile {

  /**
   * Construct a key tile at location (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  KeyTile(int x, int y) {
    super(x, y);
  }

  @Override
  void draw(Canvas canvas) {
    // Fill tile.
    paintRect.setStyle(Paint.Style.FILL);
    if (!touch) {
      paintRect.setColor(Color.BLACK);
    } else {
      paintRect.setColor(Color.rgb(200, 200, 200));
    }
    canvas.drawRect(x, y, x + width, y + height, paintRect);

    super.draw(canvas); // Draw border of tile.
  }
}
