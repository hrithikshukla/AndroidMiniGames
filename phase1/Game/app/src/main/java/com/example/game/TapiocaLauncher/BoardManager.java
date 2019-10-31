package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.res.Resources;

import com.example.game.TapiocaLauncher.Ball;

import java.util.ArrayList;
import java.util.List;

// Creates layouts for the tapiocas on screen. So far only 2 layouts.
class BoardManager extends ClassLoader {

  private List<Ball> bubbles;
  private Resources resources;

  BoardManager(Resources res) {
    this.resources = res;
    bubbles = new ArrayList<>();
  }

  BoardManager() {
    bubbles = new ArrayList<>();
  }

  // Creates layout depending on input number
  List<Ball> fillBoard(int layout) {
    if (layout == 1) { // 6 rows and 6 columns of tapioca with 1 HP
      for (int j = 0; j < 6; j++) {
        for (int i = 0; i < 6; i++) {
          Ball b = new Ball(50 + (170 * i), 50 + (140 * j), resources, 1);
          bubbles.add(b);
        }
      }
    }
    if (layout >= 2) { // 6 rows and 6 columns of tapioca with 2 HP
      for (int j = 0; j < 6; j++) {
        for (int i = 0; i < 6; i++) {
          Ball b = new Ball(50 + (170 * i), 50 + (140 * j), resources, 2);
          bubbles.add(b);
        }
      }
    }
    return bubbles;
  }
}
