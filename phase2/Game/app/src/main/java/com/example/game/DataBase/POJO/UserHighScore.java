package com.example.game.DataBase.POJO;

import androidx.room.ColumnInfo;

public class UserHighScore {

  @ColumnInfo(name = "userName")
  public String username;

  @ColumnInfo(name = "max")
  public int highScore;
}
