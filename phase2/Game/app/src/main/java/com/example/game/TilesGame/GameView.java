package com.example.game.TilesGame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.content.Context.MODE_PRIVATE;

/** A game view. */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

  /** The activity class of Tiles game. */
  private static TileGameActivity gameActivity;

  /** The Tiles board contents. */
  private Board board;

  /** The part of the program that manages time. */
  private GameThread thread;

  /** The context of this game view. */
  private Context context;

  /** Construct a game view. */
  public GameView(Context context) {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this); // Instantiate new GameThread.
    setFocusable(true);
  }

  /** Get the activity class of Tiles game. */
  public static TileGameActivity getGameActivity() {
    return gameActivity;
  }

  /** Set the activity class of Tiles game. */
  public static void setGameActivity(TileGameActivity gameActivity) {
    GameView.gameActivity = gameActivity;
  }

  /** Create the surface of this game view. */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    board = createBoard(); // Instantiate new Board.
    board.createBoardItems();
    setBoardColors();

    // Start the game thread.
    thread.setRunning(true);
    thread.start();
  }

  /** Create a board for this game. */
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

  /** Get the board of this game view. */
  public Board getBoard() {
    return board;
  }

  /** Set the display colours of this game view's board. */
  void setBoardColors() {
    // Get the user's theme colours from shared preferences.
    SharedPreferences mSettings = context.getSharedPreferences("Settings", MODE_PRIVATE);
    String username = gameActivity.getUsername();
    int colorDangerTile = mSettings.getInt(username + "colorDangerTile", 0);
    int colorKeyTile = mSettings.getInt(username + "colorKeyTile", 0);
    int colorTouch = mSettings.getInt(username + "colorTouch", 0);
    int colorLose = mSettings.getInt(username + "colorLose", 0);

    // Set the retrieved colours into the tile drawer of this game view's board.
    board.setColors(colorDangerTile, colorKeyTile, colorTouch, colorLose);
  }

  /** Update the board. */
  public void update() {
    board.update();
    if (board.isGameEnd()) {
      thread.setRunning(false);
    }
  }

  /**
   * Draw the board on canvas.
   *
   * @param canvas: the canvas to be drawn on.
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      board.draw(canvas);
    }
  }

  /** Register touch input in board. */
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {

    float x = event.getX();
    float y = event.getY();

    // Record touch event at (x, y) on this game view's board.
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      board.touchTile(x, y);

      // Start game upon first touch event.
      if (!board.isGameStart()) {
        board.setGameStart(true);
      }
    }
    return true;
  }
}
