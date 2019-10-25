package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

class Ball{

    int x, y, width, height, radius;
    int centreX, centreY;
    Bitmap orientation1;

    Ball (int x, int y, Resources res){
        orientation1 = BitmapFactory.decodeResource(res, R.drawable.brown);

        width = orientation1.getWidth() / 2; // 157
        height = orientation1.getHeight() / 2; //136

        orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
        this.x = x;
        this.y = y;
        radius = width / 2;
        centreX = x + radius;
        centreY = y + radius;

    }

    Bitmap getBall(){
        return orientation1;
    }
}
