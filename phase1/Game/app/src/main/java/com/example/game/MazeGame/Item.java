package com.example.game.MazeGame;

/**
 * Represents a generic item in the maze that the player can pickup.
 */

public class Item {

    /**
     * The position of the item in the maze.
     */
    private int posX;
    private int posY;

    /**
     * Sets the location of the item in the maze.
     *
     * @param x - the x position of the item
     * @param y - the y position of the item
     */
    public Item(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * Checks if the given coordinates of the player are on the item's location and can pick it up.
     *
     * @param x - the x position of the given player's coordinate
     * @param y - the y position of the given player's coordinate
     */
    boolean canPickup(int x, int y) {
        return ((posX == x) && (posY == y));
    }

}
