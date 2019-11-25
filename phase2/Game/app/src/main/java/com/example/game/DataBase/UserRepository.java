package com.example.game.DataBase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.game.DataBase.POJO.UserAverageScore;
import com.example.game.DataBase.POJO.UserHighScore;

import java.util.List;

public class UserRepository {
    private UserAccountDao userAccountDao;
    private LiveData<List<UserAccount>> allUsers;
    private UserScoresDao userScoresDao;
    private LiveData<List<UserScores>> allUsersScore;
    private LiveData<UserHighScore> userHighScoreLiveData;
    private LiveData<UserAverageScore> userAverageScoreLiveData;


    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        userAccountDao = db.userAccountDao();
        userScoresDao = db.userScoresDao();
    }
}
