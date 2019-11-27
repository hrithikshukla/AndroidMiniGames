package com.example.game.MazeGame.DataStructures;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

import java.util.HashMap;

// Background objects of the maze game.
public class Background {

    // Top left corner coordinate of the background bit map
  private final int x = 0, y = 0;

  // Bitmap of our background png
  private Bitmap background;
    private HashMap<String, Bitmap> arrowSquares;

  public Background(int screenX, int screenY, Resources res) {
    this.background = BitmapFactory.decodeResource(res, R.drawable.background);
    background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

      Bitmap leftArrow = BitmapFactory.decodeResource(res, R.drawable.left_arrow);
      Bitmap rightArrow = BitmapFactory.decodeResource(res, R.drawable.right_arrow);
      Bitmap upArrow = BitmapFactory.decodeResource(res, R.drawable.up_arrow);
      Bitmap downArrow = BitmapFactory.decodeResource(res, R.drawable.down_arrow);


      Bitmap ref = leftArrow;
      // Rescale the dimensions of the image by this factor.
      int SCALE_FACTOR = 3;

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

    // Getters for instance variables

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Bitmap getBackground() {
    return background;
  }

    public Bitmap getArrow(String arrow) {
        return arrowSquares.get(arrow);
    }

}
