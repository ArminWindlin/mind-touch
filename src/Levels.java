import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

class Levels {

    static Scene getLevelsScene(Main main) throws Exception {

        Group root = new Group();
        Canvas canvas = new Canvas(main.WINDOW_WIDTH, main.WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        int currentLevel = LocalStorage.getProgress();

        scene.setOnMouseClicked(e -> {
            int mouseX = (int) Math.floor(e.getX());
            int mouseY = (int) Math.floor(e.getY());

            for (int i = 1; i <= 10; i++) {
                int y = 160 + (int) Math.floor(i / 6) * 200;
                int x = 180 * ((i - 1) % 5 + 1);
                if (isInsideRect(x, y, 100, 100, mouseX, mouseY)) {
                    if (i <= currentLevel) main.goToGame(i);
                    else {
                        GraphicsContext gc = canvas.getGraphicsContext2D();
                        gc.setTextAlign(TextAlignment.CENTER);
                        gc.setFont(Font.font("Verdana", 25));
                        gc.fillText("Solve the previous levels first", 600, 100);
                    }
                }
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

        int currentLevel = LocalStorage.getProgress();
        // draw list of levels
        for (int i = 1; i <= 10; i++) {
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
            gc.setTextAlign(TextAlignment.CENTER);
            if (i < 10) gc.fillText("" + i, x + 50, y + 70);
            else gc.fillText("" + i, x + 50, y + 70);
        }
    }

    private static boolean isInsideRect(int rectX, int rectY, int rectWidth, int rectHeight, int x, int y) {
        return x > rectX && x < rectX + rectWidth && y < rectY + rectHeight && y > rectY;
    }
}
