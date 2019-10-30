package com.example.game.MazeGame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

/** * Class that initializes all three components of the MVC model. */
public class GameActivity extends AppCompatActivity {

  private GameFacade gameFacade; // Model of the game.
  private GameView gameView; // View of the game. Displays the UI and registers user input.
  // Controller of the game. Processes user input using game state logic to possibly change the
  // model.
  private GameController gameController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Set fullscreen mode.
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // Get maximum x and y coordinate of phone screen and store it in the Point object.
    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

    int mazeWidth = 41;
    int mazeHeight = 21;

    // Player starts at bottom left corner.
    int startX = 1;
    int startY = mazeHeight - 2;
    int startingScore = 100;

    // Create MVC components.
    this.gameFacade =
        new GameFacade(
            new Player(startX, startY, new Score(startingScore)), new Maze(mazeWidth, mazeHeight));
    this.gameController = new GameController(gameFacade);
    this.gameView = new GameView(this, point.x, point.y);

    // Add observors to our MVC components.
    gameView.getInputView().addObserver(gameController);
    gameFacade.addObserver(gameView.getVisualView());

    // Must update model initially so that our view has a representation of our maze to draw.
    gameController.updateModel(Movement.AFK);

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
}
