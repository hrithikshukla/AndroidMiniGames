package com.example.game.Activities.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

public class SpriteFragment extends DialogFragment {

    // The numer of the image
    private int imageNum;
    // The price of the character
    private int price;

    // The imageId of the image
    private int imageId;

    private ShopActivity activity;
    private UserRepository userRepository;


    SpriteFragment(int image, int price, int id, String username) {
        this.imageNum = image;
        this.price = price;
        this.imageId = id;
        this.userRepository = new UserRepository(getActivity(), username);
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
        // EditText etFoo = (EditText) view.findViewById(R.imageId.etFoo);
        getView().setBackgroundColor(Color.WHITE);
        final ImageView imageView = getView().findViewById(R.id.character);
        imageView.setImageResource(imageNum);

        Button cancel = getView().findViewById(R.id.cancel);
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitFragment();
                    }
                });

        Button purchase = getView().findViewById(R.id.purchase);
    purchase.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (userRepository.getUserAmount() < price) {
                // Change this to add other language support
              Toast t = Toast.makeText(activity, "LMAO U BROKE", Toast.LENGTH_SHORT);
              t.setGravity(Gravity.CENTER, 0, 0);
              t.show();
            } else {
             userRepository.updateUserAmount(-price);
                userRepository.addUserCollectible(imageId);
                // Change this to add other language support
                Toast t = Toast.makeText(activity, "YOUR PURCHASE WAS SUCCESSFULL", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
              exitFragment();
          }
        });

        TextView description = getView().findViewById(R.id.itemDescription);
        description.setText(description.getText() + "" + price + R.string.coins + "?");
    }

    private void exitFragment() {
        activity.findViewById(R.id.frame).setClickable(false);
        activity.recreate();
        activity.getSupportFragmentManager().popBackStack();
//        activity.overridePendingTransition(0, 0);
    }
}
