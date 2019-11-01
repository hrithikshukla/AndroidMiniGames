package com.example.game.TapiocaLauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.game.TapiocaGameLauncher;

//
public class GameActivity extends AppCompatActivity {

  // Views
  private GameView gameView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    Point point = new Point();
    getWindowManager().getDefaultDisplay().getSize(point);

    gameView = new GameView(this, point.x, point.y);
    gameView.setGameActivity(this);
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
    startActivity(intent);
  }
}
