package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.SurfaceView;

/** View part of the MVC, broken into 2 parts --VisualView and InputView */
@SuppressWarnings("ClickableViewAccessibility")
public class GameView extends SurfaceView implements Runnable {

  private Thread thread;
  /** True if the game is currently playing, used to help exit the game */
  private boolean isPlaying;
  /** Where all visuals are rendered */
  private VisualView visualView;
  /** Where all input is sent to and observed by the GameController */
  private InputView inputView;

  /**
   * Creates the gameView given the parameters
   *
   * @param context - Current context
   * @param screenX - x size of the screen
   * @param screenY - y size of the screen
   */
  public GameView(Context context, int screenX, int screenY) {

    super(context);
    SharedPreferences prefs = context.getSharedPreferences("game", Context.MODE_PRIVATE);
    visualView = new VisualView(screenX, screenY, getResources(), getHolder(), context);
    inputView = new InputView();
  }

  /** Runs the game */
  @Override
  public void run() {

    while (isPlaying) {
      inputView.update(); // Tells inputView to update the gameController to update the gameModel
      visualView.draw(); // Tells visualView to render the updated gameModel
      sleep(); // ensures this happens only 60 times a second
    }
  }

  /** sleeps 17ms, making the program run 60 times a second */
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
   * Sends input to inputView to handle
   *
   * @param event - the MotionEvent that occurred
   * @return true if the even was handled
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN
        || event.getAction() == MotionEvent.ACTION_UP) {
      inputView.screenTouched(event);
    }
    return true;
  }

  /** Getters and Setters. */
  public VisualView getVisualView() {
    return visualView;
  }

  public InputView getInputView() {
    return inputView;
  }
}
