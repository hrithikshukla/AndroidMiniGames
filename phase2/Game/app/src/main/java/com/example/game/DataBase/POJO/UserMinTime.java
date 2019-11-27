package com.example.game.DataBase.POJO;

import androidx.room.ColumnInfo;

public class UserMinTime {

    @ColumnInfo(name = "userName")
    public String username;

    @ColumnInfo(name = "min")
    public int minTime;
}
