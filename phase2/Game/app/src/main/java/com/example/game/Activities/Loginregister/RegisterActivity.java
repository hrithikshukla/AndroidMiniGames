package com.example.game.Activities.Loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.DataBase.UserRepository;
import com.example.game.R;

public class RegisterActivity extends AppCompatActivity {

  EditText username;
  EditText password;
  Button registerButton;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    registerButton = findViewById(R.id.button2);
  }

  public void register(View view) {

    String usernameString = username.getText().toString().trim();
    String passwordString = password.getText().toString().trim();

    // Empty usernames are illegal an the user is informed.
    if (usernameString.equals("")) {
      Toast.makeText(getApplicationContext(), "Input a username", Toast.LENGTH_LONG).show();
      return;
    }

    // Empty passwords are illegal and the user is informed.
    else if (passwordString.equals("")) {
      Toast.makeText(getApplicationContext(), "Input a password", Toast.LENGTH_LONG).show();
      return;
    }

    UserRepository userRepository = new UserRepository(this, usernameString, passwordString);
    if (userRepository.addUser()) {
      Toast.makeText(
              getApplicationContext(),
              String.format("New user %s created", usernameString),
              Toast.LENGTH_LONG)
          .show();
      Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
      loginIntent.putExtra("USERNAME", usernameString);
      startActivity(loginIntent);
    }
    //   Informs the user that their input username is already an existing user.
    else {
      Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
    }
  }
}
