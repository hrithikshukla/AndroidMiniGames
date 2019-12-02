package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
/**
 * User Collectibles Data Access interface, contains queries to retrieve information about on User
 * Collectibles
 */
public interface UserCollectiblesDao {

  @Insert
  void insert(UserCollectibles userCollectibles);

  @Update
  void update(UserCollectibles userCollectibles);

  @Delete
  void delete(UserCollectibles userCollectibles);

  @Query("SELECT * FROM C_table WHERE username LIKE :username")
  List<UserCollectibles> getUserCollectibles(String username);
}
