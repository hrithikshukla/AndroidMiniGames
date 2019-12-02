package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.game.R;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * View part of the MVC, displays all the visuals by observing gameFacade and updating whenever
 * gameFacade changes
 */
class VisualView implements Observer {
  /** The background image displayed on screen */
  private Background background;

  private Context context;
  /** GameFacade that is being observed */
  private GameFacade gameFacade;
  /** Launcher within the gameFacade */
  private Launcher launcher; //
  /** balls within the gameFacade */
  private List<Ball> balls;
  /** score/level/shots within the gameFacade */
  private int score, level, shots;
  /** A counter to display the LauncherBall's rotation */
  private int turnCounter = 0;

  private Resources res;
  private Paint paint; // Paint
  private SurfaceHolder surfaceHolder;
  /** Size of the Screen */
  private int screenX, screenY;

  /**
   * stores bitmaps of the Launcher's ball, each rotated a multiple of 90 degrees to give an
   * illusion of rotation when moving the Launcher ball
   */
  private Bitmap launcherOrientation1,
      launcherOrientation2,
      launcherOrientation3,
      launcherOrientation4;

  private Bitmap tapiocaRed,
      tapiocaBrown,
      tapiocaWhite,
      tapiocaPurple; // Bitmap of the brown, red, white balls

  /**
   * Initializes a VisualView object with the given parameters.
   *
   * @param screenX - x-size of screen
   * @param screenY - y-size of screen
   * @param res - resources being used
   * @param surfaceHolder - the surfaceHolder from a GameView object
   * @param context - the context from a GameView object
   */
  VisualView(
      int screenX, int screenY, Resources res, SurfaceHolder surfaceHolder, Context context) {
    this.screenX = screenX;
    this.screenY = screenY;
    this.res = res;
    this.surfaceHolder = surfaceHolder;
    this.context = context;

    // Create screen ratio
    background = new Background(screenX, screenY, res);

    paint = new Paint();
    paint.setTextSize(64);
    paint.setColor(Color.BLACK);

    createBitmaps();
  }

  /** Creates and stores bitmaps at the start for efficiency */
  private void createBitmaps() {
    createLauncherBitmaps();
    createTapiocaBitmaps();
  }

  /** Creates and stores the Ball's bitmaps */
  private void createTapiocaBitmaps() {
    tapiocaBrown = BitmapFactory.decodeResource(res, R.drawable.brown);
    tapiocaRed = BitmapFactory.decodeResource(res, R.drawable.red);
    tapiocaWhite = BitmapFactory.decodeResource(res, R.drawable.white);
    tapiocaPurple = BitmapFactory.decodeResource(res, R.drawable.purple);

    int width = tapiocaBrown.getWidth() / 2; // 157
    int height = tapiocaBrown.getHeight() / 2; // 136
    tapiocaBrown = Bitmap.createScaledBitmap(tapiocaBrown, width, height, false);

    width = tapiocaRed.getWidth() / 2; // 157
    height = tapiocaRed.getHeight() / 2; // 136
    tapiocaRed = Bitmap.createScaledBitmap(tapiocaRed, width, height, false);

    width = tapiocaWhite.getWidth() / 2; // 157
    height = tapiocaWhite.getHeight() / 2; // 136
    tapiocaWhite = Bitmap.createScaledBitmap(tapiocaWhite, width, height, false);

    width = tapiocaPurple.getWidth() / 2; // 157
    height = tapiocaPurple.getHeight() / 2; // 136
    tapiocaPurple = Bitmap.createScaledBitmap(tapiocaPurple, width, height, false);
  }

  /** Creates and stores the Launcher's Bitmaps */
  private void createLauncherBitmaps() {
    launcherOrientation1 = BitmapFactory.decodeResource(res, R.drawable.tapioca1);
    launcherOrientation2 = BitmapFactory.decodeResource(res, R.drawable.tapioca2);
    launcherOrientation3 = BitmapFactory.decodeResource(res, R.drawable.tapioca3);
    launcherOrientation4 = BitmapFactory.decodeResource(res, R.drawable.tapioca4);
    int width = launcherOrientation1.getWidth() / 2;
    int height = launcherOrientation1.getHeight() / 2;

    launcherOrientation1 = Bitmap.createScaledBitmap(launcherOrientation1, width, height, false);
    launcherOrientation2 = Bitmap.createScaledBitmap(launcherOrientation2, width, height, false);
    launcherOrientation3 = Bitmap.createScaledBitmap(launcherOrientation3, width, height, false);
    launcherOrientation4 = Bitmap.createScaledBitmap(launcherOrientation4, width, height, false);
  }

  /** Redraws the screen */
  void draw() {

    if (surfaceHolder.getSurface().isValid()) {

      Canvas canvas = surfaceHolder.lockCanvas();
      canvas.drawBitmap(background.getBackground(), background.getX(), background.getY(), paint);
      drawBalls(canvas); // Draw the balls
      drawLauncher(canvas); // Draw Launcher

      drawText(canvas); // Draw the level and score
      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  /**
   * Draws the Launcher
   *
   * @param canvas - canvas on which the launcher is drawn
   */
  private void drawLauncher(Canvas canvas) {
    canvas.drawBitmap(getLauncherOrientation(), launcher.getX(), launcher.getY(), paint);
  }

  /**
   * Gets the launcher's orientation which changes by 90 degrees every call to give an illusion of
   * rotation
   */
  private Bitmap getLauncherOrientation() {
    if (launcher.getSpeedX() != 0 && launcher.getSpeedY() != 0) {
      switch (turnCounter) {
        case 0:
          turnCounter++;
          return launcherOrientation1;
        case 1:
          turnCounter++;
          return launcherOrientation2;
        case 2:
          turnCounter++;
          return launcherOrientation3;
        case 3:
          turnCounter = 0;
          return launcherOrientation4;
        default:
          turnCounter = 0;
          return launcherOrientation1;
      }
    } else {
      return launcherOrientation1;
    }
  }

  /**
   * Draws all the balls
   *
   * @param canvas - canvas on which the balls are drawn
   */
  private void drawBalls(Canvas canvas) {
    for (Ball ball : balls) {
      drawBall(ball, canvas);
    }
  }

  /**
   * Draws a ball
   *
   * @param ball - the ball being drawn
   * @param canvas - canvas on which the ball is drawn
   */
  private void drawBall(Ball ball, Canvas canvas) {
    Bitmap orientation1;
    if (ball.getBallType().equals("reg") && ball.getHp() == 1) {
      orientation1 = tapiocaBrown;
    } else if (ball.getBallType().equals("reg") && ball.getHp() == 2) {
      orientation1 = tapiocaRed;
    } else if (ball.getBallType().equals("speedBoost")) {
      orientation1 = tapiocaWhite;
    } else { // if(ball.getBallType() == "image") {
      orientation1 = tapiocaPurple;
    }

    canvas.drawBitmap(orientation1, ball.getX(), ball.getY(), paint);
  }

  /**
   * Draws text on the screen
   *
   * @param canvas - canvas on which the text is drawn
   */
  private void drawText(Canvas canvas) {
    canvas.drawText(context.getString(R.string.score) + ": " + score + "", 5, screenY - 30, paint);
    canvas.drawText(context.getString(R.string.level) + (level - 1) + "", 5, screenY - 100, paint);
    canvas.drawText(context.getString(R.string.shots) + shots + "", 5, screenY - 170, paint);
  }

  /**
   * Observed if gameFacade has changed and stores its values for rendering
   *
   * @param o - gameFacade being observed
   * @param arg - object gameFacade returns
   */
  @Override
  public void update(Observable o, Object arg) {
    gameFacade = (GameFacade) arg;
    launcher = gameFacade.getLauncher();
    balls = gameFacade.getBalls();
    score = gameFacade.getScore();
    level = gameFacade.getLevel();
    shots = gameFacade.getShots();
  }
}
