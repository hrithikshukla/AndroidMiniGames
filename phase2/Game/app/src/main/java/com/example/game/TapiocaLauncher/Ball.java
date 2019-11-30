package com.example.game.TapiocaLauncher;

// Taopioca balls to be destroeyd
 class Ball {

  // The top left coordinates of the ball
  private int x, y;
  // The width, height, radius of the ball
  private int width, height, radius;
  // Whether this ball has been hit this shot or not
  private boolean hit = false;
  // The number of hits to break this tapioca
  private int hp;
  private String ballType;

  Ball(int x, int y, int radius, int hp, String ballType) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.hp = hp;
    this.width = 2 * radius;
    this.height = 2 * radius;
    this.ballType = ballType;
  }

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
