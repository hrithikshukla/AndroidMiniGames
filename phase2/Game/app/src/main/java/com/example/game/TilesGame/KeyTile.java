package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** A key tile. */
class KeyTile extends Tile {

  /** A boolean to represent whether this tile has been missed on a board. */
  private boolean missed = false;

  /**
   * Construct a key tile at location (x, y).
   *
   * @param x: the x-coordinate of this tile.
   * @param y: the y-coordinate of this tile.
   */
  KeyTile(int x, int y) {
    super(x, y);
  }

  public void setMissed(boolean missed) {
    this.missed = missed;
  }

  @Override
  void draw(Canvas canvas) {
    // Fill tile.
    paintRect.setStyle(Paint.Style.FILL);
    if (missed) {
      paintRect.setColor(Color.RED);
    } else if (!touch) {
      paintRect.setColor(Color.BLACK);
    } else {
      paintRect.setColor(Color.rgb(200, 200, 200));
    }
    canvas.drawRect(x, y, x + width, y + height, paintRect);

    super.draw(canvas); // Draw border of tile.
  }
}
