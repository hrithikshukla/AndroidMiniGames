package com.example.game.Save;

public class MazeStats extends Stats {

    // Unique stat of the Maze game.
    int totalStepsTaken;

    /**
     * Default constructor. Initialize as a blank slate.
     */
    public MazeStats() {
        this(0, 0, 0);
    }

    /**
     * Set the stats of the Maze game with the provided values.
     *
     * @param highScore          - input high score
     * @param totalGames         - input total games
     * @param totalStepsTaken    - input steps taken
     */
    public MazeStats(int highScore, int totalGames, int totalStepsTaken) {
        super(highScore, totalGames);
        this.totalStepsTaken = totalStepsTaken;
    }

    /**
     * Getter for the total number of steps taken.
     */
    @Override
    int getUniqueStat() {
        return totalStepsTaken;
    }

    /**
     * Setter for the total number of steps taken.
     *
     * @param totalStepsTaken - number of steps that were newly taken.
     */
    @Override
    void setUniqueStat(int totalStepsTaken) {
        this.totalStepsTaken += totalStepsTaken;
    }

}
