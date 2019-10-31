package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.game.R;
import com.example.game.ScoreManager;

import java.util.List;

public class LauncherManager {
    private ScoreManager scoreManager;
    // Used to animate the ball and move it
    private boolean isMoving = false;
    private boolean readyToLaunch = true;
    // Used to animate the ball
    private int turnCounter = 0;
    // Used to reset the ball after a turn
    private int count = 0;
    // Keeps track of scores
    // The screen length and width to calculate if ball is out of bounds
    private int screenX, screenY;
    private double gravity, speedX, speedY;
    private double gravityX, gravityY;
    private Launcher launcher;

    LauncherManager(int screenX, int screenY, Resources res, ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.screenX = screenX;
        this.screenY = screenY;

        launcher = new Launcher(screenX, screenY, res, scoreManager);

    }

    void moveBall(double startX, double startY, double endX, double endY) {
        isMoving = true;
        readyToLaunch = false;
        launcher.setSpeedX(Math.cos(Math.atan2(endY - startY, endX - startX)) * 300);
        launcher.setSpeedY(Math.sin(Math.atan2(endY - startY, endX - startX)) * 300);
        launcher.setGravityX(Math.abs(launcher.getSpeedX()) / 50);
        launcher.setGravityY(Math.abs(launcher.getSpeedY()) / 50);
        Log.d("", "setUpAction: moveBall");

    }

    void update(List<Ball> balls) {
        if (isMoving) {
            launcher.move();
            //launcher.setSpeedX = decrement(launcher.getspeedX(), launcher.getgravityX());
            //launcher.setSpeedY = decrement(launcher.getspeedY(), launcher.getgravityY());
            detectCollisions(balls);
            if (launcher.getSpeedX() == 0 && launcher.getSpeedY() == 0) {
                isMoving = false;
            }
        }
        if (!isMoving && count != 60) { // resets ball after 1 second of non-movement by counting 60 frames
            count++;
        }
        if (count == 60) {
            count = 0;
            launcher.setY(1850);
            launcher.setX(screenX / 2);
            readyToLaunch = true;
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isHit()) {
                    balls.get(i).setHit(false);
                }
            }
        }
    }

    // Checks for collisions between this and other tapioca, also adds points in scoreManager.
    private void detectCollisions(List<Ball> balls) {
        for (int i = 0; i < balls.size(); i++) {
            if (Math.hypot(launcher.getCentreX() - balls.get(i).centreX, launcher.getCentreY() - balls.get(i).centreY)
                    <= 2 * launcher.getRadius()) {
                if (!balls.get(i).isHit()) {
                    balls.get(i).setHp(balls.get(i).getHp() - 1);
                    Log.d("", balls.get(i).getHp() + " ");
                    balls.get(i).setHit(true);
                    if (balls.get(i).getHp() == 0) {
                        balls.remove(i);
                        i--;
                        scoreManager.addScore();
                    }
                }
            }
        }
    }

    public int getX() {
        return launcher.getX();
    }
    public int getY() {
        return launcher.getY();
    }
    public int getHeight() {
        return launcher.getHeight();
    }
    public int getWidth() {
        return launcher.getWidth();
    }
    boolean isReadyToLaunch() {
        return readyToLaunch;
    }
    Bitmap getLauncher() {
        return launcher.getLauncher();
    }




}
