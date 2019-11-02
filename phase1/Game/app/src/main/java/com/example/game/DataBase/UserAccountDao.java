package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserAccountDao {

  @Insert
  void insert(UserAccount userAccount);

  @Update
  void update(UserAccount userAccount);

  @Delete
  void delete(UserAccount userAccount);
}
