package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.game.R;
import com.example.game.ScoreManager;

import java.util.List;

@SuppressWarnings("ClickableViewAccessibility")
public class GameView extends SurfaceView implements Runnable {

  private Thread thread;
  private boolean isPlaying;
  // The background of the game
  private Background background;
  // Generates layouts for the game
  private BoardManager boardManager;
  // List of balls to display on the screen
  private List<Ball> layout;
  // Dimensions of the screen
  private int screenX, screenY;
  // The launcher tapioca that the user interacts with
  public static Launcher launcher;
  // Used for resizing and display on different devices
  public static float screenRatioX, screenRatioY;
  private Paint paint;
  private double startX = 0, startY = 0, endX = 0, endY = 0;
  private boolean ballClicked;
  private int level = 1;
  // Keeps track of scores, observer
  private ScoreManager scoreManager;

  public GameView(Context context, int screenX, int screenY) {

    super(context);
    SharedPreferences prefs = context.getSharedPreferences("game", Context.MODE_PRIVATE);
    scoreManager = new ScoreManager(prefs);
    this.screenX = screenX;
    this.screenY = screenY;
    // Create screen ratio
    screenRatioX = 1920f / screenX;
    screenRatioY = 1080f / screenY;

    background = new Background(screenX, screenY, getResources());
    launcher = new Launcher(screenX, screenY, getResources(), scoreManager);
    boardManager = new BoardManager(context);
    layout = boardManager.fillBoard(level);
    level++;
    paint = new Paint();
    paint.setTextSize(64);
    paint.setColor(Color.BLACK);
  }

  @Override
  public void run() {

    while (isPlaying) {

      update();
      draw();
      sleep();
    }
  }

  void update() {
    launcher.update(layout);
  }

  private void draw() {

    if (getHolder().getSurface().isValid()) {

      Canvas canvas = getHolder().lockCanvas();
      canvas.drawBitmap(background.getBackground(), background.getX(), background.getY(), paint);

      // Check if board is empty and ball is not moving, then generates new layout
      if (layout.size() == 0 && launcher.isReadyToLaunch()) {
        layout = boardManager.fillBoard(level);
        level++;
      }
      // Draws the balls that can be destroyed
      for (Ball ball : layout) {
        canvas.drawBitmap(ball.getBall(), ball.getX(), ball.getY(), paint);
      }
      // Draw launcher
      canvas.drawBitmap(launcher.getLauncher(), launcher.getX(), launcher.getY(), paint);
      // Draw the level and score
      canvas.drawText(
          getContext().getString(R.string.score) + scoreManager.getScore() + "",
          5,
          screenY - 30,
          paint);
      canvas.drawText(
          getContext().getString(R.string.level) + (level - 1) + "", 5, screenY - 100, paint);

      getHolder().unlockCanvasAndPost(canvas);
    }
  }

  private void sleep() {
    try {
      Thread.sleep(17);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void resume() {

    isPlaying = true;
    thread = new Thread(this);
    thread.start();
  }

  public void pause() {

    try {
      isPlaying = false;
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (launcher.isReadyToLaunch()) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        if ((event.getX() >= launcher.getX()
                && event.getX() <= (launcher.getX() + launcher.getHeight()))
            && (event.getY() >= launcher.getY()
                && event.getY() <= (launcher.getY() + launcher.getWidth()))) {
          startX = event.getX();
          startY = event.getY();
          ballClicked = true;
          // Log.d("", "onTouchEvent: Motion Down  x-val: " + startX);
          // Log.d("", "onTouchEvent: Motion Down  y-val: " + startY);
        }
      }
      //            if (event.getAction() == MotionEvent.ACTION_MOVE) {
      //                //Log.d("", "onTouchEvent: Move" + event.getX());
      //            }
      if (event.getAction() == MotionEvent.ACTION_UP) {
        if (ballClicked) {
          endX = event.getX();
          endY = event.getY();
          launcher.moveBall(startX, startY, endX, endY);
          ballClicked = false;
        }
      }
    }
    return true;
  }
}
