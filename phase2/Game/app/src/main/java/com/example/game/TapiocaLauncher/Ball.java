package com.example.game.TapiocaLauncher;

/** Tapioca balls that the player destroys */
class Ball {

  /** The top left coordinates of the ball */
  private int x, y;
  /** The width, height, radius of the ball */
  private int width, height, radius;
  /** Whether this ball has been hit this shot or not */
  private boolean hit = false;
  /** The health of the ball (how many more hits are needed to break it */
  private int hp;
  /** The type of ball */
  private String ballType;

  /**
   * Creates a new ball object with the provided arguments
   *
   * @param x - top left x coordinate
   * @param y - top left y coordinate
   * @param radius - radius of the ball
   * @param hp - hp of the ball
   * @param ballType - type of the ball
   */
  Ball(int x, int y, int radius, int hp, String ballType) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.hp = hp;
    this.width = 2 * radius;
    this.height = 2 * radius;
    this.ballType = ballType;
  }

  /** Getters And Setters */
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

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  boolean isHit() {
    return hit;
  }

  void setHit(boolean hit) {
    this.hit = hit;
  }

  int getHp() {
    return hp;
  }

  void setHp(int hp) {
    this.hp = hp;
  }

  String getBallType() {
    return ballType;
  }

  public void setBallType(String ballType) {
    this.ballType = ballType;
  }
}
