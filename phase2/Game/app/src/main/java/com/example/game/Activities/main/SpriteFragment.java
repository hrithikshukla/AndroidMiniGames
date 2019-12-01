package com.example.game.Activities.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.R;

public class SpriteFragment extends DialogFragment {

    private int image;
    private int price;

    private ShopActivity activity;

    SpriteFragment(int image, int price) {
        this.image = image;
        this.price = price;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (ShopActivity) getActivity();
        activity.setOnBackPressedListener(new BaseBackPressedListener(activity));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sprite, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        getView().setBackgroundColor(Color.WHITE);
        final ImageView imageView = getView().findViewById(R.id.character);
        imageView.setImageResource(image);

        Button cancel = getView().findViewById(R.id.cancel);
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.findViewById(R.id.frame).setClickable(false);
                        activity.getSupportFragmentManager().popBackStack();
                    }
                });

        Button purchase = getView().findViewById(R.id.purchase);
        purchase.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (usr.balance < price) {
//                            // prirnt error message
//                        } else {
//                            usr.balance.subtract(price);
//                            // add to users owned items
//                        }
                    }
                });

        TextView description = getView().findViewById(R.id.itemDescription);
        description.setText(description.getText() + "" + price + " coins?");
    }
}
