package com.example.game.DataBase.POJO;

import androidx.room.ColumnInfo;

public class UserAverageScore {

  @ColumnInfo(name = "userName")
  public String username;

  @ColumnInfo(name = "avg")
  public int averageScore;
}
