package com.example.game.MazeGame;

import com.example.game.MazeGame.DataStructures.Maze;
import com.example.game.MazeGame.DataStructures.Player;
import com.example.game.MazeGame.DataStructures.Score;

/**
 * Class to construct a GameFacade object.
 */
public class GameFacadeBuilder implements Builder {

    // Result to be returned
    private GameFacade gameFacade;

    private int startX, startY, startingScore, mazeWidth, mazeHeight;

    /**
     * Constructor for GameFacadeBuilder.
     *
     * @param startX        - starting x position of the player
     * @param startY        - starting y position of the player
     * @param startingScore - starting score of the player
     * @param mazeWidth     - width of the maze
     * @param mazeHeight    - height of the maze
     */
    GameFacadeBuilder(int startX, int startY, int startingScore, int mazeWidth, int mazeHeight) {
        this.startX = startX;
        this.startY = startY;
        this.startingScore = startingScore;
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
    }

    @Override
    public void build() {
        Score score = new Score(startingScore);
        Maze maze = new Maze(mazeWidth, mazeHeight);
        Player player = new Player(startX, startY, score);
        gameFacade = new GameFacade(player, maze);
    }

    /**
     * Returns the constructed GameFacade object.
     *
     * @return a constructed GameFacade object
     */
    GameFacade getGameFacade() {
        return gameFacade;
    }
}
