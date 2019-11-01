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

//
public class TapiocaGameActivity extends AppCompatActivity {

  // Views
  private GameView gameView;
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
