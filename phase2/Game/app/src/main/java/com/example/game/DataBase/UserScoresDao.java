package com.example.game.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.game.DataBase.POJO.UserAverageScore;
import com.example.game.DataBase.POJO.UserAverageTime;
import com.example.game.DataBase.POJO.UserHighScore;
import com.example.game.DataBase.POJO.UserMinTime;

@Dao
public interface UserScoresDao {
  @Insert
  void insert(UserScores userScores);

  @Update
  void update(UserScores userScores);

  @Delete
  void delete(UserScores userScores);

  // Call these methods to get high score of MAZE_GAME, TAPIOCA_GAME, TILES_GAME
  @Query(
      "SELECT userName, max(score) as max from scores_table WHERE userName LIKE :name "
          + "AND game_type LIKE :game_type LIMIT 1")
  public LiveData<UserHighScore> getUserHighScore(String name, String game_type);

  // Call these method to get average score of MAZE_GAME, TAPIOCA_GAME, TILES_GAME
  @Query(
      "SELECT userName, avg(score) as avg from scores_table WHERE userName LIKE :name "
          + "AND game_type LIKE :game_type GROUP BY userName, game_type LIMIT 1")
  public LiveData<UserAverageScore> getUserAvgScore(String name, String game_type);

  // Call these methods to get min time taken of MAZE_GAME, TAPIOCA_GAME, TILES_GAME in seconds
  @Query(
          "SELECT userName, min(timeSpent) as min from scores_table WHERE userName LIKE :name "
                  + "AND game_type LIKE :game_type LIMIT 1")
  public LiveData<UserMinTime> getUserMinTime(String name, String game_type);

  // Call these methods to get the average time spent in each game
  @Query(
          "SELECT userName, avg(timeSpent) as avg from scores_table WHERE userName LIKE :name "
                  + "AND game_type LIKE :game_type GROUP BY userName, game_type LIMIT 1")
  public LiveData<UserAverageTime> getUserAvgTime(String name, String game_type);



}
