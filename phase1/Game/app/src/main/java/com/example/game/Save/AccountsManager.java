package com.example.game.Save;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Manages all the User accounts. All the Users are stored in a HashMap for O(1) access time to
 * check credentials and return the corresponding User object.
 */
public class AccountsManager extends AbstractAccountsManager {

  /**
   * Key is the username of a User, while the value is the User object corresponding to that
   * username.
   */
  private HashMap<String, User> users;

  public AccountsManager() {}

  public AccountsManager(Data data) {
    super(data);
    this.users = data.read();
  }

  @Override
  /**
   * Checks if the username is an existing user in the save file.
   *
   * @param username - username to check.
   */
  public boolean isExistingUser(String username) {
    return users.containsKey(username);
  }

  @Override
  /**
   * Attempts to create a new User with the input username and password. Returns whether creation
   * was successful.
   *
   * @param username - username of the new User
   * @param password - password of the new User
   */
  public boolean createUser(String username, String password) {
    // Can't create if there's an existing User with that username.
    if (!isExistingUser(username)) {
      // Create a new user with no recorded UserData.
      User newUser = new User(username, password, new UserData());
      users.put(username, newUser);
      // After a new user is created the save file must be updated.
      data.write(users.values().toArray(new User[users.size()]));
      return true;
    }
    return false;
  }

  @Override
  /**
   * Attempts to login with the given credentials. Returns the corresponding User that matches the
   * credentials or null if no such user exists.
   *
   * @param username - input username
   * @param password - input password
   * @return
   */
  public User login(String username, String password) {
    if (isExistingUser(username) && users.get(username).checkPassword(password)) {
      return users.get(username);
    }
    return null;
  }

  @Override
  public void updateUserData() {
    this.data.write(users.values().toArray(new User[users.size()]));
  }
}
