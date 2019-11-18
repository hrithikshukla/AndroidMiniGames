package com.example.game.Save;

/**
 * Class representing the Stats of the Tile game.
 */
public class TileStats extends Stats {

    // Unique stat of the Tile game. Change if desired.
    private int totalTilesTapped;

    /**
     * Default constructor. Initialize as a blank slate.
     */
    public TileStats() {
        this(0, 0, 0);
    }

    /**
     * Set the stats of the Tile game with the provided values.
     *
     * @param highScore          - input high score
     * @param totalGames         - input total games
     * @param totalTilesTapped   - input tiles tapped
     */
    public TileStats(int highScore, int totalGames, int totalTilesTapped) {
        super(highScore, totalGames);
        this.totalTilesTapped = totalTilesTapped;
    }

    /**
     * Getter for the total number of tiles tapped.
     */
    @Override
    int getUniqueStat() {
        return totalTilesTapped;
    }

    /**
     * Setter for the total number of tiles tapped.
     *
     * @param totalTilesTapped - number of tiles that were newly tapped.
     */
    @Override
    void setUniqueStat(int totalTilesTapped) {
        this.totalTilesTapped += totalTilesTapped;
    }

}
