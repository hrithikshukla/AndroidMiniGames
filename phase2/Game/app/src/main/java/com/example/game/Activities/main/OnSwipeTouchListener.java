package com.example.game.Activities.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

  // Code based on https://stackoverflow.com/a/24256106/10322608

  private final GestureDetector gestureDetector;

  /**
   * Instantiates a new on swipe touch listener.
   *
   * @param context the context
   */
  OnSwipeTouchListener(Context context) {
    super();
    gestureDetector = new GestureDetector(context, new GestureListener());
  }

  /* (non-Javadoc)
   * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
   */
  @SuppressLint("ClickableViewAccessibility")
  public boolean onTouch(final View view, final MotionEvent motionEvent) {
    return gestureDetector.onTouchEvent(motionEvent);
  }

  /**
   * Gets the gesture detector.
   *
   * @return the gesture detector
   */
  public GestureDetector getGestureDetector() {
    return gestureDetector;
  }

  /** On swipe right. */
  void onSwipeRight() {}

  /** On swipe left. */
  void onSwipeLeft() {}

  /** On swipe top. */
  private void onSwipeTop() {}

  /** On swipe bottom. */
  private void onSwipeBottom() {}

  private final class GestureListener extends SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    /* (non-Javadoc)
     * @see android.view.GestureDetector.SimpleOnGestureListener#onDown(android.view.MotionEvent)
     */
    @Override
    public boolean onDown(MotionEvent e) {
      return true;
    }

    /* (non-Javadoc)
     * @see android.view.GestureDetector.SimpleOnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
     */

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      boolean result = false;
      try {
        float diffY = e2.getRawY() - e1.getRawY();
        float diffX = e2.getRawX() - e1.getRawX();
        if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
          if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffX > 0) {
              onSwipeRight();
            } else {
              onSwipeLeft();
            }
          }
        } else {
          if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) {
              onSwipeBottom();
            } else {
              onSwipeTop();
            }
          }
        }
      } catch (Exception e) {
        System.out.println("uWu");
      }
      return result;
    }
  }
}
