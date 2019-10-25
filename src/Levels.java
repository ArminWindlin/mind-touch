import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

class Levels {

    static Scene getLevelsScene(Main main, Stage window) throws Exception {

        Group root = new Group();
        Canvas canvas = new Canvas(main.WINDOW_WIDTH, main.WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        scene.setOnMouseClicked(e -> {
            int x = (int) Math.floor(e.getX());
            int y = (int) Math.floor(e.getY());

            if (isInsideRect(300, 150, 600, 100, x, y)) {

            }
        });

        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (Objects.equals(code, "ESCAPE")) main.goToMenu();
        });

        drawLevels(canvas, main.WINDOW_WIDTH, main.WINDOW_HEIGHT);

        return scene;
    }

    private static void drawLevels(Canvas canvas, int width, int height) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, height, width);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, height, width);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);

        int maxLevel = 10;
        int currentLevel = LocalStorage.getProgress();
        // draw list of levels
        for (int i = 1; i <= maxLevel; i++) {
            int y = 160 + (int) Math.floor(i / 6) * 200;
            int x = 180 * ((i - 1) % 5 + 1);
            // background
            gc.setFill(Color.rgb(255, 204, 0));
            if (i < currentLevel) gc.fillRect(x, y, 100, 100);
            gc.setFill(Color.rgb(20, 136, 255));
            if (i == currentLevel) gc.fillRect(x, y, 100, 100);
            // rectangle
            gc.setFill(Color.BLACK);
            gc.strokeRect(x, y, 100, 100);
            // number
            if (i < 10) gc.fillText("" + i, x + 30, y + 70);
            else gc.fillText("" + i, x + 5, y + 70);
        }
    }

    private static boolean isInsideRect(int rectX, int rectY, int rectWidth, int rectHeight, int x, int y) {
        return x > rectX && x < rectX + rectWidth && y < rectY + rectHeight && y > rectY;
    }
}
