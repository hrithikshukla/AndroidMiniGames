package com.example.game.Save;

import java.util.HashMap;

/**
 * Class that stores a User's information about our game e.g. high scores.
 */
class UserData {

    private HashMap<Games, Stats> gameStats;

    /**
     * Initalize UserData as a clean slate.
     */
    public UserData() {
        this(new MazeStats(), new TapiocaStats(), new TileStats());
    }

    /**
     * Initalize UserData with given stats of each game.
     */
    public UserData(MazeStats mazeStats, TapiocaStats tapiocaStats, TileStats tileStats) {
        this.gameStats = new HashMap<>();
        gameStats.put(Games.MAZE, mazeStats);
        gameStats.put(Games.TAPIOCA, tapiocaStats);
        gameStats.put(Games.TILES, tileStats);
    }

    /**
     * Get the high score of the specified game.
     *
     * @param game - name of one of our games
     */
    int getHighScore(Games game) {
        return gameStats.get(game).getHighScore();
    }

    /**
     * Attempts to set the high score of the specified game with the input score if the latter is
     * higher.
     *
     * @param game  - name of one of our games
     * @param score - input score
     */
    void setHighScore(Games game, int score) {
        if (score > gameStats.get(game).getHighScore()) {
            gameStats.get(game).setHighScore(score);
        }
    }

    /**
     * Returns the total games played for the specified game.
     * @param game - name of one of our games
     */
    int getTotalGames(Games game) {
        return gameStats.get(game).getTotalGamesPlayed();
    }

    /**
     * Increment the total amount of games played for the specified game by one.
     * @param game - name of one of our games
     */
    void incrementTotalGames(Games game) {
        gameStats.get(game).incrementGamesPlayed();
    }

    /**
     * Return the unique stat of the specified game.
     * @param game - name of one of our games
     */
    int getUniqueStat(Games game){
        return gameStats.get(game).getUniqueStat();
    }


}
