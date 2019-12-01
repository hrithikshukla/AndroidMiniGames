package com.example.game.TilesGame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  /** The activity class of the Tiles game. */
  private static TileGameActivity gameActivity;

  /** The tile board contents. */
  private Board board;

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

  public static TileGameActivity getGameActivity() {
    return gameActivity;
  }

  public static void setGameActivity(TileGameActivity gameActivity) {
    GameView.gameActivity = gameActivity;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    board = createBoard(); // Instantiate new Board.
    board.createBoardItems();

    thread.setRunning(true);
    thread.start();
  }

  Board createBoard() {
    String boardType = gameActivity.getBoardType();

    if (boardType.equals("5By5")) {
      return new Board5By5(context);
    } else if (boardType.equals("Invert")) {
      return new BoardInvert(context);
    } else {
      return new Board4By4(context);
    }
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

  public Board getBoard() {
    return board;
  }

  /** Update the board. */
  public void update() {
    board.update();
    if (board.isGameEnd()) {
      thread.setRunning(false);
    }
  }

  /** Draw the board on canvas. */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      board.draw(canvas);
    }
  }

  /** Register touch input in boardManager. */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {

    float x = event.getX();
    float y = event.getY();

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      board.touchTile(x, y);

      if (!board.isGameStart()) {
        board.setGameStart(true);
      }
    }
    return true;
  }
}
