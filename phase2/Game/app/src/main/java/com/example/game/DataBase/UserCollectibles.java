package com.example.game.DataBase;

import android.util.SparseBooleanArray;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.game.R;

@Entity(primaryKeys = {"userName", "character_id"}, tableName = "Collectibles_table")
public class UserCollectibles {

    @NonNull
    private String userName;
    private int character_id;


    public UserCollectibles(String userName, int character_id) {
        this.userName = userName;
        this.character_id = character_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }
}
