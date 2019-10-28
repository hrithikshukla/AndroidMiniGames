package com.example.game.TapiocaLauncher;

import android.content.Context;

import com.example.game.TapiocaLauncher.Ball;

import java.util.ArrayList;
import java.util.List;

class BoardManager extends ClassLoader {

    private List<Ball> bubbles;
    private int screenX, screenY;
    private Context context;

    BoardManager(int screenX, int screenY, Context context) {
        bubbles = new ArrayList<>();
        this.screenX = screenX;
        this.screenY = screenY;
        this.context = context;
    }

    BoardManager() {
        bubbles = new ArrayList<>();
    }

    List<Ball> fillBoard(int layout) {
        if (layout == 1) {
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 6; i++) {
                    Ball b = new Ball(50 + (170 * i), 50 + (140 * j), context.getResources(), 1);
                    bubbles.add(b);
                }
            }
        }
        if (layout >= 2) {
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 6; i++) {
                    Ball b = new Ball(50 + (170 * i), 50 + (140 * j), context.getResources(), 2);
                    bubbles.add(b);
                }
            }
        }
        return bubbles;
    }

//    static boolean intersects(Launcher launcher, Ball ball) {
//        boolean intersects = false;
//        if (distance(launcher.centreX, ball.centreX, launcher.centreY, ball.centreY) <= 2 * launcher.radius) {
//            intersects = true;
//        }
//        return intersects;
//    }
//
//    private static float distance(int x1, int x2, int y1, int y2) {
//        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
//    }
}
