package com.example.game.TapiocaLauncher;

import android.content.res.Resources;

import com.example.game.ScoreManager;

import java.util.List;

public class GameManager {

    private Background background;
    // Generates layouts for the game
    private BoardManager boardManager;
    // List of balls to display on the screen
    private List<Ball> layout;
    // Dimensions of the screen
    private int screenX, screenY;
    // The launcher tapioca that the user interacts with
    private LauncherManager launcherMan;
    // Used for resizing and display on different devices

    // Keeps track of scores, observer
    private ScoreManager scoreManager;
    private int level;

    GameManager(
            int screenX,
            int screenY,
            Resources res,
             ScoreManager scoreManager) {

        this.screenX = screenX;
        this.screenY = screenY;

        launcherMan = new LauncherManager(screenX, screenY, res, scoreManager);
        boardManager = new BoardManager(res);
        layout = boardManager.fillBoard(level);
        level++;
    }

    void update() {
        if (layout.size() == 0 && launcherMan.isReadyToLaunch()) {
            layout = boardManager.fillBoard(level);
            level++;
        }
        launcherMan.update(layout);
    }

    List<Ball> getBalls() {
        return layout;
    }

    LauncherManager getLauncherMan() {
        return launcherMan;
    }


}
