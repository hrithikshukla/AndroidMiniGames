package com.example.game.TapiocaLauncher;

/** Tapioca that the user launches to clear the stage */
class Launcher {

  /** The top left coordinates of the ball and the radius */
  private int x, y, radius;
  /** The speed components of the ball. */
  private double speedX, speedY;

  /**
   * Creates a Launcher object with the provided arguments
   *
   * @param x - starting x coordinate
   * @param y - starting y coordinate
   * @param radius - radius of the Launcher
   */
  Launcher(int x, int y, int radius) {
    this.x = x;
    this.y = y;
    this.speedX = 0;
    this.speedY = 0;
    this.radius = radius;
  }

  /** Getters and Setters */
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  double getSpeedX() {
    return speedX;
  }

  void setSpeedX(double speedX) {
    this.speedX = speedX;
  }

  double getSpeedY() {
    return speedY;
  }

  void setSpeedY(double speedY) {
    this.speedY = speedY;
  }
}
