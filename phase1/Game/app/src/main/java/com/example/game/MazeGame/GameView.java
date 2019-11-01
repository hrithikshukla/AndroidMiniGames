package com.example.game.MazeGame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

/** View component of the game. Handles the drawing of the screen and user inputs (touches). */
public class GameView extends SurfaceView implements Runnable, Gameover {

  private Thread thread;
  private boolean isPlaying;

  private InputView inputView; // View subcomponent dealing with user input.
  private VisualView visualView; // View subcomponent dealing with drawing the screen.

  public GameView(Context context, int maxScreenX, int maxScreenY) {
    super(context);
    this.inputView = new InputView(maxScreenX, maxScreenY);
    this.visualView = new VisualView(maxScreenX, maxScreenY, context, getHolder());
  }

  /** Runs the game. */
  @Override
  public void run() {

    while (isPlaying) {

      visualView.draw(); // Draw maze
      sleep(); // Sleep to avoid constantly redrawing the maze
    }

  }

  /** Updates the screen roughly 60 times a second. */
  private void sleep() {
    try {
      Thread.sleep(17);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /** Resumes the game from a paused state. */
  public void resume() {
    isPlaying = true;
    thread = new Thread(this);
    thread.start();
  }

  /** Pauses the game. */
  public void pause() {

    try {
      isPlaying = false;
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Registers a touch event by the user and passes it to inputView.
   *
   * @param event - user input event
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      inputView.setNewTouch(event.getX(), event.getY());
    }
    return true;
  }

    /**
     * Getter for inputView.
     */
  InputView getInputView() {
    return inputView;
  }

    /**
     * Getter for visualView.
     */
  VisualView getVisualView() {
    return visualView;
  }

  @Override
  public boolean isGameOver() {
    return visualView.isGameOver();
  }
}
