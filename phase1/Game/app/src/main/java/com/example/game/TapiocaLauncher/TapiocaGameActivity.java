package com.example.game.TapiocaLauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.game.Save.User;
import com.example.game.TapiocaGameLauncher;

import java.util.ArrayList;
import java.util.List;

//
public class TapiocaGameActivity extends AppCompatActivity {

  // Views
  private GameView gameView;

  private GameFacade gameFacade;
  private GameController gameController;
  User usr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    usr = (User) getIntent().getSerializableExtra("UserObject");

    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

    gameView = new GameView(this, point.x, point.y);
    setContentView(gameView);

    int launcherRadius = 99/2;
    int launcherX = point.x / 2 - launcherRadius;
    int launcherY = point.y - 3 * launcherRadius;
    // Create MVC components.
    this.gameFacade = new GameFacade(new Launcher(launcherX, launcherY, launcherRadius), new ArrayList<Ball>());
    this.gameController = new GameController(gameFacade, point.x, point.y);
    this.gameView = new GameView(this, point.x, point.y);

    // Add observors to our MVC components.
    gameView.getInputView().addObserver(gameController);
    gameFacade.addObserver(gameView.getVisualView());
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

  public void endGame() {
    Intent intent = new Intent(this, TapiocaGameLauncher.class);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }
}
