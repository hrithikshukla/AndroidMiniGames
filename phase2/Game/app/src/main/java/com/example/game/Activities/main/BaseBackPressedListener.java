package com.example.game.Activities.main;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.game.R;

public class BaseBackPressedListener implements OnBackPressedListener {
    private final FragmentActivity activity;

    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack() {
        activity.findViewById(R.id.frame).setClickable(false);
        activity.recreate();
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
