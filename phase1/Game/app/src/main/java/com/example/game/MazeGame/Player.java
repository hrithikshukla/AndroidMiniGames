package com.example.game.MazeGame;

import java.util.ArrayList;

/**
 * Represents the player in a maze.
 */
public class Player {

    /**
     * Position of the player.
     */
    private int posX;
    private int posY;

    /**
     * Powerups the player currently is affected by.
     */
    private ArrayList<Powerup> powerups;

    /**
     * Initializes the player at the given coordinates in the maze.
     *
     * @param x - starting x coordinate
     * @param y - starting y coordinate
     */
    public Player(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.powerups = new ArrayList<>();
    }

    /**
     * Getter for the powerups player is affected by.
     */
    ArrayList<Powerup> getPowerups() {
        return powerups;
    }

    /**
     * Adds the given powerup to the list of powerups affecting the player.
     *
     * @param pu - new powerup that player is affected by
     */
    void addPowerup(Powerup pu) {
        powerups.add(pu);
    }

    /**
     * Removes the given powerup from the list of powerups affecting the player.
     *
     * @param pu - existing powerup that player is affected by
     */
    void removePowerup(Powerup pu) {
        if (isAffectedBy(pu)) {
            powerups.remove(pu);
        }
    }

    /**
     * Returns if player is currently affected by the given powerup.
     *
     * @param pu - powerup to be checked
     */
    boolean isAffectedBy(Powerup pu) {
        return powerups.contains(pu);
    }

    /**
     * Sets the position of the player.
     *
     * @param newX - new x coordinate of the player.
     * @param newY - new y coordinate of the player.
     */
    void setPos(int newX, int newY) {
        posX = newX;
        posY = newY;
    }

    /**
     * Returns the current position of the player.
     */
    int[] getPos() {
        return new int[]{posX, posY};
    }

}
