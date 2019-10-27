package com.example.game.MazeGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

class Background {

    private int x = 0, y = 0;

    Bitmap background;

    public Background(int screenX, int screenY, Resources res) {
        this.background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Bitmap getBackground() {
        return background;
    }
}
