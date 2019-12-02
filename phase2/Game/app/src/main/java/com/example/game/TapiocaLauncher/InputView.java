package com.example.game.TapiocaLauncher;

import android.view.MotionEvent;

import java.util.Observable;

/** Handles Input and updates GameController every tick, */
class InputView extends Observable {

  InputView() {}

  /**
   * Sends motion event to GameController to deal with
   *
   * @param event - the motionEvent that occurred
   */
  void screenTouched(MotionEvent event) {
    setChanged();
    notifyObservers(event);
  }

  /** Signals gameController to update the game */
  void update() {
    setChanged();
    notifyObservers(true);
  }
}
