package com.example.game.Activities.main;

import androidx.fragment.app.FragmentActivity;

import com.example.game.R;

public class BaseBackPressedListener implements OnBackPressedListener {
  // The concrete back press listener

  // The activity that we return to
  private final FragmentActivity activity;

  /** @param activity the activity that is returned to */
  BaseBackPressedListener(FragmentActivity activity) {
    this.activity = activity;
  }

  @Override
  public void doBack() {
    // Recreate new activity and close the current one, with no transition
    activity.findViewById(R.id.frame).setClickable(false);
    activity.startActivity(activity.getIntent());
    activity.finish();
    activity.overridePendingTransition(0, 0);
  }
}
