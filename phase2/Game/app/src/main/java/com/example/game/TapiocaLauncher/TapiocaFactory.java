package com.example.game.TapiocaLauncher;

/** Implements Factory Design to make the creation of Tapioca simpler */
class TapiocaFactory {

  /** Initializes the factory */
  TapiocaFactory() {}

  /**
   * Makes a brown Ball
   *
   * @param x - x-coordinate of ball
   * @param y - y-coordinate of ball
   * @param radius - radius of ball
   * @return Brown Ball
   */
  Ball makeBrown(int x, int y, int radius) {
    return new Ball(x, y, radius, 1, "reg");
  }

  /**
   * Makes a Red Ball
   *
   * @param x - x-coordinate of ball
   * @param y - y-coordinate of ball
   * @param radius - radius of ball
   * @return Red Ball
   */
  Ball makeRed(int x, int y, int radius) {
    return new Ball(x, y, radius, 2, "reg");
  }

  /**
   * Makes a White Ball
   *
   * @param x - x-coordinate of ball
   * @param y - y-coordinate of ball
   * @param radius - radius of ball
   * @return White Ball
   */
  Ball makeWhite(int x, int y, int radius) {
    return new Ball(x, y, radius, 1, "speedBoost");
  }

  /**
   * Makes a Purple Ball
   *
   * @param x - x-coordinate of ball
   * @param y - y-coordinate of ball
   * @param radius - radius of ball
   * @return Purple Ball
   */
  Ball makePurple(int x, int y, int radius) {
    return new Ball(x, y, radius, 1, "extraShot");
  }
}
