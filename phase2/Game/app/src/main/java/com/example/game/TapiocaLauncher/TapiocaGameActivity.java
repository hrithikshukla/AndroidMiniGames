package com.example.game.TapiocaLauncher;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.game.Activities.Game.GameActivity;
import com.example.game.Save.User;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// Creates the MVC design within the TapiocaLauncher
public class TapiocaGameActivity extends GameActivity implements Observer {

    // View
    private GameView gameView;

    // Model
    private GameFacade gameFacade;

    // Controller
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usr = (User) getIntent().getSerializableExtra("UserObject"); // Gets the

        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);

        int launcherRadius = 136 / 2; // Radius of the launcher
        int launcherX = point.x / 2 - launcherRadius; // Launchers inital x
        int launcherY = point.y - 3 * launcherRadius; // Launchers inital y

        // Create MVC components.
        this.gameFacade =
                new GameFacade(new Launcher(launcherX, launcherY, launcherRadius), new ArrayList<Ball>());
        this.gameController = new GameController(gameFacade, point.x, point.y);
        this.gameView = new GameView(this, point.x, point.y);

        // Add observers to our MVC components.
        gameView.getInputView().addObserver(gameController);
        gameFacade.addObserver(gameView.getVisualView());
        gameFacade.addObserver(this);
        gameFacade.update();
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    // Observes the GameFacade to see if the game is over, and if so goes to the exit screen and
    // updates the statistics
    @Override
    public synchronized void update(Observable o, Object arg) {
        gameFacade = (GameFacade) arg;
        if (gameFacade.isGameOver()) {
            SharedPreferences sharedPreferences = getSharedPreferences("highScores", MODE_PRIVATE);
            if (usr.getUserData().getTapiocaHighScore() < gameFacade.getScore()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(usr.getUsername() + "tapiocahighscore", gameFacade.getScore());
                editor.apply();
                switchToGameOverActivity(this);
            }
        }
    }
}
