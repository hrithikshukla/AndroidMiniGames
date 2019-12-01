package com.example.game.MazeGame;

import android.graphics.Rect;

import com.example.game.MazeGame.DataStructures.Movement;

import java.util.Observable;

/** View responsible for handling the inputs to the screen. */
class InputView extends Observable {

  /**
   * Rectangles represent regions of the screen corresponding to player motion. e.g. if the player
   * presses in the moveLeft rectangle they would move to the left.
   */
  private Rect moveLeft, moveRight, moveUp, moveDown;

  InputView(Rect moveLeft, Rect moveRight, Rect moveUp, Rect moveDown) {
    this.moveLeft = moveLeft;
    this.moveRight = moveRight;
    this.moveUp = moveUp;
    this.moveDown = moveDown;
  }

  /**
   * Registers a new touch on the screen and notifies the game controller of what kind of action it
   * was.
   *
   * @param x - x position of the touch
   * @param y - y position of the touch
   */
  void setNewTouch(float x, float y) {
    setChanged();
    notifyObservers(getMovementFromTouch(Math.round(x), Math.round(y)));
  }

  /**
   * Returns the direction corresponding to the region the touch was registered in.
   *
   * @param x - x position of the touch
   * @param y - y position of the touch
   */
  private Movement getMovementFromTouch(int x, int y) {
    if (moveLeft.contains(x, y)) {
      return Movement.LEFT;
    } else if (moveRight.contains(x, y)) {
      return Movement.RIGHT;
    } else if (moveUp.contains(x, y)) {
      return Movement.UP;
    } else if (moveDown.contains(x, y)) {
      return Movement.DOWN;
    }
    // Return AFK by default.
    return Movement.AFK;
  }
}
