package com.example.game.MazeGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.MazeGame.DataStructures.Cell;
import com.example.game.MazeGame.DataStructures.Sprites;
import com.example.game.R;

import java.util.HashMap;

/**
 * Represents the bitmaps of the tiles of the maze. A tile is a wall, a floor, a player, or a
 * collectible.
 */
class Tile {
  /** Tile bitmaps are stored by their cell name. */
  private HashMap<Cell, Bitmap> tiles;

  /** Side length of any tile bitmap. */
  private int sideLength;

  /**
   * Creates all Tile bitmaps of the maze.
   *
   * @param res - resources folder
   * @param name - Enum of the sprite the player is using
   */
  Tile(Resources res, Sprites name) {

    // Unscaled bitmaps.
    Bitmap wall = BitmapFactory.decodeResource(res, R.drawable.brick_wall);
    Bitmap floor = BitmapFactory.decodeResource(res, R.drawable.floor);
    Bitmap collectible = BitmapFactory.decodeResource(res, R.drawable.score_modifier);

    Bitmap player, playerAtExit;

    switch (name) {
      case DOLLAR:
        player = BitmapFactory.decodeResource(res, R.drawable.dollar);
        playerAtExit = BitmapFactory.decodeResource(res, R.drawable.dollar_exit);
        break;
      case HASHTAG:
        player = BitmapFactory.decodeResource(res, R.drawable.hashtag);
        playerAtExit = BitmapFactory.decodeResource(res, R.drawable.hashtag_exit);
        break;
      case PERCENT:
        player = BitmapFactory.decodeResource(res, R.drawable.percent);
        playerAtExit = BitmapFactory.decodeResource(res, R.drawable.percent_exit);
        break;
      default:
        player = BitmapFactory.decodeResource(res, R.drawable.at);
        playerAtExit = BitmapFactory.decodeResource(res, R.drawable.at_exit);
        break;
    }

    Bitmap exit = BitmapFactory.decodeResource(res, R.drawable.exit);

    // Reference tile for the side length of any tile.
    Bitmap ref = wall;

    // Rescale the dimensions of the bitmap by this factor.
    int SCALE_FACTOR = 8;

    this.sideLength = ref.getWidth() / SCALE_FACTOR;

    this.tiles = new HashMap<>();
    tiles.put(Cell.WALL, Bitmap.createScaledBitmap(wall, sideLength, sideLength, false));
    tiles.put(Cell.FLOOR, Bitmap.createScaledBitmap(floor, sideLength, sideLength, false));
    tiles.put(
        Cell.COLLECTIBLE, Bitmap.createScaledBitmap(collectible, sideLength, sideLength, false));
    tiles.put(Cell.PLAYER, Bitmap.createScaledBitmap(player, sideLength, sideLength, false));
    tiles.put(
        Cell.PLAYER_AT_EXIT,
        Bitmap.createScaledBitmap(playerAtExit, sideLength, sideLength, false));
    tiles.put(Cell.EXIT, Bitmap.createScaledBitmap(exit, sideLength, sideLength, false));
  }

  /**
   * @param tile - tile name of the bitmap to be returned
   * @return - a bitmap corresponding to the tile name
   */
  Bitmap getTile(Cell tile) {
    return tiles.get(tile);
  }

  /**
   * Getter for the side length of any tile bitmap
   *
   * @return side length of a tile bitmap
   */
  int getSideLength() {
    return sideLength;
  }
}
