package com.example.game;

import android.content.Intent;
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
    }

    public void register(View view) {

        String usernameString = username.getText().toString().trim();
        String passwordString = password.getText().toString().trim();

        // Empty usernames are illegal an the user is informed.
        if (usernameString.equals("")) {
            Toast.makeText(getApplicationContext(), "Input a username", Toast.LENGTH_LONG).show();
        }

        // Empty passwords are illegal and the user is informed.
        else if (passwordString.equals("")) {
            Toast.makeText(getApplicationContext(), "Input a password", Toast.LENGTH_LONG).show();
        } else if (accountsManager.createUser(usernameString, passwordString)) {
            // Informs the user that their account is created.
            Toast.makeText(getApplicationContext(), String.format("New user %s created", usernameString), Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(RegisterActivity.this, com.example.game.LoginActivity.class);
            startActivity(loginIntent);
        }
        // Informs the user that their input username is already an existing user.
        else {
            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
        }
    }
}
