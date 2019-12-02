package com.example.game.DataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(
    primaryKeys = {"username", "character_id"},
    tableName = "C_table")
/**
 * An entity set of User Collectibles; contains what the User owns from shop Keeps track of users'
 * username (super key) and each shop's item unique id
 */
public class UserCollectibles {

  @NonNull private String username;
  private int character_id;

  UserCollectibles(String username, int character_id) {
    this.username = username;
    this.character_id = character_id;
  }

  @NonNull
  String getUsername() {
    return username;
  }

  void setUsername(String username) {
    this.username = username;
  }

  int getCharacter_id() {
    return character_id;
  }

  void setCharacter_id(int character_id) {
    this.character_id = character_id;
  }
}
