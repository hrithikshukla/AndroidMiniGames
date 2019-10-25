package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.game.R;

class Arrow {

    int x, y, width, height;
    Bitmap arrow;
    boolean isMoving;

    Arrow (int loc_X, int loc_y, Resources res) {
        arrow = BitmapFactory.decodeResource(res, R.drawable.arrow);

        width = arrow.getWidth() / 4;
        height = arrow.getHeight() / 4;

        arrow = Bitmap.createScaledBitmap(arrow, width, height, false);

        this.x = loc_X;
        this.y = loc_y - 400;

    }

    Bitmap getArrow() {
        return rotate(90);
    }

    void move () {
    }

    private Bitmap rotate(int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(arrow, 0, 0,arrow.getWidth(), arrow.getHeight(), matrix, true);
    }
}
