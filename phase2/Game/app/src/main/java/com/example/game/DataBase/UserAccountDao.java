package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserAccountDao {

  @Insert
  void insert(UserAccount userAccount);

  @Update
  void update(UserAccount userAccount);

  @Delete
  void delete(UserAccount userAccount);

  // Call this method if you want to find username is taken or user entered a correct username
  @Query("SELECT * FROM Accounts_table WHERE userName LIKE :name")
  public abstract List<UserAccount> findUserAccountByUsername(String name);

  // Call this method to login
  @Query("SELECT * FROM Accounts_table WHERE userName LIKE :name AND password like :pass")
  public abstract List<UserAccount> findUserCredentials(String name, String pass);
}
