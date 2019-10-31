package com.example.game.Save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/** Class that can read/save User information to a JSON file. */
public class JSONData implements Data {

  /** JSON file to save to. */
  private File saveFile;

  public JSONData(File fileName) {
    this.saveFile = fileName;
  }

  @Override
  /**
   * Returns a HashMap where the key is the username of a User, and the value is another HashMap.
   * The keys of this second HashMap correspond to certain values of interest like the password or
   * high scores.
   */
  public HashMap<String, User> read() {
    HashMap<String, User> tmp = new HashMap<>();

    // Source for implementation of read:
    // http://www.musingscafe.com/reading-large-json-file-in-java-efficiently/

    try (JsonReader jsonReader =
        new JsonReader(new InputStreamReader(new FileInputStream(saveFile), "UTF-8"))) {
      Gson gson = new GsonBuilder().create();

      // Read the JSON file as an array.
      jsonReader.beginArray();
      while (jsonReader.hasNext()) {
        // GSON builds a User object from each element of the JSON.
        User user = gson.fromJson(jsonReader, User.class);
        tmp.put(user.getUsername(), user);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return tmp;
  }

  @Override
  /** Writes to the save file using the given array of Users. */
  public void write(User[] users) {

    // Source for implementation of write:
    // https://www.mkyong.com/java/how-to-parse-json-with-gson/

    try (FileWriter writer = new FileWriter(saveFile)) {
      Gson gson = new Gson();
      // The array of Users is converted into a JSON format by GSON and written to the save file.
      gson.toJson(users, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
