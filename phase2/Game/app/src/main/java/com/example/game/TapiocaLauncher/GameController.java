package com.example.game.TapiocaLauncher;

import android.view.MotionEvent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/** Controller of the MVC, handles all the game logic */
public class GameController implements Observer {

  /** Stores the model */
  private GameFacade gameFacade;
  /** Whether the Launcher is moving or not */
  private boolean isMoving = false;
  /** counts 60 frames (1 second) after which the Launcher ball's position is reset */
  private int count = 0;
  /** Determine if the launcher is ready to fire */
  private boolean readyToLaunch = true;
  /** Size of the screen */
  private int screenX, screenY;
  /** How much to decreases the Launcher's speed by each tick */
  private double gravityX, gravityY;
  /** Indicates if the player has clicked the ball yet */
  private boolean ballClicked = false;
  /** Stores start and end coordinates of the player's hand motion */
  private double startX, startY, endX, endY;

  /**
   * Create a gameController with the given parameters
   *
   * @param gameFacade - gameModel which the controller will update
   * @param screenX - x-size of the screen
   * @param screenY - y-size of the screen
   */
  GameController(GameFacade gameFacade, int screenX, int screenY) {
    this.gameFacade = gameFacade;
    this.screenX = screenX;
    this.screenY = screenY;
    generateLevel();
  }

  /**
   * Updates the model by moving the launcher, checking for collision, if the ball is still it
   * launches counter which counts to 1 second after which the ball is reset to its original
   * position
   */
  private void updateModel() {
    if (isMoving) {
      moveLauncher();
      checkCollision();
      updateIsMoving();
    }
    if (!isMoving) {
      counter();
    }
  }

  /**
   * Counts 60 frames(1 second) after the ball stops moving to reset everything for the next launch
   */
  private void counter() {
    if (count != 60) {
      count++;
    }
    if (count == 60) {
      if (gameFacade.getShots() == 0) {
        gameFacade.setGameOver(true);
      } else {
        Launcher launcher = gameFacade.getLauncher();
        List<Ball> balls = gameFacade.getBalls();
        count = 0;
        launcher.setY(screenY - 3 * launcher.getRadius());
        launcher.setX(screenX / 2 - launcher.getRadius());
        readyToLaunch = true;
        for (int i = 0; i < balls.size(); i++) {
          if (balls.get(i).isHit()) {
            balls.get(i).setHit(false);
          }
        }
        if (balls.isEmpty()) {
          generateLevel();
        }
      }
    }
  }

  /** updates isMoving by checking the launcher's speed */
  private void updateIsMoving() {
    if (gameFacade.getLauncher().getSpeedX() == 0 && gameFacade.getLauncher().getSpeedY() == 0) {
      isMoving = false;
    }
  }

  /**
   * Moves the launcher according to its speed values and reduces the speed by the gravity amount
   * after
   */
  private void moveLauncher() {

    Launcher launcher = gameFacade.getLauncher();

    launcher.setX((int) (launcher.getX() + launcher.getSpeedX()));
    launcher.setY((int) (launcher.getY() + launcher.getSpeedY()));

    if (launcher.getX() < 0) {
      launcher.setX(0);
      launcher.setSpeedX(-launcher.getSpeedX());
    } else if (launcher.getX() + 2 * launcher.getRadius() > screenX) {
      launcher.setX(screenX - 2 * launcher.getRadius());
      launcher.setSpeedX(-launcher.getSpeedX());
    }

    if (launcher.getY() < 0) {
      launcher.setY(0);
      launcher.setSpeedY(-launcher.getSpeedY());
    } else if (launcher.getY() + 2 * launcher.getRadius() > screenY) {
      launcher.setY(screenY - 2 * launcher.getRadius());
      launcher.setSpeedY(-launcher.getSpeedY());
    }

    gameFacade.update();
    slowLauncher();
  }

  /** Decreases launcher's by the gravity amount in the x and y direction */
  private void slowLauncher() {
    Launcher launcher = gameFacade.getLauncher();
    launcher.setSpeedX(decrement(launcher.getSpeedX(), gravityX));
    launcher.setSpeedY(decrement(launcher.getSpeedY(), gravityY));
    gameFacade.update();
  }

  /**
   * Reduces speed by gravity amount while ensuring it doesn't go past 0
   *
   * @param speed - original speed
   * @param gravity - gravity amount
   * @return - speed reduced by the gravity
   */
  private double decrement(double speed, double gravity) {
    if (speed >= 0) {
      speed -= gravity;
      if (speed < 0) {
        speed = 0;
      }
    } else {
      speed += gravity;
      if (speed > 0) {
        speed = 0;
      }
    }
    return speed;
  }

  /** Speeds up the launcher */
  private void speedLauncher() {
    Launcher launcher = gameFacade.getLauncher();
    launcher.setSpeedX(launcher.getSpeedX() * 1.3);
    launcher.setSpeedY(launcher.getSpeedY() * 1.3);
    gameFacade.update();
  }

  /** Checks and handles collisions between the Launcher and Balls */
  private void checkCollision() {
    Launcher launcher = gameFacade.getLauncher();
    List<Ball> balls = gameFacade.getBalls();
    for (int i = 0; i < balls.size(); i++) {
      if (Math.hypot(
              launcher.getX()
                  + launcher.getRadius()
                  - balls.get(i).getX()
                  - balls.get(i).getRadius(),
              launcher.getY()
                  + launcher.getRadius()
                  - balls.get(i).getY()
                  - balls.get(i).getRadius())
          <= 2 * launcher.getRadius()) {
        if (!balls.get(i).isHit()) {
          Ball currBall = balls.get(i);
          currBall.setHp(currBall.getHp() - 1);
          currBall.setHit(true);
          if (currBall.getBallType().equals("speedBoost")) {
            speedLauncher();
          }
          if (currBall.getBallType().equals("extraShot")) {
            gameFacade.setShots(gameFacade.getShots() + 2);
          }
          if (currBall.getHp() == 0) {
            balls.remove(i);
            i--;
            gameFacade.setScore(gameFacade.getScore() + 1);
          }
          gameFacade.update();
        }
      }
    }
  }

  /**
   * Observes the InputView and updates if there is an input or a tick
   *
   * @param o - InputView being observed
   * @param arg - Object inputView is passing
   */
  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof MotionEvent) { // Handles input
      MotionEvent event = (MotionEvent) arg;
      Launcher launcher = gameFacade.getLauncher();
      if (readyToLaunch) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          if ((event.getX() >= launcher.getX()
                  && event.getX() <= (launcher.getX() + 2 * launcher.getRadius()))
              && (event.getY() >= launcher.getY()
                  && event.getY() <= (launcher.getY() + 2 * launcher.getRadius()))) {
            startX = event.getX();
            startY = event.getY();
            ballClicked = true;
          }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
          if (ballClicked) {
            endX = event.getX();
            endY = event.getY();
            moveBall(startX, startY, endX, endY);
            isMoving = true;
            ballClicked = false;
          }
        }
      }
    } else if (arg instanceof Boolean) { // Handles Tick
      updateModel();
      gameFacade.update();
    }
  }

  /**
   * Moves the ball in the angle of the direction of the starting and end point of the player's
   * motion
   *
   * @param startX - x coordinate of the starting point
   * @param startY - y coordinate of the starting point
   * @param endX - x coordinate of the end point
   * @param endY - y coordinate of the end point
   */
  private void moveBall(double startX, double startY, double endX, double endY) {
    isMoving = true;
    readyToLaunch = false;
    gameFacade.setShots(gameFacade.getShots() - 1);
    Launcher launcher = gameFacade.getLauncher();
    launcher.setSpeedX(Math.cos(Math.atan2(endY - startY, endX - startX)) * 300);
    launcher.setSpeedY(Math.sin(Math.atan2(endY - startY, endX - startX)) * 300);
    gravityX = (Math.abs(launcher.getSpeedX()) / 50);
    gravityY = (Math.abs(launcher.getSpeedY()) / 50);
  }

  /** generates the level based on the current level or ends the game if it's the last level */
  private void generateLevel() {
    if (gameFacade.getLevel() == 1) {
      gameFacade.setBalls(1);
      gameFacade.setLevel(2);
    } else if (gameFacade.getLevel() == 2) {
      gameFacade.setBalls(2);
      gameFacade.setLevel(3);
    } else if (gameFacade.getLevel() == 3) {
      gameFacade.setBalls(3);
      gameFacade.setLevel(4);
    } else if (gameFacade.getLevel() == 4) {
      gameFacade.setGameOver(true);
      gameFacade.update();
    }
  }
}
