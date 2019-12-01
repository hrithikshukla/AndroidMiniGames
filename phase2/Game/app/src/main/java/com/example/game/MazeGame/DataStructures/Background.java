package com.example.game.MazeGame.DataStructures;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

import java.util.HashMap;

/** Represents the images of the background elements of the Maze game. */
public class Background {

  /** Black background of the game. */
  private Bitmap background;

  /** Arrow buttons of the game. */
  private HashMap<String, Bitmap> arrowSquares;

  /**
   * Constructs the bitmaps of the Background elements
   *
   * @param screenX - maximum x-value of the screen
   * @param screenY - maximum y-value of the screen
   * @param res - resources folder
   */
  public Background(int screenX, int screenY, Resources res) {
    createBackGroundBitMap(screenX, screenY, res);
    createArrowsBitMap(res);
  }

  /**
   * Creates the background bitmap.
   *
   * @param screenX - maximum x-value of the screen
   * @param screenY - maximum y-value of the screen
   * @param res - resources folder
   */
  private void createBackGroundBitMap(int screenX, int screenY, Resources res) {
    this.background = BitmapFactory.decodeResource(res, R.drawable.background);
    background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
  }

  /**
   * Creates the arrow button bitmaps.
   *
   * @param res - resources folder
   */
  private void createArrowsBitMap(Resources res) {
    Bitmap leftArrow = BitmapFactory.decodeResource(res, R.drawable.left_arrow);
    Bitmap rightArrow = BitmapFactory.decodeResource(res, R.drawable.right_arrow);
    Bitmap upArrow = BitmapFactory.decodeResource(res, R.drawable.up_arrow);
    Bitmap downArrow = BitmapFactory.decodeResource(res, R.drawable.down_arrow);

    Bitmap ref = leftArrow;
    int SCALE_FACTOR = 3; // Rescale the dimensions of the image by this factor.

    int arrowSideLength = ref.getWidth() / SCALE_FACTOR;

    leftArrow = Bitmap.createScaledBitmap(leftArrow, arrowSideLength, arrowSideLength, false);
    rightArrow = Bitmap.createScaledBitmap(rightArrow, arrowSideLength, arrowSideLength, false);
    upArrow = Bitmap.createScaledBitmap(upArrow, arrowSideLength, arrowSideLength, false);
    downArrow = Bitmap.createScaledBitmap(downArrow, arrowSideLength, arrowSideLength, false);

    arrowSquares = new HashMap<>();

    this.arrowSquares.put("left", leftArrow);
    this.arrowSquares.put("right", rightArrow);
    this.arrowSquares.put("up", upArrow);
    this.arrowSquares.put("down", downArrow);
  }

  /**
   * Getter for the background bitmap.
   *
   * @return - bitmap of the background
   */
  public Bitmap getBackground() {
    return background;
  }

  /**
   * Getter for the arrow button bitmap.
   *
   * @param arrow - string of the desired arrow button
   * @return - bitmap corresponding to the desired arrow
   */
  public Bitmap getArrow(String arrow) {
    return arrowSquares.get(arrow);
  }
}
