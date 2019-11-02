package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

// The background to be drawn in the tapioca game
class Background {

    // Coordinates to represent the top left of the background
    private int x, y;

    // The bitmap image of the background
    private Bitmap background;

    Background(int screenX, int screenY, Resources res) {
        x = 0;
        y = 0;
        background = BitmapFactory.decodeResource(res, R.drawable.milktea);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Bitmap getBackground() {
        return background;
    }
}
