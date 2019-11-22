package com.example.game;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public abstract class GameActivity extends AppCompatActivity {
  public User usr;

  protected void switchToGameOverActivity(Context context) {
    Intent intent = new Intent(context, GameOverActivity.class);
    usr.getUserData().setPrefs(null);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }
}
