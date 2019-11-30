package com.example.game.DataBase;

import android.util.SparseBooleanArray;

import androidx.room.Entity;

import com.example.game.R;

@Entity(primaryKeys = {"userName", "characters"})
public class UserCollectibles {

    private String userName;
    private String characters;
    private SparseBooleanArray ownedChars;

    public UserCollectibles(String userName, String characters) {
        this.userName = userName;
        this.characters = characters;
        this.ownedChars = createCharsMap();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SparseBooleanArray getOwnedChars() {
        return ownedChars;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    private SparseBooleanArray createCharsMap() {
        SparseBooleanArray chars = new SparseBooleanArray();
        chars.put(R.id.char_worm, false);
        chars.put(R.id.char_bird, false);
        chars.put(R.id.char_spider, false);
        chars.put(R.id.char_dog, false);
        chars.put(R.id.char_tree, false);
        chars.put(R.id.char_astro, false);
        chars.put(R.id.char_alien, false);
        chars.put(R.id.char_monster, false);
        chars.put(R.id.char_bat, false);
        chars.put(R.id.char_king, false);
        chars.put(R.id.char_summoner, false);
        chars.put(R.id.char_viking1, false);
        chars.put(R.id.char_viking2, false);
        chars.put(R.id.char_viking3, false);
        chars.put(R.id.char_wizard, false);
        chars.put(R.id.char_archer, false);
        chars.put(R.id.char_knight, false);
        chars.put(R.id.char_samurai, false);
        chars.put(R.id.char_shogun, false);
        return chars;
    }
}
