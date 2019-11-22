package com.example.game.Save;

/**
 * Base class representing the stats every game should have.
 */
public abstract class Stats {

    private int highScore;
    private int totalGamesPlayed;

    /**
     * Default constructor. Initialize as a blank slate.
     */
    public Stats() {
        this(0, 0);
    }

    public Stats(int highScore, int totalGamesPlayed) {
        this.highScore = highScore;
        this.totalGamesPlayed = totalGamesPlayed;
    }

    /**
     * Getter for high score.
     */
    int getHighScore() {
        return highScore;
    }

    /**
     * Setter for high score.
     *
     * @param highScore - new high score
     */
    void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Getter for total games played.
     */
    int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    /**
     * Increment the total games played by one.
     */
    void incrementGamesPlayed() {
        totalGamesPlayed += 1;
    }

    /**
     * Getter for the unique stat of each game.
     */
    abstract int getUniqueStat();

    /**
     * Setter for the unique stat of each game.
     *
     * @param uniqueStat - new unique stat value
     */
    abstract void setUniqueStat(int uniqueStat);
}
