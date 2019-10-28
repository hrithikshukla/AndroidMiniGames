package com.example.game.MazeGame;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

//TODO: return a Cell 2d array representation of game; method name getGrid()

public class GameFacade extends Observable {

    private Player player;
    private Maze maze;

    private Map<Movement, Pair<Integer, Integer>> movementMap = new HashMap<>();

    // Assume player init position is in a valid position in the map
    public GameFacade(Player player, Maze maze) {
        this.player = player;
        this.maze = maze;
    }

    Player getPlayer() {
        return player;
    }

    Maze getMaze() {
        return maze;
    }

    void update(Pair<Integer , Integer> movement_vector) {
        // moves player
        int xDisplacement =  movement_vector.first;
        int yDisplacement =  movement_vector.second;
        player.displace(xDisplacement, yDisplacement);

        setChanged();
        // Need to put a 2d array of Cells into notifyObservers parameters
        notifyObservers(getCellrepresentation());


    }

    private Cell [][] getCellrepresentation(){
        Cell [][] representation = maze.getGrid();
        representation[player.getPos()[1]][player.getPos()[0]] = Cell.PLAYER;
        return representation;
    }

}
