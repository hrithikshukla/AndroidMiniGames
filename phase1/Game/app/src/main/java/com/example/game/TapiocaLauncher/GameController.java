package com.example.game.TapiocaLauncher;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private GameFacade gameFacade;
    private boolean isMoving = false;
    private int count = 0;
    private boolean readyToLaunch = false;
    private int screenX, screenY;
    private double gravityX, gravityY;
    private boolean ballClicked = false;
    private double startX, startY, endX, endY;

    GameController(GameFacade gameFacade, int screenX, int screenY) {
        this.gameFacade = gameFacade;
        this.screenX = screenX;
        this.screenY = screenY;
        generateLevel();
    }

    // Update the model of the balls i.e new positions based on timestep, health, remove them if
    // ball has no health etc.

    void updateModel() {
        if (isMoving) {
            moveLauncher();
            checkCollision();
            updateIsMoving();
        }
        if (!isMoving) {
            counter();
        }
    }

    private void counter() {
        if (count != 60) { //resets ball after 1 second of non-movement by counting 60 frames
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

    private void updateIsMoving() {
        if (gameFacade.getLauncher().getSpeedX() == 0 && gameFacade.getLauncher().getSpeedY() == 0) {
            isMoving = false;
        }
    }

    // Move your launcher by the given timestep.
    void moveLauncher() {

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
        Log.d("", "GameController: moveLauncher");

        gameFacade.update();
        slowLauncher();

    }

    // Slow your launcher by some factor of friction.
    void slowLauncher() {
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

    // Check for collisions between launcher and balls. Maybe introduce a method in Ball.java
    // to accept an x and y position and return any balls that collide with the launcher.
    // Remove any such balls in updateModel()
    void checkCollision() {
        Launcher launcher = gameFacade.getLauncher();
        List<Ball> balls = gameFacade.getBalls();
        for (int i = 0; i < balls.size(); i++) {
            if (Math.hypot(launcher.getX() + launcher.getRadius() - balls.get(i).getX() - balls.get(i).getRadius(),
                    launcher.getY() + launcher.getRadius() - balls.get(i).getY() - balls.get(i).getRadius())
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof  MotionEvent) {
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
                        Log.d("", "gameController: Motion Down  x-val: " + startX);
                        Log.d("", "gameController: Motion Down  y-val: " + startY);
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (ballClicked) {
                        endX = event.getX();
                        endY = event.getY();
                        moveBall(startX, startY, endX, endY);
                        isMoving = true;
                        ballClicked = false;
                        Log.d("", "motion Up");
                    }
                }
            }
        }
        else if (arg instanceof Boolean) {
            updateModel();
            gameFacade.update();
        }
    }

    void moveBall(double startX, double startY, double endX, double endY) {
        isMoving = true;
        readyToLaunch = false;
        Launcher launcher = gameFacade.getLauncher();
        launcher.setSpeedX(Math.cos(Math.atan2(endY - startY, endX - startX)) * 300);
        launcher.setSpeedY(Math.sin(Math.atan2(endY - startY, endX - startX)) * 300);
        gravityX = (Math.abs(launcher.getSpeedX()) / 50);
        gravityY = (Math.abs(launcher.getSpeedY()) / 50);
        Log.d("", "GameController: moveBall");
    }

    void generateLevel() {
        if(gameFacade.getLevel() == 1) {
            generateLevel1();
            gameFacade.setLevel(2);
        }
        else if(gameFacade.getLevel() == 2)  {
            generateLevel2();
            gameFacade.setLevel(3);
        }
        else if(gameFacade.getLevel() == 3) {
           gameFacade.setGameOver(true);
           gameFacade.update();
        }
    }

    void generateLevel1() {
         // 6 rows and 6 columns of tapioca with 1 HP
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 6; i++) {
                    Ball b = new Ball(50 + (170 * i), 50 + (140 * j), 41, 1);
                    gameFacade.getBalls().add(b);
                }
            }
        Log.d("", "GameController: generatedLevel1");
        }

    void generateLevel2() {
        // 6 rows and 6 columns of tapioca with 2 HP
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 6; i++) {
                    Ball b = new Ball(50 + (170 * i), 50 + (140 * j), 41, 2);
                    gameFacade.getBalls().add(b);
                }
            }
        Log.d("", "GameController: generatedLevel2");
    }

}
