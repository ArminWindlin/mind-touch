import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class GameView {
    private Canvas canvas;
    private Main main;

    GameView(Main main, Canvas canvas) {
        this.main = main;
        this.canvas = canvas;
    }

    void drawFrame(GameObject ball1, GameObject ball2) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // clear the canvas
        gc.clearRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setFill(Color.BLUE);
        gc.fillOval(ball1.getX(), ball1.getY(), 40, 40);
        gc.setFill(Color.GREEN);
        gc.fillOval(ball2.getX(), ball1.getY(), 40, 40);

        // draw grid
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 30; j++) {
                gc.setStroke(Color.BLACK);
                gc.strokeRect(j * 40, i * 40, 40, 40);

            }

        }
    }

    void drawGrid(int[][] grid) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // clear the canvas adn set white background
        gc.clearRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_WIDTH);

        // draw grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0:
                        gc.setStroke(Color.BLACK);
                        gc.strokeRect(j * 40, i * 40, 40, 40);
                        break;
                    case 1:
                        gc.setFill(Color.BLUE);
                        gc.fillOval(j * 40, i * 40, 40, 40);
                        break;
                    case 2:
                        gc.setFill(Color.GREEN);
                        gc.fillOval(j * 40, i * 40, 40, 40);
                        break;
                    default:
                        break;
                }
                gc.setStroke(Color.BLACK);
                gc.strokeRect(j * 40, i * 40, 40, 40);

            }

        }
    }

}
