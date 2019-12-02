package com.example.game.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A repository for our database; use it as an interface for our main application This way we don't
 * have to worry where the data comes form, i.e database, web api
 */
public class UserRepository {

  private UserAccountDao userAccountDao;
  private UserScoresDao userScoresDao;
  private UserCollectiblesDao userCollectiblesDao;
  private String username;
  private String password;

  /**
   * @param context: Where the constructor is called
   * @param password: password user entered
   * @param username : username user entered USE THIS CONSTRUCTOR FOR LOGIN/REGISTRATION
   */
  public UserRepository(Context context, String username, String password) {
    UserDatabase db = UserDatabase.getInstance(context);
    userAccountDao = db.userAccountDao();
    this.username = username;
    this.password = password;
  }

  /**
   * @param username: username for database; username is a super key
   * @param context: where the constructor is called
   */
  public UserRepository(Context context, String username) {
    UserDatabase db = UserDatabase.getInstance(context);
    userAccountDao = db.userAccountDao();
    userScoresDao = db.userScoresDao();
    userCollectiblesDao = db.userCollectiblesDao();
    this.username = username;
  }

  /**
   * @return : true indicates login was successful, false otherwise METHOD BODY: gives it to async
   *     task and async task determines whether user's login information is correct
   */
  public boolean validateCredentials() {
    UserAccount ac = new UserAccount(username, password);
    validateCredentialsAsyncTask v = new validateCredentialsAsyncTask(userAccountDao);
    try {
      v.execute(ac).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return v.isSuccess();
  }

  /**
   * @return : true indicates registration was successful, false otherwise METHOD BODY: gives it to
   *     async task and async task determines whether user's username was already taken
   */
  public boolean addUser() {
    UserAccount ac = new UserAccount(username, password);
    ac.setAmount(0);
    addUserAsyncTask a = new addUserAsyncTask(userAccountDao);
    try {
      a.execute(ac).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // try{a.get()}catch()
    return a.isSuccess();
  }

  /**
   * @return returns the user account associated with the database METHOD BODY: sends it to async
   *     task to retrieve user account
   */
  private UserAccount getUser() {
    getUserAsyncTask a = new getUserAsyncTask(userAccountDao);
    try {
      a.execute(username).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserAccount();
  }

  /** @return returns the amount associated with the user */
  public int getUserAmount() {
    UserAccount ac = getUser();
    return ac.getAmount();
  }

  // updates the user's amount with corresponding differential
  public void updateUserAmount(int differential) {
    UserAccount ac = getUser();
    ac.setAmount(ac.getAmount() + differential);
    updateUserAmountAsyncTask a = new updateUserAmountAsyncTask(userAccountDao);
    try {
      a.execute(ac).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Add a new user score when a game is over
  public void addUserScore(UserScores userScores) {
    addUserScoreAsyncTask a = new addUserScoreAsyncTask(userScoresDao);
    try {
      a.execute(userScores).get();
      Log.i("ADD_USER_SCORE", "user score successfully recorded");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** @return the user high score for a specific game */
  public int getUserHighScore(String username, String gameType) {
    StatsTaskParam s = new StatsTaskParam(username, gameType);
    getUserHighScoreAsyncTask a = new getUserHighScoreAsyncTask(userScoresDao);
    try {
      a.execute(s).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserHighScore();
  }

  /** @return the user average score for a specific game */
  public double getUserAverageScore(String username, String gameType) {
    StatsTaskParam s = new StatsTaskParam(username, gameType);
    getUserAverageScoreAsyncTask a = new getUserAverageScoreAsyncTask(userScoresDao);
    try {
      a.execute(s).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserAverageScore();
  }

  /** Use this method to delete all user scores that were recorded in the table */
  public void resetUserStatistics(String username) {
    removeUserStatisticsAsyncTask a = new removeUserStatisticsAsyncTask(userScoresDao);
    try {
      a.execute(username).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Use this method to record that a user has purchased an item from the shop */
  public void addUserCollectible(int userCollectible) {
    addUserCollectibleAsyncTask a = new addUserCollectibleAsyncTask(userCollectiblesDao);
    UserCollectibles uC = new UserCollectibles(username, userCollectible);
    try {
      a.execute(uC).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** @return returns a list of integers which corresponds to items that user owns */
  public List<Integer> getUserCollectibles() {
    getUserCollectiblesAsyncTask a = new getUserCollectiblesAsyncTask(userCollectiblesDao);
    try {
      a.execute(username).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<Integer> b = new ArrayList<>();
    for (UserCollectibles u : a.getUserCollectibles()) {
      b.add(u.getCharacter_id());
    }
    return b;
  }

  /**
   * @param gameType: game we're interested in to find minimum time
   * @return the user minimum time taken in this particular game
   */
  public int getUserMinTime(String gameType) {
    StatsTaskParam s = new StatsTaskParam(username, gameType);
    getUserMinTimeAsyncTask a = new getUserMinTimeAsyncTask(userScoresDao);
    try {
      a.execute(s).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserMinTime();
  }

  /**
   * @param gameType: game we're interested in to find maximum time
   * @return the user maximum time taken in this particular game
   */
  public int getUserMaxTime(String gameType) {
    StatsTaskParam s = new StatsTaskParam(username, gameType);
    getUserMaxTimeAsyncTask a = new getUserMaxTimeAsyncTask(userScoresDao);
    try {
      a.execute(s).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserMaxTime();
  }

  /**
   * @param gameType: game we're interested in to find average time
   * @return the user average time taken in this particular game
   */
  public int getUserAvgTime(String gameType) {
    StatsTaskParam s = new StatsTaskParam(username, gameType);
    getUserAvgTimeAsyncTask a = new getUserAvgTimeAsyncTask(userScoresDao);
    try {
      a.execute(s).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a.getUserAvgTime();
  }

  // THE FOLLOWING ASYNCTASK CLASS ARE USED BY USER REPOSITORY METHODS TO
  // USE OUR DAO METHODS TO PERFORM QUERY OPERATIONS.
  // THIS IS BECAUSE ROOM QUERY CANNOT BE DONE ON THE MAIN THREAD
  public static class addUserAsyncTask extends AsyncTask<UserAccount, Void, Boolean> {

    private UserAccountDao userAccountDao;
    private boolean success;

    addUserAsyncTask(UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
      this.success = false;
    }

    boolean isSuccess() {
      return success;
    }

    @Override
    protected Boolean doInBackground(UserAccount... userAccounts) {
      System.out.println("l_1");
      if (userAccountDao.getUserAccountByUsername(userAccounts[0].getUsername()) == null) {
        userAccountDao.insert(userAccounts[0]);
        System.out.println("l_1_1");
        this.success = true;
        return true;
      }
      this.success = false;
      return false;
    }
  }

  public static class validateCredentialsAsyncTask extends AsyncTask<UserAccount, Void, Boolean> {

    private UserAccountDao userAccountDao;
    private boolean success;

    validateCredentialsAsyncTask(UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
      this.success = false;
    }

    boolean isSuccess() {
      return success;
    }

    @Override
    protected Boolean doInBackground(UserAccount... userAccounts) {
      String userName = userAccounts[0].getUsername();
      String password = userAccounts[0].getPassword();
      this.success = userAccountDao.getUserCredentials(userName, password) != null;
      return this.success;
    }
  }

  public static class addUserScoreAsyncTask extends AsyncTask<UserScores, Void, Void> {
    private UserScoresDao userScoresDao;

    addUserScoreAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    @Override
    protected Void doInBackground(UserScores... userScores) {
      userScoresDao.insert(userScores[0]);
      return null;
    }
  }

  public static class getUserAsyncTask extends AsyncTask<String, Void, UserAccount> {
    private UserAccountDao userAccountDao;
    private UserAccount userAccount;

    getUserAsyncTask(UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
    }

    UserAccount getUserAccount() {
      return userAccount;
    }

    @Override
    protected UserAccount doInBackground(String... strings) {
      userAccount = userAccountDao.getUserAccountByUsername(strings[0]);
      return userAccount;
    }
  }

  public static class updateUserAmountAsyncTask extends AsyncTask<UserAccount, Void, Void> {
    private UserAccountDao userAccountDao;

    updateUserAmountAsyncTask(UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
    }

    @Override
    protected Void doInBackground(UserAccount... userAccounts) {
      userAccountDao.update(userAccounts[0]);
      return null;
    }
  }

  public static class getUserHighScoreAsyncTask extends AsyncTask<StatsTaskParam, Void, Integer> {
    private UserScoresDao userScoresDao;
    private int userHighScore;

    getUserHighScoreAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    int getUserHighScore() {
      return userHighScore;
    }

    @Override
    protected Integer doInBackground(StatsTaskParam... statsTaskParams) {
      String userName = statsTaskParams[0].getUserName();
      String gameType = statsTaskParams[0].getGameType();
      userHighScore = userScoresDao.getUserHighScore(userName, gameType);
      return userHighScore;
    }
  }

  public static class getUserAverageScoreAsyncTask extends AsyncTask<StatsTaskParam, Void, Double> {
    private UserScoresDao userScoresDao;
    private double userAverageScore;

    getUserAverageScoreAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    double getUserAverageScore() {
      return userAverageScore;
    }

    @Override
    protected Double doInBackground(StatsTaskParam... statsTaskParams) {
      String userName = statsTaskParams[0].getUserName();
      String gameType = statsTaskParams[0].getGameType();
      userAverageScore = userScoresDao.getUserAvgScore(userName, gameType);
      return userAverageScore;
    }
  }

  public static class removeUserStatisticsAsyncTask extends AsyncTask<String, Void, Void> {
    private UserScoresDao userScoresDao;

    removeUserStatisticsAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    @Override
    protected Void doInBackground(String... strings) {
      userScoresDao.deleteUserScores(strings[0]);
      return null;
    }
  }

  public static class getUserCollectiblesAsyncTask extends AsyncTask<String, Void, Void> {
    private UserCollectiblesDao userCollectiblesDao;
    private List<UserCollectibles> userCollectibles;

    getUserCollectiblesAsyncTask(UserCollectiblesDao userCollectiblesDao) {
      this.userCollectiblesDao = userCollectiblesDao;
    }

    List<UserCollectibles> getUserCollectibles() {
      return userCollectibles;
    }

    @Override
    protected Void doInBackground(String... strings) {
      userCollectibles = userCollectiblesDao.getUserCollectibles(strings[0]);
      return null;
    }
  }

  public static class addUserCollectibleAsyncTask extends AsyncTask<UserCollectibles, Void, Void> {
    private UserCollectiblesDao userCollectiblesDao;

    addUserCollectibleAsyncTask(UserCollectiblesDao userCollectiblesDao) {
      this.userCollectiblesDao = userCollectiblesDao;
    }

    @Override
    protected Void doInBackground(UserCollectibles... userCollectibles) {
      userCollectiblesDao.insert(userCollectibles[0]);
      return null;
    }
  }

  public static class getUserMinTimeAsyncTask extends AsyncTask<StatsTaskParam, Void, Void> {
    private UserScoresDao userScoresDao;
    private int userMinTime;

    getUserMinTimeAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    int getUserMinTime() {
      return userMinTime;
    }

    @Override
    protected Void doInBackground(StatsTaskParam... statsTaskParams) {
      String userName = statsTaskParams[0].getUserName();
      String gameType = statsTaskParams[0].getGameType();
      userMinTime = userScoresDao.getUserMinTime(userName, gameType);
      return null;
    }
  }

  public static class getUserMaxTimeAsyncTask extends AsyncTask<StatsTaskParam, Void, Void> {
    private UserScoresDao userScoresDao;
    private int userMaxTime;

    getUserMaxTimeAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    int getUserMaxTime() {
      return userMaxTime;
    }

    @Override
    protected Void doInBackground(StatsTaskParam... statsTaskParams) {
      String userName = statsTaskParams[0].getUserName();
      String gameType = statsTaskParams[0].getGameType();
      userMaxTime = userScoresDao.getUserMaxTime(userName, gameType);
      return null;
    }
  }

  public static class getUserAvgTimeAsyncTask extends AsyncTask<StatsTaskParam, Void, Void> {
    private UserScoresDao userScoresDao;
    private int userAvgTime;

    getUserAvgTimeAsyncTask(UserScoresDao userScoresDao) {
      this.userScoresDao = userScoresDao;
    }

    int getUserAvgTime() {
      return userAvgTime;
    }

    @Override
    protected Void doInBackground(StatsTaskParam... statsTaskParams) {
      String userName = statsTaskParams[0].getUserName();
      String gameType = statsTaskParams[0].getGameType();
      userAvgTime = userScoresDao.getUserAvgTime(userName, gameType);
      return null;
    }
  }

  /**
   * Helper class for specific Async Task methods, use to combine username and game_type into a
   * single object
   */
  private class StatsTaskParam {
    String userName;
    String gameType;

    StatsTaskParam(String userName, String gameType) {
      this.userName = userName;
      this.gameType = gameType;
    }

    String getUserName() {
      return userName;
    }

    String getGameType() {
      return gameType;
    }
  }
}
