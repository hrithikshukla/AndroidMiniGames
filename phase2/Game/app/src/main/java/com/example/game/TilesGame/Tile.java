package com.example.game.TilesGame;

interface Tile {

  int getX();

  int getY();

  void move(int speed);

  boolean isTouch();

  void setTouch(boolean touch);
}
