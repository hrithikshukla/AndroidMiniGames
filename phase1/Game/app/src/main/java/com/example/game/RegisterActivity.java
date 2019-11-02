package com.example.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Save.AccountsManager;

public class RegisterActivity extends AppCompatActivity {

  EditText username;
  EditText password;
  Button registerButton;
  AccountsManager accountsManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    username = (EditText) findViewById(R.id.username);
    password = (EditText) findViewById(R.id.password);
    registerButton = (Button) findViewById(R.id.button2);
    this.accountsManager = (AccountsManager) getIntent().getSerializableExtra("AccountsManager");
    System.out.println("m");
  }

  public void register(View view) {
    username = (EditText) findViewById(R.id.username);
    password = (EditText) findViewById(R.id.password);
    if (accountsManager.createUser(
        username.getText().toString().trim(), password.getText().toString().trim())) {
      Intent loginIntent = new Intent(RegisterActivity.this, com.example.game.LoginActivity.class);
      startActivity(loginIntent);
    }
    else {
      Toast.makeText(getApplicationContext(), "Invalid Registration", Toast.LENGTH_LONG).show();
    }
  }
}
