package com.example.game.TilesGame;

import android.graphics.Canvas;

interface Board {
  int getScore();

  boolean isGameStart();

  void setGameStart(boolean gameStart);

  boolean isGameEnd();

  void createBoardItems();

  void update();

  void draw(Canvas canvas);

  void touchTile(float x, float y);
}
