package com.example.game.MazeGame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** View component of the game. Handles the drawing of the screen and user inputs (touches). */
class GameView extends SurfaceView implements Runnable {

  private Thread thread;
  private boolean isPlaying;

  /** View subcomponents dealing with user input and drawing the screen. Dependency injected in. */
  private InputView inputView;

  private VisualView visualView;

  GameView(Context context) {
    super(context);
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
   * Setter for inputView
   *
   * @param inputView the inputView to be injected
   */
  void setInputView(InputView inputView) {
    this.inputView = inputView;
  }

  /**
   * Setter for visualView
   *
   * @param visualView the visualView to be injected
   */
  void setVisualView(VisualView visualView) {
    this.visualView = visualView;
  }

  /**
   * Getter for GameView's SurfaceHolder object.
   *
   * @return SurfaceHolder object of GameView
   */
  SurfaceHolder getSurfaceHolder() {
    return getHolder();
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
}
