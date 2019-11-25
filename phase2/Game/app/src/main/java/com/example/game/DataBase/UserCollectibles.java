package com.example.game.DataBase;

import androidx.room.Entity;

@Entity(primaryKeys = {"userName", "characters"})
public class UserCollectibles {

    private String userName;
    private String characters;

    public UserCollectibles(String userName, String characters) {
        this.userName = userName;
        this.characters = characters;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
