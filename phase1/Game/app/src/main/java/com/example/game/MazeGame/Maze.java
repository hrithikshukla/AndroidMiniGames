package com.example.game.MazeGame;

/**
 * Represents the maze of the game.
 */
public class Maze {

    /**
     * The maze is represented as a 2D array, with the first and second positions being row and
     * column respectively.
     */
    private Object[][] maze;
    private Player player;

    /**
     * Generates the maze and create a new player for it.
     */
    public Maze() {
        this.maze = generateMaze();
        this.player = new Player(0, 0);  // Temporary starting position
    }

    /**
     * Algorithm for generating the maze.
     */
    private Object[][] generateMaze() {
        return null;    // Currently unimplemented.
    }

}
