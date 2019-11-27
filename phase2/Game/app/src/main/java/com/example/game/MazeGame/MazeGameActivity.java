package com.example.game.MazeGame;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.game.Activities.Game.GameActivity;
import com.example.game.MazeGame.DataStructures.NewGameState;
import com.example.game.Save.User;

import java.util.Observable;
import java.util.Observer;

/** * Class that initializes all three components of the MVC model. */
public class MazeGameActivity extends GameActivity implements Observer {

    private GameView gameView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    usr = (User) getIntent().getSerializableExtra("UserObject");
    // Set fullscreen mode.
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // Get maximum x and y coordinate of phone screen and store it in the Point object.
    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

      int maxScreenX = point.x;
      int maxScreenY = point.y;

    int mazeWidth = 41;
    int mazeHeight = 21;

    // Player starts at bottom left corner.
    int startX = 1;
    int startY = mazeHeight - 2;
    int startingScore = 100;

      // Create MVC components. Complicated objects are assembled using builders.

      // Model of the game.
      GameFacadeBuilder gameFacadeBuilder =
              new GameFacadeBuilder(startX, startY, startingScore, mazeWidth, mazeHeight);
      gameFacadeBuilder.build();
      GameFacade gameFacade = gameFacadeBuilder.getGameFacade();

      // View of the game. Displays the UI and registers user input.
      ViewBuilder viewBuilder = new ViewBuilder(this, maxScreenX, maxScreenY);
      viewBuilder.build();
      InputView inputView = viewBuilder.getInputView();
      VisualView visualView = viewBuilder.getVisualView();
      this.gameView = viewBuilder.getGameView();

      // Controller of the game. Processes user input using game state logic to possibly change the
      // model.
      GameController gameController = new GameController(gameFacade);

      // Add observers to our MVC components.
      inputView.addObserver(gameController);
      gameFacade.addObserver(visualView);
      gameFacade.addObserver(inputView);
    gameFacade.addObserver(this);

      // The view components observing gameFacade must be updated at least once to display the
      // initial board.
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

  @Override
  public synchronized void update(Observable o, Object arg) {
    // Set users high score if applicable when game ends and send it to gameOverActivity
    NewGameState newGameState = (NewGameState) arg;
    SharedPreferences sharedPreferences = getSharedPreferences("highScores", MODE_PRIVATE);
    if (newGameState.isGameOver()) {
      if (usr.getUserData().getMazeHighScore() < newGameState.getScore()) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(usr.getUsername() + "mazehighscore", newGameState.getScore());
        editor.apply();
      }
      switchToGameOverActivity(this);
    }
  }
}
