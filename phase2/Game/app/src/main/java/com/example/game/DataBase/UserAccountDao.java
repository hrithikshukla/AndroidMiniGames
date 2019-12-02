package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
/**
 * User Account Data Access interface, contains queries to retrieve information about on User
 * Accounts
 */
public interface UserAccountDao {

  @Insert
  void insert(UserAccount userAccount);

  @Update
  void update(UserAccount userAccount);

  @Delete
  void delete(UserAccount userAccount);

  // Call this method if you want to find username is taken or user entered a correct username
  @Query("SELECT * FROM Accounts_table WHERE username LIKE :name")
  UserAccount getUserAccountByUsername(String name);

  // Call this method to login
  @Query("SELECT * FROM Accounts_table WHERE username LIKE :name AND password like :pass")
  UserAccount getUserCredentials(String name, String pass);

  // Call this method to get all users
  @Query("SELECT * FROM Accounts_table")
  List<UserAccount> getAllUsers();
}
