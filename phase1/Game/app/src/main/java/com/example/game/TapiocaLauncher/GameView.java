package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.game.ScoreManager;

@SuppressWarnings("ClickableViewAccessibility")
public class GameView extends SurfaceView implements Runnable {

  private Thread thread;
  private boolean isPlaying;
  private TapiocaGameActivity gameActivity;

  private VisualView visualView;
  private InputView inputView;

  public GameView(Context context, int screenX, int screenY) {

    super(context);
    SharedPreferences prefs = context.getSharedPreferences("highScores", Context.MODE_PRIVATE);
    ScoreManager scoreManager = new ScoreManager(prefs);
    visualView =
        new VisualView(screenX, screenY, getResources(), getHolder(), context, scoreManager);
    inputView = new InputView(screenX, screenY);
  }

  @Override
  public void run() {
    while (isPlaying) {

      visualView.update();
      checkEndGame();
      visualView.draw();
      sleep();
    }
    gameActivity.endGame();
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
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
        inputView.setDownAction(event);
      }
    if (event.getAction() == MotionEvent.ACTION_UP) {
      inputView.setUpAction(event);
    }
    return true;
  }
  void setGameActivity(TapiocaGameActivity activity) {
    this.gameActivity = activity;
  }

  void checkEndGame() {
    if (visualView.checkScore())
    isPlaying = false;
  }
}
