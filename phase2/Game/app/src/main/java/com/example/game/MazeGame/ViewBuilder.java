package com.example.game.MazeGame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.example.game.MazeGame.DataStructures.Background;
import com.example.game.MazeGame.DataStructures.Sprites;

import java.util.HashMap;

/**
 * Constructs the View objects of the maze. All view objects are constructed in the same builder
 * because GameView is composed with InputView and VisualView. VisualView also depends on the
 * SurfaceHolder object of GameView, and constructing them in the same builder simplifies things.
 */
class ViewBuilder implements Builder {

  /** Finished View objects to be returned */
  private GameView gameView;

  private InputView inputView;
  private VisualView visualView;

  /** Attributes required for the construction of View objects */
  private MazeGameActivity context;

  private int maxScreenX, maxScreenY;

  private Sprites sprite;

  /**
   * Creates a ViewBuilder object with the given attributes.
   *
   * @param context - context of the MazeGameActivity
   * @param maxScreenX - maximum x position of the screen
   * @param maxScreenY - maximum y position of the screen
   * @param sprite - sprite the player uses
   */
  ViewBuilder(MazeGameActivity context, int maxScreenX, int maxScreenY, Sprites sprite) {
    this.context = context;
    this.maxScreenX = maxScreenX;
    this.maxScreenY = maxScreenY;
    this.sprite = sprite;
  }

  /** Builds the View objects i.e. GameView, VisualView, and InputView and setting up observers. */
  @Override
  public void build() {

    // GameView object must be constructed first to get a SurfaceHolder object for VisualView.
    gameView = new GameView(context);

    // Offset for arrow buttons; calculated from the bottom right corner of the screen
    int xOffSet = 250;
    int yOffSet = 250;

    HashMap<String, Rect> arrowKeyRects = buildArrowKeyRects(xOffSet, yOffSet);

    buildVisualView(gameView.getSurfaceHolder(), arrowKeyRects);
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
   * Builds the rectangles corresponding to where the arrow keys are drawn in the maze. All arrow
   * rects are built with reference to the top left corner of where the right arrow rect is
   * positioned. xOffSet and yOffset are used to determine this top-left corner.
   *
   * @param xOffSet - the x-offset from the max x-position of the screen
   * @param yOffSet - the y-offset from the max y-position of the screen
   * @return a HashMap of rectangles corresponding to the position of the 4 buttons of the maze
   */
  private HashMap<String, Rect> buildArrowKeyRects(int xOffSet, int yOffSet) {
    Background tmp = new Background(maxScreenX, maxScreenY, context.getResources());
    int sideLength = tmp.getArrow("left").getWidth(); // Gets the side length of an arrow bitmap.
    // Top left corner of the right arrow button.
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
   * Builds the VisualView object where arrowKeyRects are the rects where the arrow key buttons are
   * drawn.
   *
   * @param surfaceHolder - the surfaceHolder of a GameView object
   * @param arrowKeyRects - the rectangles of the arrow keys in the game
   */
  private void buildVisualView(SurfaceHolder surfaceHolder, HashMap<String, Rect> arrowKeyRects) {

    Tile tile = new Tile(context.getResources(), sprite);

    Background background = new Background(maxScreenX, maxScreenY, context.getResources());

    Paint backgroundPaint = new Paint();
    backgroundPaint.setColor(Color.WHITE);

    // Set colors and size of text.
    Paint textPaint = new Paint();
    textPaint.setColor(Color.GREEN);
    textPaint.setTextSize(32);

    visualView =
        new VisualView(
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
