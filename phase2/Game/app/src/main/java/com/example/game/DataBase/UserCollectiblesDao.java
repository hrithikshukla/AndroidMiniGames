package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserCollectiblesDao {

    @Insert
    public void insert(UserCollectibles userCollectibles);

    @Update
    public void update(UserCollectibles userCollectibles);

    @Delete
    public void delete(UserCollectibles userCollectibles);

    @Query("SELECT character_id FROM Collectibles_table WHERE userName like :username")
    public List<Integer> getUserCollectibles(String username);


}
