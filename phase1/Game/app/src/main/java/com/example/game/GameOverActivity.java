package com.example.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class GameOverActivity extends AppCompatActivity {
  User usr;
  Button goToHomePage;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    // Set the theme.
    usr = (User) getIntent().getSerializableExtra("UserObject");
    String username = usr.getUsername();
    SharedPreferences mSettings = this.getSharedPreferences("Settings", MODE_PRIVATE);
    ThemeManager.setTheme(GameOverActivity.this, mSettings.getInt(username + "theme", 0));

    super.onCreate(savedInstanceState);
    setContentView(R.layout.gameover_activity);

    goToHomePage = findViewById(R.id.homepagebutton);
  }

  public void goToHomePage(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("UserObject", usr);
    startActivity(intent);
  }
}
