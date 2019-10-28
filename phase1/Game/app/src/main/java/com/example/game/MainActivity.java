package com.example.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /** Called when the user taps the 'MAZE' button */
  public void goToMazeGame(View view) {
    Intent intent = new Intent(this, com.example.game.MazeGameLauncher.class);
    startActivity(intent);
  }

  /** Called when the user taps the 'TAPIOCA LAUNCHER' button */
  public void goToTapiocaLauncher(View view) {
    Intent intent = new Intent(this, com.example.game.TapiocaGameLauncher.class);
    startActivity(intent);
  }

  /** Called when the user taps the 'TILES' button */
  public void goToTilesGame(View view) {
    Intent intent = new Intent(this, com.example.game.TilesGameLauncher.class);
    startActivity(intent);
  }
}
