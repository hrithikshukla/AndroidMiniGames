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
import com.example.game.DataBase.UserAccount;
import com.example.game.DataBase.UserRepository;
import com.example.game.R;
import com.example.game.Save.AccountsManager;
import com.example.game.Save.JSONData;
import com.example.game.Save.User;

import java.io.File;

public class LoginActivity extends AppCompatActivity {
  EditText username;
  EditText password;
  Button loginButton;
  Button registerButton;
  AccountsManager accountsManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    loginButton = (Button) findViewById(R.id.loginButton);
    registerButton = (Button) findViewById(R.id.registerButton);

    // Open .json file; this is an empty file need to fix later
    //        File file = new File("/Users/tom_kan/Documents/School/Second Year/CSC
    // 207/group_0596/phase1/group_0596/phase1/Game/app/src/main/java/com/example/game/Save/save.json");
    File file = new File(this.getFilesDir(), "save.json");

    // Create JSONData object
    JSONData jsonData = new JSONData(file);

    // Create AccountManager object
    this.accountsManager = new AccountsManager(jsonData);
  }

  public void goToRegisterActivity(View view) {
    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
    registerIntent.putExtra("AccountsManager", this.accountsManager);
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

  //    User usr =
  //        accountsManager.login(
  //            username.getText().toString().trim(), password.getText().toString().trim());
  //    if (usr != null) {
  //      Intent mainActivityIntent =
  //          new Intent(LoginActivity.this, MainActivity.class);
  //      mainActivityIntent.putExtra("UserObject", usr);
  //      startActivity(mainActivityIntent);
  //    } else {
  //      Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
  //    }
  //  };
}
