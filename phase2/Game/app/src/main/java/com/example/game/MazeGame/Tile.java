package com.example.game.MazeGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.R;

/** Class representing the tiles of the maze. A tile is a wall, a floor, or a player. */
class Tile {

  private Bitmap wall, floor, collectable, player, playerAtExit;

  // Side length of any Tile bitmap.
  private int sideLength;

  Tile(Resources res) {

    Bitmap tmpWall = BitmapFactory.decodeResource(res, R.drawable.brick_wall);
    Bitmap tmpfloor = BitmapFactory.decodeResource(res, R.drawable.floor);
    Bitmap tmpCollectable = BitmapFactory.decodeResource(res, R.drawable.score_modifier);
    Bitmap tmpPlayer = BitmapFactory.decodeResource(res, R.drawable.character);
    Bitmap tmpCharacterAtExit = BitmapFactory.decodeResource(res, R.drawable.character_at_exit);

    // Reference tile for the side length of any tile.
    Bitmap ref = tmpWall;

    // Rescale the dimensions of the image by this factor.
    int SCALE_FACTOR = 8;

    this.sideLength = ref.getWidth() / SCALE_FACTOR;

    // Rescale the wall and floor bitmaps to fit on the phone screen.
    this.wall = Bitmap.createScaledBitmap(tmpWall, sideLength, sideLength, false);
    this.floor = Bitmap.createScaledBitmap(tmpfloor, sideLength, sideLength, false);
    this.collectable = Bitmap.createScaledBitmap(tmpCollectable, sideLength, sideLength, false);
    this.player = Bitmap.createScaledBitmap(tmpPlayer, sideLength, sideLength, false);
    this.playerAtExit =
        Bitmap.createScaledBitmap(tmpCharacterAtExit, sideLength, sideLength, false);
  }

  /**
   * Exit Tile is represented as a normal floor Tile until the player is at its location.
   *
   * @param tile - integer representing the tile to be returned
   */
  Bitmap getTile(Cell tile) {
    switch (tile) {
      case FLOOR:
        return floor;

      case WALL:
        return wall;

      case PLAYER:
        return player;

      case EXIT:
        return floor;

      case PLAYER_AT_EXIT:
        return playerAtExit;

        case COLLECTIBLE:
        return collectable;

      default:
        return wall;
    }
  }

  int getSideLength() {
    return sideLength;
  }
}
