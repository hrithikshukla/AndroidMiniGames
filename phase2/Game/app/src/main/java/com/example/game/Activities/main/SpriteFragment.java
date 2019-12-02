package com.example.game.Activities.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// A fragment where the user can purchase a character sprite
public class SpriteFragment extends DialogFragment {

  // The numer of the image
  private int imageNum;
  // The price of the character
  private int price;

  // The imageId of the image
  private int imageId;

  // The base activity that this came from
  private ShopActivity activity;
  // The user repository database
  private UserRepository userRepository;
  // List of characters owned by the user, by imageId
  private List<Integer> ownedChars;

  /**
   * @param imageNum the R.drawable number of the image
   * @param price the price of the image in coins
   * @param imageId the R.id.char_ number of the image
   * @param username the username of the user
   */
  SpriteFragment(int imageNum, int price, int imageId, String username) {
    this.imageNum = imageNum;
    this.price = price;
    this.imageId = imageId;
    this.userRepository = new UserRepository(getActivity(), username);
    ownedChars = userRepository.getUserCollectibles();
  }

  /**
   * @param inflater instantiates xml file into corresponding view objects
   * @param container contains children views, base class for layouts and view contains
   * @param savedInstanceState save state of application
   * @return returns View that is created
   */
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    activity = (ShopActivity) getActivity();
    assert activity != null;
    activity.setOnBackPressedListener(new BaseBackPressedListener(activity));
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_sprite, container, false);
  }

  /**
   * @param view the view that the user sees when viewing a character
   * @param savedInstanceState the save state of application
   */
  @SuppressLint("SetTextI18n")
  @Override
  public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
    final ImageView imageView = getView().findViewById(R.id.character);
    // Assign image to imageView
    imageView.setImageResource(imageNum);

    // Set the cancel button's actions
    Button cancel = getView().findViewById(R.id.cancel);
    cancel.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            exitFragment();
          }
        });

    // Set the purchase button's actions
    Button purchase = getView().findViewById(R.id.purchase);
    purchase.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (!ownedChars.contains(imageId)) {
              if (userRepository.getUserAmount() < price) {
                // user does not have enough funds
                Toast t = Toast.makeText(activity, R.string.insufficientFunds, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
              } else {
                userRepository.updateUserAmount(-price);
                userRepository.addUserCollectible(imageId);
                // User makes purchase
                Toast t = Toast.makeText(activity, R.string.successfulPurchase, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                exitFragment();
              }

            } else {
              Toast t = Toast.makeText(activity, R.string.alreadyOwn, Toast.LENGTH_SHORT);
              t.setGravity(Gravity.CENTER, 0, 0);
              t.show();
            }
          }
        });

    // Set description of image
    TextView description = getView().findViewById(R.id.itemDescription);
    description.setText(
        description.getText().toString() + price + " " + getString(R.string.coins) + "?");
  }

  // Exit fragment and update activity with no transition
  private void exitFragment() {
    activity.findViewById(R.id.frame).setClickable(false);
    activity.startActivity(activity.getIntent());
    activity.finish();
    activity.overridePendingTransition(0, 0);
  }
}
