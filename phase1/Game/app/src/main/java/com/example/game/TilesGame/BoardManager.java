package com.example.game.TilesGame;

import android.graphics.Canvas;

class BoardManager extends ClassLoader {

  private int boardWidth;
  private int boardHeight;

  BoardManager(int width, int height) {
    this.boardWidth = width;
    this.boardHeight = height;
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  void createBoardItems() {}

  void update() {}

  void draw(Canvas canvas) {}
}
