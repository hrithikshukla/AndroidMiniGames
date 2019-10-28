package com.example.game.TilesGame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The width of a character. */
  public static float charWidth;
  /** The height of a character. */
  public static float charHeight;

  /** The tile board contents. */
  private BoardManager boardManager;

  /** The part of the program that manages time. */
  private GameThread thread;

  public GameView(Context context) {
    super(context);
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this); // Instantiate new GameThread.
    setFocusable(true);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Use the letter size and screen height to determine the size of the tile board.
    boardManager = new BoardManager(); // Instantiate new BoardManager.
    boardManager.createBoardItems();

    thread.setRunning(true);
    thread.start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update the board. */
  public void update() {
    boardManager.update();
    if (boardManager.isGameEnd()) {
      thread.setRunning(false);
    }
  }

  /** Draw the board on canvas. */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      boardManager.draw(canvas);
    }
  }

  /** Register touch input in boardManager. */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {

    float x = event.getX();
    float y = event.getY();

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      boardManager.touchTile(x, y);

      if (!boardManager.isGameStart()) {
        boardManager.setGameStart(true);
      }
    }
    return true;
  }
}
