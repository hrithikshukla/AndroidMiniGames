package com.example.game.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/** The room database for our application */
@Database(
    entities = {UserAccount.class, UserScores.class, UserCollectibles.class},
    version = 4,
    exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

  // Create singleton; so can't create multiple instances
  private static UserDatabase instance;

  /**
   * Use this method to retrieve our database, method body builds it and static makes it a singleton
   * so we have a single copy of database everywhere; data remains consistent
   */
  public static synchronized UserDatabase getInstance(Context context) {
    if (instance == null) {
      instance =
          Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
              .fallbackToDestructiveMigration()
              .build();
    }
    return instance;
  }

  // Call these methods to retrieve our DAO (Data access objects)
  public abstract UserAccountDao userAccountDao();

  public abstract UserScoresDao userScoresDao();

  public abstract UserCollectiblesDao userCollectiblesDao();
}
