package com.example.game.MazeGame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.game.R;

import java.util.Observable;
import java.util.Observer;

/** View responsible for drawing the game on the screen. */
public class VisualView implements Observer, Gameover {

  private Cell[][] grid; // Representation of the Maze grid object.
  private Paint backgroundPaint, textPaint;
  private Tile tile;
  private Background background;
  private SurfaceHolder surfaceHolder;
  private Context context;

  // Maximum x and y positions of the screen.
  private int maxScreenX, maxscreenY;

  private int score;
  private int numSteps;

  private boolean gameOver;

  /**
   * @param maxScreenX - maximum x position of the screen
   * @param maxScreenY - maximum y position of the screen
   */
  public VisualView(
          int maxScreenX, int maxScreenY, Context context, SurfaceHolder surfaceHolder) {
    this.maxScreenX = maxScreenX;
    this.maxscreenY = maxScreenY;

    this.context = context;
    this.tile = new Tile(context.getResources());
    this.background = new Background(maxScreenX, maxScreenY, context.getResources());

    this.surfaceHolder = surfaceHolder;

    this.backgroundPaint = new Paint();

    // Set colors and size of text.
    this.textPaint = new Paint();
    textPaint.setColor(Color.GREEN);
    textPaint.setTextSize(32);
  }

  /** Draws the UI of the Maze. */
  void draw() {

    if (surfaceHolder.getSurface().isValid()) {

      Canvas canvas = surfaceHolder.lockCanvas();

      // Draw the background first.
      canvas.drawBitmap(
          background.getBackground(), background.getX(), background.getY(), backgroundPaint);
      drawTiles(canvas);
      drawText(canvas);

      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  /** Draws the text of the Maze. */
  private void drawText(Canvas canvas) {
    canvas.drawText(context.getString(R.string.score) + score, maxScreenX - 250, 50, textPaint);
    canvas.drawText(context.getString(R.string.steps) + numSteps, 115, 50, textPaint);
  }

  /** Draws the Tiles of the Maze. */
  private void drawTiles(Canvas canvas) {
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
            backgroundPaint);
      }
    }
  }

  /** @param o most recent representation of the Maze. */
  @Override
  public void update(Observable observable, Object o) {
    NewGameState newGameState = (NewGameState) o;
    grid = newGameState.getGrid();
    score = newGameState.getScore();
    numSteps = newGameState.getNumSteps();
    gameOver = newGameState.isGameOver();
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }
}
