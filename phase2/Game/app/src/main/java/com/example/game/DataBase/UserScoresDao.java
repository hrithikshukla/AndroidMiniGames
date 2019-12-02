package com.example.game.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
      "SELECT max(score) as max from scores_table WHERE userName LIKE :name "
          + "AND game_type LIKE :game_type LIMIT 1")
  public int getUserHighScore(String name, String game_type);

  // Call these method to get average score of MAZE_GAME, TAPIOCA_GAME, TILES_GAME
  @Query(
      "SELECT avg(score) as avg from scores_table WHERE userName LIKE :name "
          + "AND game_type LIKE :game_type GROUP BY userName, game_type LIMIT 1")
  public double getUserAvgScore(String name, String game_type);

  // Call these methods to get min time taken of MAZE_GAME, TAPIOCA_GAME, TILES_GAME in seconds
  @Query(
          "SELECT min(timeSpent) as min from scores_table WHERE userName LIKE :name "
                  + "AND game_type LIKE :game_type LIMIT 1")
  public int getUserMinTime(String name, String game_type);

  // Call these methods to get max time taken of MAZE_GAME, TAPIOCA_GAME, TILES_GAME in seconds
  @Query(
          "SELECT max(timeSpent) as max from scores_table WHERE userName LIKE :name "
                  + "AND game_type LIKE :game_type LIMIT 1")
  public int getUserMaxTime(String name, String game_type);


  // Call these methods to get the average time spent in each game
  @Query(
          "SELECT avg(timeSpent) as avg from scores_table WHERE userName LIKE :name "
                  + "AND game_type LIKE :game_type GROUP BY userName, game_type LIMIT 1")
  public int getUserAvgTime(String name, String game_type);

  @Query("DELETE FROM scores_table WHERE userName LIKE :name")
  public void deleteUserScores(String name);

}
