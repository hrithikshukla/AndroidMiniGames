package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class TileDrawer {

    /**
     * The width of a tile to be drawn.
     */
    private int tileWidth;

    /**
     * The height of a tile to be drawn.
     */
    private int tileHeight;

    private Paint paintRect = new Paint();

    /**
     * Construct a tile drawer.
     */
    TileDrawer(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /**
     * Draw the tile onto canvas.
     *
     * @param canvas: the canvas to draw on.
     * @param tile:   the tile to draw.
     */
    void draw(Canvas canvas, Tile tile) {
        if (tile instanceof KeyTile) {
            drawKeyTile(canvas, (KeyTile) tile);
        } else {
            drawDangerTile(canvas, (DangerTile) tile);
        }
    }

    /**
     * A helper method to draw a key tile.
     */
    private void drawKeyTile(Canvas canvas, KeyTile tile) {
        // Set brush for fill of tile.
        paintRect.setStyle(Paint.Style.FILL);
        if (tile.isMissed()) { // If tile has been missed (has left the board):
            paintRect.setColor(Color.RED);
        } else if (!tile.isTouch()) { // If tile has been touched:
            paintRect.setColor(Color.BLACK);
        } else { // If tile has not been touched (has not left the board):
            paintRect.setColor(Color.rgb(200, 200, 200));
        }
        // Draw the fill of tile.
        canvas.drawRect(
                tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
        // Draw the border of tile.
        drawBorder(canvas, tile);
    }

    /**
     * A helper method to draw a danger tile.
     */
    private void drawDangerTile(Canvas canvas, DangerTile tile) {
        // Set brush for fill of tile.
        paintRect.setStyle(Paint.Style.FILL);
        if (!tile.isTouch()) { // If tile has not been touched:
            paintRect.setColor(Color.WHITE);
        } else { // If tile has been touched:
            paintRect.setColor(Color.RED);
        }
        // Draw the fill of tile.
        canvas.drawRect(
                tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
        // Draw the border of tile.
        drawBorder(canvas, tile);
    }

    /**
     * A helper method to draw a tile border.
     */
    private void drawBorder(Canvas canvas, Tile tile) {
        // Set brush for border of tile.
        paintRect.setStyle(Paint.Style.STROKE);
        paintRect.setColor(Color.rgb(200, 200, 200)); // LIGHT GRAY
        // Draw the border of tile.
        canvas.drawRect(
                tile.getX(), tile.getY(), tile.getX() + tileWidth, tile.getY() + tileHeight, paintRect);
    }
}
