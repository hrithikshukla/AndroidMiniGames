package com.example.game.MazeGame;

import android.util.Pair;

import java.util.ArrayList;

public class Maze {

        int [][] MazeItems;
        /**
         *
         * @param width width of maze
         * @param height height of maze
         * @param complexity the difficulty of the maze
         * @param density the number of "islands" in the maze
         */
        Maze(float width, float height, int complexity, int density){

            // Build
            int shapeHeight = (int)((height/2)*2 + 1);
            int shapeWidth = (int)((width/2)*2 + 1);
            MazeItems = new int[shapeHeight][shapeWidth];
            // Adjust complexity and density relative to map size
            complexity = (int) (complexity * (5 * (shapeWidth + shapeHeight)));
            density = (int) (density * (shapeWidth/2) * (shapeHeight/2));
            // Fill borders
            for(int i = 0; i < MazeItems[0].length; i++){
                MazeItems[0][i] = 1;
            }
            for(int i = 0; i < MazeItems[(int)height - 1].length; i ++){
                MazeItems[(int)height - 1][i] = 1;
            }
            for(int i = 0; i < MazeItems.length; i ++){
                MazeItems[i][0] = 1;
                MazeItems[i][((int)width) - 1] = 1;
            }

            // Make Aisles
            for(int i = 0; i < density; i ++){
                int x = (int) (Math.random() * (int)shapeWidth/2) * 2;
                int y = (int) (Math.random() * (int)shapeHeight/2) * 2;
                MazeItems[y][x] = 1;
                for(int j = 0; j < complexity; j ++){
                    ArrayList<Pair<Integer, Integer>> neighbours = new ArrayList<>();
                    if(x > 1){
                        neighbours.add(new Pair<>(x -2, y));
                    }
                    if(x < width -2){
                        neighbours.add(new Pair<>(x + 2, y));
                    }
                    if(y > 1){
                        neighbours.add(new Pair<>(x, y - 2));
                    }
                    if(y < height - 2){
                        neighbours.add(new Pair<>(x, y + 2));
                    }
                    if (!neighbours.isEmpty()){
                        int x_ = neighbours.get((int) (Math.random() * (neighbours.size() - 1))).first;
                        int y_ = neighbours.get((int) (Math.random() * (neighbours.size() - 1))).second;
                        if (MazeItems[y_][x_] == 0){
                            MazeItems[y_][x_] = 1;
                            MazeItems[y_+ (y - y_)/2][x_ + (x - x_)/2] = 1;
                            x = x_;
                            y = y_;
                        }
                    }
                }

            }


        }
}
