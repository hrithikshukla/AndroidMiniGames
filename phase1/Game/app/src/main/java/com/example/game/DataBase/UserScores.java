package com.example.game.DataBase;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "Scores_table")
public class UserScores {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ForeignKey(entity = UserAccount.class, parentColumns = "userName",
                childColumns = "userName", onDelete = ForeignKey.CASCADE)
    private String userName;

    private int score;
    @ColumnInfo(name = "game_type")
    private String gameType;

    public UserScores(String userName, int score, String gameType) {
        this.userName = userName;
        this.score = score;
        this.gameType = gameType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getGameType() {
        return gameType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
