package com.example.game.MazeGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.game.MazeGame.DataStructures.Background;
import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.NewGameState;
import com.example.game.R;

import java.util.Observable;
import java.util.Observer;

/** View responsible for drawing the game on the screen. */
public class VisualView implements Observer, Gameover {

  private Cell[][] grid; // Representation of the Maze grid object.

  // Dependency injected in
  private Paint backgroundPaint, textPaint;
  private Tile tile;
  private Background background;
  private SurfaceHolder surfaceHolder;
  private Context context;
  private int maxScreenX, maxScreenY;

  private int score;
  private int numSteps;

  private boolean gameOver;

  /**
   * @param context - the context from a GameView object
   * @param surfaceHolder - the SurfaceHolder from a GameView object
   * @param tile - used for drawing tiles
   * @param background - used for drawing the background
   * @param maxScreenX - maximum x-position of the screen
   * @param maxScreenY - maximum y-position of the screen
   * @param textPaint - Paint object used for drawing text
   * @param backgroundPaint - Paint object used for drawing the background
   */
  VisualView(
      Context context,
      SurfaceHolder surfaceHolder,
      Tile tile,
      Background background,
      int maxScreenX,
      int maxScreenY,
      Paint textPaint,
      Paint backgroundPaint) {

    this.context = context;
    this.surfaceHolder = surfaceHolder;
    this.tile = tile;
    this.background = background;

    this.maxScreenX = maxScreenX;
    this.maxScreenY = maxScreenY;

    this.textPaint = textPaint;
    this.backgroundPaint = backgroundPaint;
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
    int topLeftTileY = (maxScreenY - grid.length * tile.getSideLength()) / 2;

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
