package com.example.game.TapiocaLauncher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Creates the Layout of the Balls on the Tapioca Launcher Board */
class BoardManager {

  /** TapiocaFactory that makes the balls */
  private TapiocaFactory tapiocaFactory;

  /** Creates the boardManager and initializes the Tapioca Factory */
  BoardManager() {
    this.tapiocaFactory = new TapiocaFactory();
  }

  /**
   * Chooses the level design based on the current level
   *
   * @param num - the current level
   * @return a list of balls in the specific layout
   */
  List<Ball> getLevel(int num) {
    switch (num) {
      case 1:
        return generateLevel1();
      case 2:
        return generateTriangle();
      default:
        return generateRandomLevel();
    }
  }

  /**
   * Generates level 1
   *
   * @return list of balls corresponding to level 1
   */
  private List<Ball> generateLevel1() {
    List<Ball> balls = new ArrayList<>();
    // 6 rows and 6 columns of tapioca with 1 HP
    for (int j = 0; j < 6; j++) {
      for (int i = 0; i < 6; i++) {
        balls.add(this.tapiocaFactory.makeBrown(50 + (170 * i), 50 + (140 * j), 41));
      }
    }
    return balls;
  }

  /**
   * Generates a Triangle Layout for level 2
   *
   * @return list of balls corresponding to level 2
   */
  private List<Ball> generateTriangle() {
    List<Ball> balls = new ArrayList<>();
    for (int j = 0; j < 7; j++) {
      for (int i = 6; i > j; i--) {
        if ((j == 3 && i == 6) || (j == 3 && i == 4)) {
          balls.add(
              this.tapiocaFactory.makeWhite(
                  50 * (2 * j + 1) + (170 * (6 - i)), 50 + (140 * j), 41));
        } else if (j == 3 && i == 5) {
          balls.add(
              this.tapiocaFactory.makePurple(
                  50 * (2 * j + 1) + (170 * (6 - i)), 50 + (140 * j), 41));
        } else {
          balls.add(
              this.tapiocaFactory.makeRed(50 * (2 * j + 1) + (170 * (6 - i)), 50 + (140 * j), 41));
        }
      }
    }
    return balls;
  }

  /**
   * Generates a random layout for level 3
   *
   * @return list of balls corresponding to level 3
   */
  private List<Ball> generateRandomLevel() {
    int whiteLimit = 0; // Limits the number of white balls
    int purpLimit = 0; // Limits the number of purple balls
    boolean created;
    List<Ball> balls = new ArrayList<>();
    for (int j = 0; j < 6; j++) {
      for (int i = 0; i < 6; i++) {
        created = false;
        while (!created) {
          Ball new_ball = this.makeRandom(50 + (170 * i), 50 + (140 * j), 41);
          if (new_ball.getBallType().equals("speedBoost")) {
            if (whiteLimit < 2) {
              balls.add(new_ball);
              whiteLimit++;
              created = true;
            }
          } else if (new_ball.getBallType().equals("extraShot")) {
            if (purpLimit < 1) {
              balls.add(new_ball);
              purpLimit++;
              created = true;
            }
          } else {
            balls.add(new_ball);
            created = true;
          }
        }
      }
    }
    return balls;
  }

  /**
   * Randomly creates a ball type
   *
   * @param x - x coordinate of ball
   * @param y - y coordinate of ball
   * @param radius - radius of ball
   * @return a random Ball
   */
  private Ball makeRandom(int x, int y, int radius) {
    Random rand = new Random();
    int num = rand.nextInt(36);
    switch (num) {
      case 0:
        return this.tapiocaFactory.makeWhite(x, y, radius);
      case 1:
      case 2:
        return this.tapiocaFactory.makePurple(x, y, radius);
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
        return this.tapiocaFactory.makeRed(x, y, radius);
      default:
        return this.tapiocaFactory.makeBrown(x, y, radius);
    }
  }
}
