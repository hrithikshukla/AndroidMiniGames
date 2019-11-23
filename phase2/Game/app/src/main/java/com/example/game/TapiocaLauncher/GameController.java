package com.example.game.TapiocaLauncher;

import android.view.MotionEvent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

// Controller of the MVC, handles all the game logic
public class GameController implements Observer {

  private GameFacade gameFacade; // stores the model
  private boolean isMoving = false; // whether the Launcher is moving or not
  private int count =
          0; // counts 60 frames (1 second) after which the Launcher ball's position is reset
  private boolean readyToLaunch = false; // Determine if the launcher is ready to fire
  private int screenX, screenY; // Size of the screen
  private double gravityX, gravityY; // How much to decreases the Launcher's speed by each tick
  private boolean ballClicked = false; // Indicates if the player has done a down action yet
  private double startX,
          startY,
          endX,
          endY; // stores the start and end values of the player's hand motion

  // Initializes the GameController
  GameController(GameFacade gameFacade, int screenX, int screenY) {
    this.gameFacade = gameFacade;
    this.screenX = screenX;
    this.screenY = screenY;
    generateLevel();
  }

  // Update the model of the balls i.e new positions based on timestep, health, remove them if
  // ball has no health etc.

  // Updates the model by moving the launcher, checking for collision, if the ball is still
  // it launches counter which counts to 1 second after which the ball is reset to its
  // original position
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

  // counts 60 frames(1 second) after the ball stops moving to reset everything for the next launch
  private void counter() {
    if (count != 60) { // resets ball after 1 second of non-movement by counting 60 frames
      count++;
    }
    if (count == 60) {
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

  // updates isMoving by checking the launcher's speed
  private void updateIsMoving() {
    if (gameFacade.getLauncher().getSpeedX() == 0 && gameFacade.getLauncher().getSpeedY() == 0) {
      isMoving = false;
    }
  }

  // Moves the launcher according to its speed values and reduces the speed by gravity after
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

  // Decreases launcher by the gravity amount.
  private void slowLauncher() {
    Launcher launcher = gameFacade.getLauncher();
    launcher.setSpeedX(decrement(launcher.getSpeedX(), gravityX));
    launcher.setSpeedY(decrement(launcher.getSpeedY(), gravityY));
    gameFacade.update();
  }

  private double decrement(double speed, double gravity) { // reduces speed by gravity amount
    // Log.d("", "decremented " + speed);
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

  // Check for collisions between launcher and balls
  void checkCollision() {
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
          balls.get(i).setHp(balls.get(i).getHp() - 1);
          balls.get(i).setHit(true);
          if (balls.get(i).getHp() == 0) {
            balls.remove(i);
            i--;
            gameFacade.setScore(gameFacade.getScore() + 1);
            gameFacade.update();
          }
        }
      }
    }
  }

  // Observes the InputView and updates if there is input or a tick
  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof MotionEvent) {
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
    } else if (arg instanceof Boolean) {
      updateModel();
      gameFacade.update();
    }
  }

  // moves the ball in the angle of the direction of the starting and end point of the players
  // motion
  private void moveBall(double startX, double startY, double endX, double endY) {
    isMoving = true;
    readyToLaunch = false;
    Launcher launcher = gameFacade.getLauncher();
    launcher.setSpeedX(Math.cos(Math.atan2(endY - startY, endX - startX)) * 300);
    launcher.setSpeedY(Math.sin(Math.atan2(endY - startY, endX - startX)) * 300);
    gravityX = (Math.abs(launcher.getSpeedX()) / 50);
    gravityY = (Math.abs(launcher.getSpeedY()) / 50);
  }

  // generates the level based on the current level or ends the game if it's the last level
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
