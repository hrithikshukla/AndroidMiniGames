package com.example.game.TilesGame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/** Use to manage threading and updates. */
public class GameThread extends Thread {

  /** The canvas on which to draw the tile board. */
  private static Canvas canvas;
  /** Where the tile board items are drawn. */
  private GameView gameView;
  /** The canvas container. */
  private SurfaceHolder surfaceHolder;
  /** Whether the thread is running. */
  private boolean isRunning;

  /**
   * Construct a game thread.
   *
   * @param surfaceHolder the canvas container.
   * @param view where the tile board items are drawn.
   */
  GameThread(SurfaceHolder surfaceHolder, GameView view) {
    this.surfaceHolder = surfaceHolder;
    this.gameView = view;
  }

  @Override
  public void run() {
    while (isRunning) {
      canvas = null;

      try {
        canvas = this.surfaceHolder.lockCanvas();
        synchronized (surfaceHolder) {
          this.gameView.update();
          this.gameView.draw(canvas);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (canvas != null) {
          try {
            surfaceHolder.unlockCanvasAndPost(canvas);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      try {
        sleep(300);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    // Go to Game Over page
    GameView.getGameActivity().endTilesGame(gameView);
  }

  /** Set this thread to be running. */
  void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }
}
