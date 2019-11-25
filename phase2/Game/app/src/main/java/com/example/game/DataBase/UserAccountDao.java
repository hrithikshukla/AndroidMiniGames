package com.example.game.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserAccountDao {

  @Insert
  public void insert(UserAccount userAccount);

  @Update
  public void update(UserAccount userAccount);

  @Delete
  public void delete(UserAccount userAccount);

  // Call this method if you want to find username is taken or user entered a correct username
  @Query("SELECT * FROM Accounts_table WHERE userName LIKE :name")
  public List<UserAccount> findUserAccountByUsername(String name);

  // Call this method to login
  @Query("SELECT * FROM Accounts_table WHERE userName LIKE :name AND password like :pass")
  public List<UserAccount> findUserCredentials(String name, String pass);

  @Query("SELECT * FROM Accounts_table")
  public LiveData<List<UserAccount>> getAllUsers();
}
