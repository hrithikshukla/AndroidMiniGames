package com.example.game.TapiocaLauncher;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

class Ball{

    int x, y, width, height, radius;
    int centreX, centreY;
    Bitmap orientation1;
    Resources res;
    boolean hit = false;
    int hp;

    Ball (int x, int y, Resources res, int hp){
        if (hp == 1) {
            orientation1 = BitmapFactory.decodeResource(res, R.drawable.brown);
        } else if (hp == 2) {
            orientation1 = BitmapFactory.decodeResource(res, R.drawable.red);
        }
        width = orientation1.getWidth() / 2; // 157
        height = orientation1.getHeight() / 2; //136

        orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
        this.x = x;
        this.y = y;
        radius = width / 2;
        centreX = x + radius;
        centreY = y + radius;
        this.hp = hp;
        this.res = res;
    }

    Bitmap getBall(){
        return orientation1;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (hp == 1) {
            orientation1 = BitmapFactory.decodeResource(res, R.drawable.brown);
        } else if (hp == 2) {
            orientation1 = BitmapFactory.decodeResource(res, R.drawable.red);
        }
        width = orientation1.getWidth() / 2; // 157
        height = orientation1.getHeight() / 2; //136

        orientation1 = Bitmap.createScaledBitmap(orientation1, width, height, false);
        this.x = x;
        this.y = y;
        radius = width / 2;
        centreX = x + radius;
        centreY = y + radius;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;

    }
}
