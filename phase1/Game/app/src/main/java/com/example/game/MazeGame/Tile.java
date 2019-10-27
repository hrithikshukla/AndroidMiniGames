package com.example.game.MazeGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

/**
 * Class representing the tiles of the maze. A tile is a wall, a floor, or a character.
 */
public class Tile {

    private Bitmap wall, floor, character;

    // Side length of any Tile bitmap.
    private int sideLength;

    public Tile(Resources res) {

        Bitmap tmpWall = BitmapFactory.decodeResource(res, R.drawable.brick_wall);
        Bitmap tmpfloor = BitmapFactory.decodeResource(res, R.drawable.floor);
        Bitmap tmpCharacter = BitmapFactory.decodeResource(res, R.drawable.character);

        // Reference tile for the side length of any tile.
        Bitmap ref = tmpWall;

        // Rescale the dimensions of the image by this factor.
        int SCALE_FACTOR = 8;

        this.sideLength = ref.getWidth() / SCALE_FACTOR;

        // Rescale the wall and floor bitmaps to fit on the phone screen.
        this.wall = Bitmap.createScaledBitmap(tmpWall, sideLength, sideLength, false);
        this.floor = Bitmap.createScaledBitmap(tmpfloor, sideLength, sideLength, false);
        this.character = Bitmap.createScaledBitmap(tmpCharacter, sideLength, sideLength, false);
    }

    /**
     * @param tile - integer representing the tile to be returned
     */
    Bitmap getTile(int tile) {
        switch (tile) {
            case 0:
                return floor;

            case 1:
                return wall;

            case 2:
                return character;

            default:
                return wall;
        }
    }

    int getSideLength() {
        return sideLength;
    }

}
