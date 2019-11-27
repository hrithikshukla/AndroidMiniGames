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

    void draw4By4(Canvas canvas) {
        draw(canvas); // Fill tile.
        canvas.drawRect(x, y, x + width4By4, y + height4By4, paintRect);
        draw4By4Border(canvas); // Draw border of tile.
    }

    void draw5By5(Canvas canvas) {
        draw(canvas); // Fill tile.
        canvas.drawRect(x, y, x + width5By5, y + height5By5, paintRect);
        draw5By5Border(canvas); // Draw border of tile.
    }

    private void draw(Canvas canvas) {
        // Set brush to fill tile.
    paintRect.setStyle(Paint.Style.FILL);
    if (missed) {
      paintRect.setColor(Color.RED);
    } else if (!touch) {
      paintRect.setColor(Color.BLACK);
    } else {
      paintRect.setColor(Color.rgb(200, 200, 200));
    }
  }
}
