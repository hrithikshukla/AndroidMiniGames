package com.example.game.TapiocaLauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.SurfaceView;

@SuppressWarnings("ClickableViewAccessibility")

// View part of the MVC, broken into 2 parts --VisualView and InputView
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;

    private VisualView visualView; // Where all visuals are rendered
    private InputView inputView; // Where all input is sent to and observed by the GameController

    public GameView(Context context, int screenX, int screenY) {

        super(context);
        SharedPreferences prefs = context.getSharedPreferences("game", Context.MODE_PRIVATE);
        visualView = new VisualView(screenX, screenY, getResources(), getHolder(), context);
        inputView = new InputView();
    }

    @Override
    public void run() {

        while (isPlaying) {
            inputView.update(); // Tells inputView to update the gameController to update the gameModel
            visualView.draw(); // Tells visualView to render the updated gameModel
            sleep(); // ensures this happens only 60 times a second
        }
    }

    // sleeps 17ms before thread, making the program run 60 times a second
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // sends input to inputView to deal with
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_UP) {
            inputView.screenTouched(event);
        }
        return true;
    }

    public VisualView getVisualView() {
        return visualView;
    }

    public InputView getInputView() {
        return inputView;
    }
}
