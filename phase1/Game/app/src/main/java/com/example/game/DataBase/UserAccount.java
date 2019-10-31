package com.example.game.DataBase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accounts_table")
public class UserAccount {
    @PrimaryKey
    private String userName;
    private String password;

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
