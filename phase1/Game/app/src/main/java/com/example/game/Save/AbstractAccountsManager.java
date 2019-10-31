package com.example.game.Save;

import java.io.Serializable;

/**
 * Manages all the User accounts e.g. account creation, retrieval of User account to continue
 * tracking stats in our game.
 */
public abstract class AbstractAccountsManager implements Serializable {

  /** The data file used to save the user information. */
  Data data;

  public AbstractAccountsManager() {}

  public AbstractAccountsManager(Data data) {
    this.data = data;
  }

  /**
   * Checks if the username is an existing user in the save file.
   *
   * @param username - username to check.
   */
  public abstract boolean isExistingUser(String username);

  /**
   * Attempts to create a new User with the input username and password. Returns whether creation
   * was successful.
   *
   * @param username - username of the new User
   * @param password - password of the new User
   */
  public abstract boolean createUser(String username, String password);

  /**
   * Attempts to login with the given credentials. Returns the corresponding User that matches the
   * credentials or null if no such user exists.
   *
   * @param username - input username
   * @param password - input password
   * @return
   */
  public abstract User login(String username, String password);

  /*
   * Updates user data(any statistics) we're tracking in our jsonData object
   * */
  public abstract void updateUserData();
}
