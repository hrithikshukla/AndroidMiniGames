package com.example.game.MazeGame;

import android.graphics.Rect;

import com.example.game.MazeGame.DataStructures.Movement;
import com.example.game.MazeGame.DataStructures.NewGameState;

import java.util.Observable;
import java.util.Observer;

/** Viewe responsible for handling the inputs to the screen. */
public class InputView extends Observable implements Observer, Gameover {

  private boolean gameOver;

  /**
   * Rectangles represent regions of the screen where if the touch was registered in moveX Rect, the
   * player would move in the x direction.
   */
  private Rect moveLeft, moveRight, moveUp, moveDown;

  InputView(int maxScreenX, int maxScreenY) {
    int horizontalWidth = maxScreenX / 4;
    int verticalHeight = maxScreenY / 2;
    this.moveLeft = new Rect(0, 0, horizontalWidth, maxScreenY);
    this.moveRight = new Rect(maxScreenX - horizontalWidth, 0, maxScreenX, maxScreenY);
    this.moveUp = new Rect(horizontalWidth, 0, maxScreenX - horizontalWidth, verticalHeight);
    this.moveDown =
        new Rect(horizontalWidth, verticalHeight, maxScreenX - horizontalWidth, maxScreenY);
  }

  /**
   * Registers a new touch on the screen.
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

  // Observing gameFacade, if game ends then set gameOver
  @Override
  public void update(Observable observable, Object o) {
    NewGameState newGameState = (NewGameState) o;
    this.gameOver = newGameState.isGameOver();
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }
}
