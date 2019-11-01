package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.game.R;
import com.example.game.ScoreManager;

import java.util.List;

class VisualView {

  private Background background;
  // Generates layouts for the game
//  private BoardManager boardManager;
  // List of balls to display on the screen
//  private List<Ball> layout;
  // Dimensions of the screen
  private int screenX, screenY;
  // The launcher tapioca that the user interacts with
//  static LauncherManager launcherMan;
  // Used for resizing and display on different devices
  static float screenRatioX, screenRatioY;
  private Paint paint;
  private SurfaceHolder surfaceHolder;
  private int level = 1;

  // Keeps track of scores, observer
  private ScoreManager scoreManager;
  private Context context;

  static GameManager gameMan;

  VisualView(
      int screenX,
      int screenY,
      Resources res,
      SurfaceHolder surfaceHolder,
      Context context,
      ScoreManager scoreManager) {
    this.screenX = screenX;
    this.screenY = screenY;

    this.surfaceHolder = surfaceHolder;
    this.scoreManager = scoreManager;
    this.context = context;

    // Create screen ratio
    screenRatioX = 1920f / screenX;
    screenRatioY = 1080f / screenY;
//
    background = new Background(screenX, screenY, res);
//    launcherMan = new LauncherManager(screenX, screenY, res, scoreManager);
//    boardManager = new BoardManager(res);
//    layout = boardManager.fillBoard(level);
//    level++;

    paint = new Paint();
    paint.setTextSize(64);
    paint.setColor(Color.BLACK);
    gameMan = new GameManager(screenX, screenY, res, scoreManager);

  }

  void draw() {

    if (surfaceHolder.getSurface().isValid()) {


      Canvas canvas = surfaceHolder.lockCanvas();
      canvas.drawBitmap(background.getBackground(), background.getX(), background.getY(), paint);

      // Draws the balls that can be destroyed

      for (Ball ball : gameMan.getBalls()) {
        canvas.drawBitmap(ball.getBall(), ball.getX(), ball.getY(), paint);
      }
      // Draw launcher
      canvas.drawBitmap(gameMan.getLauncherMan().getLauncher(), gameMan.getLauncherMan().getX(), gameMan.getLauncherMan().getY(), paint);
      // Draw the level and score
      canvas.drawText(
          context.getString(R.string.score) + scoreManager.getScore() + "", 5, screenY - 30, paint);
      canvas.drawText(
          context.getString(R.string.level) + (level - 1) + "", 5, screenY - 100, paint);

      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }
  boolean checkScore () {
    return scoreManager.getScore() >= 69 && gameMan.getLauncherMan().isReadyToLaunch();
  }

  void update() {
    gameMan.update();
  }
}
