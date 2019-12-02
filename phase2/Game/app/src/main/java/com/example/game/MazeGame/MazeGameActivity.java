package com.example.game.MazeGame;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.game.Activities.Game.GameActivity;
import com.example.game.DataBase.UserRepository;
import com.example.game.DataBase.UserScores;
import com.example.game.MazeGame.DataStructures.Sprites;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Class that initializes all three components of the MVC model. Observes the model GameFacade to
 * determine when the game is over.
 *
 * <p>High level overview of what happens when the user presses the screen: InputView notifies
 * GameController of this movement and the latter processes it. GameController updates the
 * GameFacade for a valid input. Player's position is updated and it notifies Maze and Collectibles
 * of its new position Maze updates the player's position in its representation. Collectible checks
 * if the player has picked up a new collectible and notifies score Score updates with the value of
 * the picked up collectible GameFacade notifies VisualView to update it with the most recent model
 * of the game.
 */
public class MazeGameActivity extends GameActivity implements Observer {

  /** View of the game. */
  private GameView gameView;

  private UserRepository ur;

  private LocalTime startime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setUsername(getIntent().getStringExtra("USERNAME"));
    ur = new UserRepository(this, getUsername());
    startime = LocalTime.now();
    // Set fullscreen mode.
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // Get maximum x and y coordinate of phone screen and store it in the Point object.
    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

    int maxScreenX = point.x;
    int maxScreenY = point.y;

    // Maze dimension is dependency injected in.
    int[] dimensions = getIntent().getIntArrayExtra("DIMENSIONS");
    int mazeWidth = Objects.requireNonNull(dimensions)[0];
    int mazeHeight = dimensions[1];

    // Player starts at bottom left corner.
    int startX = 1;
    int startY = mazeHeight - 2;

    // Starting score is dependency injected in.
    int startingScore = getIntent().getIntExtra("SCORE", 0);

    // Create MVC components. Complicated objects are assembled using builders.

    // Model of the game.
    GameFacadeBuilder gameFacadeBuilder =
        new GameFacadeBuilder(startX, startY, startingScore, mazeWidth, mazeHeight);
    gameFacadeBuilder.build();
    GameFacade gameFacade = gameFacadeBuilder.getGameFacade();

    // View of the game. Displays the UI and registers user input.

    // Get Sprite to be used by the player.
    Sprites sprite = (Sprites) getIntent().getSerializableExtra("SPRITE");

    ViewBuilder viewBuilder = new ViewBuilder(this, maxScreenX, maxScreenY, sprite);
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
    GameFacade gameFacade = (GameFacade) o;
    if (gameFacade.getMaze().hasEscaped()) {
      String difficulty = getIntent().getStringExtra("DIFFICULTY");
      LocalTime endtime = LocalTime.now();
      int timetaken = (int) startime.until(endtime, SECONDS);
      UserScores u =
          new UserScores(
              getUsername(),
              gameFacade.getPlayer().getScore(),
              "MAZE_GAME_" + difficulty,
              timetaken);
      ur.addUserScore(u);
      ur.updateUserAmount((int) Math.ceil(gameFacade.getPlayer().getScore() * 0.2));
      setScore(gameFacade.getPlayer().getScore());
      switchToGameOverActivity(this);
    }
  }
}
