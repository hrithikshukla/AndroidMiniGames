package com.example.game.TilesGame;

class TileFactory {

  TileFactory() {}

  KeyTile getKeyTile(int x, int y) {
    return new KeyTile(x, y);
  }

  DangerTile getDangerTile(int x, int y) {
    return new DangerTile(x, y);
  }
}
