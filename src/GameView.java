import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

class GameView {
    private Canvas canvas;
    private Main main;
    private Game game;
    private GraphicsContext gc;
    private int[][] previousGrid;

    GameView(Main main, Game game, Canvas canvas) {
        this.main = main;
        this.game = game;
        this.canvas = canvas;

        gc = canvas.getGraphicsContext2D();
        previousGrid = new int[17][30];
    }

    void drawGrid(int[][] grid) {
        // clear the canvas adn set white background
        gc.clearRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);

        // draw grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0:
                        // activate for developer mode
                        // gc.setStroke(Color.BLACK);
                        // gc.strokeRect(j * 40, i * 40, 40, 40);
                        break;
                    case 1:
                        gc.setFill(Color.GREEN);
                        gc.fillOval(j * 40, i * 40, 40, 40);
                        break;
                    case 2:
                        gc.setFill(Color.BLUE);
                        gc.fillOval(j * 40, i * 40, 40, 40);
                        break;
                    case 6:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(j * 40, i * 40, 40, 40);
                        break;
                    default:
                        break;
                }
            }
        }
        saveAsPreviousGrid(grid);
    }

    private void saveAsPreviousGrid(int[][] grid) {
        for (int i = 0; i < previousGrid.length; i++) {
            System.arraycopy(grid[i], 0, previousGrid[i], 0, grid[i].length);
        }
    }

    // changes of 1 field are animated, bigger changes currently won't be drawn at all with this method
    void drawGridWithAnimation(int[][] grid) {
        drawGridAnimations(calculateGridAnimations(grid));
        saveAsPreviousGrid(grid);
    }

    private ArrayList<ObjectAnimationInformation> calculateGridAnimations(int[][] grid) {
        ArrayList<ObjectAnimationInformation> animations = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (previousGrid[y][x] != grid[y][x] && previousGrid[y][x] != 0) {
                    if (y - 1 >= 0 && grid[y - 1][x] == previousGrid[y][x]) {
                        animations.add(new ObjectAnimationInformation(x, y, x, y - 1, previousGrid[y][x]));
                    }
                    if (y + 1 < 17 && grid[y + 1][x] == previousGrid[y][x]) {
                        animations.add(new ObjectAnimationInformation(x, y, x, y + 1, previousGrid[y][x]));
                    }
                    if (x - 1 >= 0 && grid[y][x - 1] == previousGrid[y][x]) {
                        animations.add(new ObjectAnimationInformation(x, y, x - 1, y, previousGrid[y][x]));
                    }
                    if (x + 1 < 30 && grid[y][x + 1] == previousGrid[y][x]) {
                        animations.add(new ObjectAnimationInformation(x, y, x + 1, y, previousGrid[y][x]));
                    }
                }
            }
        }
        return animations;
    }

    private void drawGridAnimations(ArrayList<ObjectAnimationInformation> animations) {
        if (!animations.isEmpty()) game.keyLock.lock();
        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                animations.forEach(a -> {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(a.x1 * 40, a.y1 * 40, 40, 40);
                    gc.fillRect(a.x2 * 40, a.y2 * 40, 40, 40);
                    a.distance += 4;
                    if (a.type == 1) gc.setFill(Color.GREEN);
                    if (a.type == 2) gc.setFill(Color.BLUE);
                    gc.fillOval(a.x1 * 40 + (a.distance * a.directionX),
                            a.y1 * 40 + (a.distance * a.directionY), 40, 40);
                    if (a.distance >= 40) {
                        this.stop();
                        game.keyLock.unlock();
                    }
                });
            }
        };
        animationTimer.start();
    }

    void drawWin() {
        gc.setGlobalAlpha(0.8);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setGlobalAlpha(1);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gc.setFill(Color.BLACK);
        gc.fillText("YOU WIN!", 440, 200);
    }

}
