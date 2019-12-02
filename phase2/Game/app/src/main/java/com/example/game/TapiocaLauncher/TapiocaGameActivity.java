package com.example.game.TapiocaLauncher;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.game.Activities.Game.GameActivity;
import com.example.game.DataBase.UserRepository;
import com.example.game.DataBase.UserScores;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static java.time.temporal.ChronoUnit.SECONDS;

/** Creates the MVC design within the TapiocaLauncher */
public class TapiocaGameActivity extends GameActivity implements Observer {

  /** View */
  private GameView gameView;

  /** Model */
  private GameFacade gameFacade;

  /** Controller */
  private GameController gameController;

  private UserRepository ur;

  private LocalTime startime;

  /**
   * Creates the game
   *
   * @param savedInstanceState - Saved State of application
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setUsername(getIntent().getStringExtra("USERNAME")); // Gets the
    ur = new UserRepository(this, getUsername());
    startime = LocalTime.now();

    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

    gameView = new GameView(this, point.x, point.y);
    setContentView(gameView);

    int launcherRadius = 136 / 2; // Radius of the launcher
    int launcherX = point.x / 2 - launcherRadius; // Launchers initial x
    int launcherY = point.y - 3 * launcherRadius; // Launchers initial y

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

  /** Pauses the game */
  @Override
  protected void onPause() {
    super.onPause();
    gameView.pause();
  }

  /** Resumes the game */
  @Override
  protected void onResume() {
    super.onResume();
    gameView.resume();
  }
  /**
   * Observes the GameFacade to see if the game is over, and if so goes to the exit screen and
   * updates the statistics
   */
  @Override
  public synchronized void update(Observable o, Object arg) {
    gameFacade = (GameFacade) arg;
    if (gameFacade.isGameOver()) {
      LocalTime endtime = LocalTime.now();
      int timetaken = (int) startime.until(endtime, SECONDS);
      UserScores u =
          new UserScores(getUsername(), gameFacade.getScore(), "TAPIOCA_GAME", timetaken);
      ur.addUserScore(u);
      ur.updateUserAmount((int) Math.ceil(gameFacade.getScore() * 0.1));
      setScore(gameFacade.getScore());
      switchToGameOverActivity(this);
    }
  }
}
