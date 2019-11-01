package com.example.game.TilesGame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  /** The activity class of the Tiles game. */
  private static GameActivity gameActivity;

  /** The tile board contents. */
  private BoardManager boardManager;

  /** The part of the program that manages time. */
  private GameThread thread;

  private Context context;

  public GameView(Context context) {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this); // Instantiate new GameThread.
    setFocusable(true);
  }

  public static GameActivity getGameActivity() {
    return gameActivity;
  }

  public static void setGameActivity(GameActivity gameActivity) {
    GameView.gameActivity = gameActivity;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    boardManager = new BoardManager(context); // Instantiate new BoardManager.
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

  public BoardManager getBoardManager() {
    return boardManager;
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
