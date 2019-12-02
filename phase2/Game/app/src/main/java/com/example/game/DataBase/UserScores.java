package com.example.game.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Scores_table")
/**
 * An entity set of user scores include username, score and time spent in game; Uses and user scores
 * id as a primary key to reduce cost of access
 */
public class UserScores {

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ForeignKey(
      entity = UserAccount.class,
      parentColumns = "username",
      childColumns = "username",
      onDelete = ForeignKey.CASCADE)
  private String username;

  private int score;
  private int timeSpent;

  @ColumnInfo(name = "game_type")
  private String gameType;

  /**
   * @param username: Username for user score
   * @param score: Score for the game
   * @param gameType: specifies which game the score it's for
   * @param timeSpent: time spent playing the game
   */
  public UserScores(String username, int score, String gameType, int timeSpent) {
    this.username = username;
    this.score = score;
    this.gameType = gameType;
    this.timeSpent = timeSpent;
  }

  int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public int getScore() {
    return score;
  }

  void setScore(int score) {
    this.score = score;
  }

  String getGameType() {
    return gameType;
  }

  void setGameType(String gameType) {
    this.gameType = gameType;
  }

  void setUserName(String userName) {
    this.username = userName;
  }

  int getTimeSpent() {
    return timeSpent;
  }

  void setTimeSpent(int timeSpent) {
    this.timeSpent = timeSpent;
  }
}
