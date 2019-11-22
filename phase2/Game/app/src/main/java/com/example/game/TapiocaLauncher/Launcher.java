package com.example.game.TapiocaLauncher;

// Tapioca that the user launches to clear the stage
class Launcher {

    // The top left coordinates of the ball and the radius
    private int x, y, radius;
    // The speed components of the ball.
    private double speedX, speedY;

    // The Launcher ball that the player launches
    Launcher(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 0;
        this.radius = radius;
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
