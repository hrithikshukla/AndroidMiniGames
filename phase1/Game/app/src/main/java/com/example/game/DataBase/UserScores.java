package com.example.game.DataBase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "Scores_table")
public class UserScores {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = UserAccount.class, parentColumns = "userName",
                childColumns = "userName", onDelete = ForeignKey.CASCADE)
    private String userName;

    private int score;

    private String gameType;



}
