package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.User;

public class GameOverActivity extends AppCompatActivity {
    public User usr;
    Button goToHomePage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usr = (User) getIntent().getSerializableExtra("UserObject");
        setContentView(R.layout.gameover_activity);

        goToHomePage = (Button) findViewById(R.id.homepagebutton);



    }

    public void goToHomePage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("UserObject", usr);
        startActivity(intent);
    }
}
