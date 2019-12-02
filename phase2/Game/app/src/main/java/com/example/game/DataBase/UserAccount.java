package com.example.game.DataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accounts_table")
/**
 * An entity set of user accounts include username and password; keeps track of user credentials and
 * amount they have
 */
public class UserAccount {
  @PrimaryKey @NonNull private String username;
  private String password;
  private int amount;

  /**
   * A user account entity
   *
   * @param username: username of user, primary key
   * @param password: password of user
   */
  UserAccount(@NonNull String username, String password) {
    this.username = username;
    this.password = password;
  }

  @NonNull
  String getUsername() {
    return username;
  }

  String getPassword() {
    return password;
  }

  int getAmount() {
    return amount;
  }

  void setAmount(int amount) {
    this.amount = amount;
  }
}
