package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

public class Background {

    private int x = 0, y = 0;
    private Bitmap background;

    Background(int screenX, int screenY, Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.milktea);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBackground() {
        return background;
    }
}
