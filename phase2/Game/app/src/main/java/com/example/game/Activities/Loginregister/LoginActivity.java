package com.example.game.Activities.Loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Activities.main.MainActivity;
import com.example.game.DataBase.UserRepository;
import com.example.game.R;

public class LoginActivity extends AppCompatActivity {
  EditText username;
  EditText password;
  Button loginButton;
  Button registerButton;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loginButton = findViewById(R.id.loginButton);
    registerButton = findViewById(R.id.registerButton);
  }

  public void goToRegisterActivity(View view) {
    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
    startActivity(registerIntent);
  }

  public void goToMainActivity(View view) {
    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    String u = username.getText().toString().trim();
    String p = password.getText().toString().trim();
    UserRepository userRepository = new UserRepository(this, u, p);
    if (userRepository.validateCredentials()) {
      Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
      mainActivityIntent.putExtra("USERNAME", u);
      startActivity(mainActivityIntent);
    } else {
      Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
    }
  }
}
