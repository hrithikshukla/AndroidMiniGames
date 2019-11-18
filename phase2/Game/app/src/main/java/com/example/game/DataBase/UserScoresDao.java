package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserScoresDao {
    @Insert
    void insert(UserScores userScores);

    @Update
    void update(UserScores userScores);

    @Delete
    void delete(UserScores userScores);
}
