package com.example.game.MazeGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Class responsible for drawing the UI of the game.
 */
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;

    // Maximum x and y positions of the screen.
    private int screenX, screenY;

    private Paint paint;
    private Tile tile;
    private Background background;
    private int[][] maze;

    public GameView(Context context, int screenX, int screenY, int[][] maze) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        this.maze = maze;

        this.tile = new Tile(getResources());
        this.background = new Background(screenX, screenY, getResources());

        this.paint = new Paint();
    }

    /**
     * Runs the game when the maze window is active.
     */
    @Override
    public void run() {

        while (isPlaying) {

            update();
            draw();
            sleep();

        }

    }

    /**
     * Updates the state of the game given user inputs.
     */
    private void update() {

    }

    /**
     * Draws the tiles of the maze.
     */
    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();

            // Draw the background first.
            canvas.drawBitmap(background.getBackground(), background.getX(), background.getY(), paint);

            // Maze is drawn so that it is centered on the screen.

            // Following two lines represents the x and y position of the first tile of the maze,
            // i.e. maze[0][0].
            int topLeftTileX = (this.screenX - maze[0].length * tile.getSideLength()) / 2;
            int topLeftTileY = (this.screenY - maze.length * tile.getSideLength()) / 2;

            // Draw every tile of the maze. An offset of a tile's side length ensures that each
            // tile is drawn right next to each other.
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    canvas.drawBitmap(tile.getTile(maze[i][j]),
                            topLeftTileX + (j * tile.getSideLength()),
                            topLeftTileY + (i * tile.getSideLength()),
                            paint);
                }
            }

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    /**
     * Updates the screen roughly 60 times a second.
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Resumes the game from a paused state.
     */
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Pauses the game.
     */
    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
