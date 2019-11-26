package com.example.game.MazeGame;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.game.MazeGame.DataStructures.Background;

/**
 * Class to construct the View objects. All View objects are constructed in the same class because
 * of how GameView is composed with InputView and VisualView.
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

    inputView = new InputView(maxScreenX, maxScreenY);
    visualView = buildVisualView(gameView.getSurfaceHolder());

    gameView.setInputView(inputView);
    gameView.setVisualView(visualView);
  }

  /**
   * Builds the VisualView object
   *
   * @param surfaceHolder the surfaceHolder of a GameView object
   * @return the constructed VisualView object
   */
  private VisualView buildVisualView(SurfaceHolder surfaceHolder) {

    Tile tile = new Tile(context.getResources());
    Background background = new Background(maxScreenX, maxScreenY, context.getResources());

    Paint backgroundPaint = new Paint();

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
        backgroundPaint);
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
