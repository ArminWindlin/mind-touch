import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class GameView {
    private Main main;
    private Game game;
    private GraphicsContext gc;
    private int[][] previousGrid;
    private int[][] queuedGrid;
    private Level lastLevel;
    private Boolean gridInQueue;
    private Image octagon1;
    private Image octagon2;
    private Image arrowLeft;
    private Image arrowRight;
    private Image arrowDown;
    private Image arrowUp;

    GameView(Main main, Game game, Canvas canvas) {
        this.main = main;
        this.game = game;
        this.gridInQueue = false;

        gc = canvas.getGraphicsContext2D();
        previousGrid = new int[17][30];

        // load images
        try {
            octagon1 = new Image(new FileInputStream("./src/assets/octagon_deeppurple_eye1.png"));
            octagon2 = new Image(new FileInputStream("./src/assets/octagon_lightgreen_eye1.png"));
            arrowLeft = new Image(new FileInputStream("./src/assets/arrowLeft.png"));
            arrowRight = new Image(new FileInputStream("./src/assets/arrowRight.png"));
            arrowDown = new Image(new FileInputStream("./src/assets/arrowDown.png"));
            arrowUp = new Image(new FileInputStream("./src/assets/arrowUp.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void drawLevel(Level level) {
        drawGrid(level.getGrid());
        drawHUD(level);
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
                        gc.drawImage(octagon1, j * 40, i * 40);
                        break;
                    case 2:
                        gc.drawImage(octagon2, j * 40, i * 40);
                        break;
                    case 6:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(j * 40, i * 40, 40, 40);
                        break;
                    case 7:
                        gc.setFill(Color.rgb(244, 67, 54));
                        gc.fillRect(j * 40, i * 40, 40, 40);
                        break;
                    case 8:
                        gc.setFill(Color.rgb(175, 82, 222));
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
                    Image imageToBeAnimated = octagon1;
                    if (a.type == 1) imageToBeAnimated = octagon1;
                    if (a.type == 2) imageToBeAnimated = octagon2;
                    gc.drawImage(imageToBeAnimated, a.x1 * 40 + (a.distance * a.directionX),
                            a.y1 * 40 + (a.distance * a.directionY));
                    if (a.distance >= 40) {
                        this.stop();
                        game.keyLock.unlock();
                        // Draw queue grid after animation
                        // At the moment only used for  portal (teleportation after animation)
                        if (gridInQueue) {
                            drawGrid(queuedGrid);
                            drawHUD(lastLevel);
                            gridInQueue = false;
                        }
                    }
                });
            }
        };
        animationTimer.start();
    }

    void queueGridDraw(int[][] grid) {
        gridInQueue = true;
        queuedGrid = grid;
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

    void drawLoss() {
        gc.setGlobalAlpha(0.8);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setGlobalAlpha(1);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gc.setFill(Color.BLACK);
        gc.fillText("OOPS, YOU LOST", 380, 200);
    }

    void drawHUD(Level level) {
        lastLevel = level;
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("LEVEL " + level.getLevelNumber(), 20, 35);
        gc.drawImage(getControlsImage(level.getControls().getUp()), main.WINDOW_WIDTH - 55, 7);
        gc.drawImage(getControlsImage(level.getControls().getRight()), main.WINDOW_WIDTH - 30, 32);
        gc.drawImage(getControlsImage(level.getControls().getDown()), main.WINDOW_WIDTH - 55, 32);
        gc.drawImage(getControlsImage(level.getControls().getLeft()), main.WINDOW_WIDTH - 80, 32);
        // show tutorial text if level 1
        if(level.getLevelNumber() == 1){
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(Font.font("Verdana", 20));
            gc.fillText("Make the two octagons touch each other", main.WINDOW_WIDTH / 2, 35);
        }
    }

    Image getControlsImage(int controlIndicator) {
        switch (controlIndicator) {
            case 0:
                return arrowUp;
            case 1:
                return arrowLeft;
            case 2:
                return arrowDown;
            case 3:
                return arrowRight;
            default:
                return arrowUp;
        }
    }

}
