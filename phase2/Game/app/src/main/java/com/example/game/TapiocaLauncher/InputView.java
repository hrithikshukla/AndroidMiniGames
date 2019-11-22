package com.example.game.TapiocaLauncher;

import android.util.Log;
import android.view.MotionEvent;

import java.util.Observable;

class InputView extends Observable {

    InputView() {
    }

    // sends motion event to GameController to deal with
    void screenTouched(MotionEvent event) {
        setChanged();
        notifyObservers(event);
        Log.e("", "Screen touched");
    }

    // Signals gameController to update the game
    void update() {
        setChanged();
        notifyObservers(true);
    }
}
