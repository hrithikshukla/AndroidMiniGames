package com.example.game.MazeGame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Observable;
import java.util.Observer;

/** View responsible for drawing the game on the screen. */
public class VisualView implements Observer {

  private Cell[][] grid; // Representation of the Maze grid object.
  private Paint paint;
  private Tile tile;
  private Background background;
  private SurfaceHolder surfaceHolder;

  // Maximum x and y positions of the screen.
  private int maxScreenX, maxscreenY;

  /**
   * @param maxScreenX - maximum x position of the screen
   * @param maxScreenY - maximum y position of the screen
   */
  public VisualView(
      int maxScreenX, int maxScreenY, Resources resources, SurfaceHolder surfaceHolder) {
    this.maxScreenX = maxScreenX;
    this.maxscreenY = maxScreenY;

    this.tile = new Tile(resources);
    this.background = new Background(maxScreenX, maxScreenY, resources);

    this.surfaceHolder = surfaceHolder;

    this.paint = new Paint();
  }

  /** Draws the Tiles of the Maze. */
  void draw() {

    if (surfaceHolder.getSurface().isValid()) {

      Canvas canvas = surfaceHolder.lockCanvas();

      // Draw the background first.
      canvas.drawBitmap(background.getBackground(), background.getX(), background.getY(), paint);

      // Maze is drawn so that it is centered on the screen.

      // Following two lines represents the x and y position of the first Tile of the Maze,
      // i.e. grid[0][0].
      int topLeftTileX = (maxScreenX - grid[0].length * tile.getSideLength()) / 2;
      int topLeftTileY = (maxscreenY - grid.length * tile.getSideLength()) / 2;

      // Draw every Tile of the Maze. An offset of a Tile's side length ensures that each
      // Tile is drawn right next to each other.
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          canvas.drawBitmap(
              tile.getTile(grid[i][j]),
              topLeftTileX + (j * tile.getSideLength()),
              topLeftTileY + (i * tile.getSideLength()),
              paint);
        }
      }

      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  /** @param o most recent representation of the Maze. */
  @Override
  public void update(Observable observable, Object o) {
    this.grid = (Cell[][]) o;
  }
}
