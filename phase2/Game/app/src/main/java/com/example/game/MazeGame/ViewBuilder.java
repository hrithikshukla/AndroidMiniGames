package com.example.game.MazeGame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.example.game.MazeGame.DataStructures.Background;

import java.util.HashMap;

/**
 * Class to construct the View objects. All View objects are constructed in the same class because
 * of how GameView is composed with InputView and VisualView. VisualView also depends on the
 * SurfaceHolder object of GameView.
 */
class ViewBuilder implements Builder {

  // Results to be returned
  private GameView gameView;
  private InputView inputView;
  private VisualView visualView;

  private MazeGameActivity context;
  private int maxScreenX, maxScreenY;

  /**
   * ViewBuilder constructor.
   *
   * @param context - context of the MazeGameActivity
   * @param maxScreenX - maximum x position of the screen
   * @param maxScreenY - maximum y position of the screen
   */
  ViewBuilder(MazeGameActivity context, int maxScreenX, int maxScreenY) {
    this.context = context;
    this.maxScreenX = maxScreenX;
    this.maxScreenY = maxScreenY;
  }

  @Override
  public void build() {
    // GameView object must be constructed first to get a SurfaceHolder object for VisualView.
    gameView = new GameView(context);

    int xOffSet = 250;
    int yOffSet = 250;

    HashMap<String, Rect> arrowKeyRects = buildArrowKeyRects(xOffSet, yOffSet);

    visualView = buildVisualView(gameView.getSurfaceHolder(), arrowKeyRects);
    inputView =
        new InputView(
            arrowKeyRects.get("left"),
            arrowKeyRects.get("right"),
            arrowKeyRects.get("up"),
            arrowKeyRects.get("down"));

    gameView.setInputView(inputView);
    gameView.setVisualView(visualView);
  }

  /**
   * Builds the rectangles corresponding to where the arrow keys are.
   *
   * @param xOffSet - the x-offset from the max x-position of the screen
   * @param yOffSet - the y-offset from the max y-position of the screen
   * @return a hashmap of rectangles corresponding to the 4 buttons of the maze
   */
  private HashMap<String, Rect> buildArrowKeyRects(int xOffSet, int yOffSet) {
    // Gets the side length of an arrow bitmap.
    Background tmp = new Background(maxScreenX, maxScreenY, context.getResources());
    int sideLength = tmp.getArrow("left").getWidth();
    int topLeftCornerX = maxScreenX - xOffSet;
    int topLeftCornerY = maxScreenY - yOffSet;
    HashMap<String, Rect> arrowKeyRects = new HashMap<>();
    arrowKeyRects.put(
        "right",
        new Rect(
            topLeftCornerX,
            topLeftCornerY,
            topLeftCornerX + sideLength,
            topLeftCornerY + sideLength));
    arrowKeyRects.put(
        "down",
        new Rect(
            topLeftCornerX - sideLength,
            topLeftCornerY,
            topLeftCornerX,
            topLeftCornerY + sideLength));
    arrowKeyRects.put(
        "up",
        new Rect(
            topLeftCornerX - sideLength,
            topLeftCornerY - sideLength,
            topLeftCornerX,
            topLeftCornerY));
    arrowKeyRects.put(
        "left",
        new Rect(
            topLeftCornerX - 2 * sideLength,
            topLeftCornerY,
            topLeftCornerX - sideLength,
            topLeftCornerY + sideLength));
    return arrowKeyRects;
  }

  /**
   * Builds the VisualView object
   *
   * @param surfaceHolder the surfaceHolder of a GameView object
   * @param arrowKeyRects
   * @return the constructed VisualView object
   */
  private VisualView buildVisualView(
      SurfaceHolder surfaceHolder, HashMap<String, Rect> arrowKeyRects) {

    Tile tile = new Tile(context.getResources());

    Background background = new Background(maxScreenX, maxScreenY, context.getResources());

    Paint backgroundPaint = new Paint();
    backgroundPaint.setColor(Color.WHITE);

    // Set colors and size of text.
    Paint textPaint = new Paint();
    textPaint.setColor(Color.GREEN);
    textPaint.setTextSize(32);

    return new VisualView(
        context,
        surfaceHolder,
        tile,
        background,
        maxScreenX,
        maxScreenY,
        textPaint,
        backgroundPaint,
        arrowKeyRects);
  }

  /**
   * Getter for the constructed GameView object
   *
   * @return constructed GameView Object
   */
  GameView getGameView() {
    return gameView;
  }

  /**
   * Getter for the constructed InputView object
   *
   * @return constructed InputView Object
   */
  InputView getInputView() {
    return inputView;
  }

  /**
   * Getter for the constructed VisualView object
   *
   * @return constructed VisualView Object
   */
  VisualView getVisualView() {
    return visualView;
  }
}
