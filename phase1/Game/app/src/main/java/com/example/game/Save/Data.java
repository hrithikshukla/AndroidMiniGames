package com.example.game.Save;

import java.util.HashMap;

/**
 * Behaviour that any save file for our games should have. Specifically the ability to return a
 * HashMap with the specified structure when reading and the ability to write to the save file given
 * an array of Users.
 */
public interface Data {

  /**
   * Returns a HashMap where the key is the username of a User, and the value is another HashMap.
   * The keys of this second HashMap correspond to certain values of interest like the password or
   * high scores.
   */
  HashMap<String, User> read();

  /** Writes to the save file using the given array of Users. */
  void write(User[] users);
}
