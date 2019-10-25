import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;

class Menu {

    static Scene getMenuScene(Main main, Stage window) throws Exception {

        Group root = new Group();
        Canvas canvas = new Canvas(main.WINDOW_WIDTH, main.WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        scene.setOnMouseClicked(e -> {
            int x = (int) Math.floor(e.getX());
            int y = (int) Math.floor(e.getY());

            // play button
            if (isInsideRect(300, 150, 600, 100, x, y))
                main.goToGame(-1);

            // levels button
            if (isInsideRect(300, 350, 600, 100, x, y))
                try {
                    main.goToLevels();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
        });

        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (Objects.equals(code, "SPACE") || Objects.equals(code, "ENTER")) {
                main.goToGame(-1);
            }
        });

        drawMenu(canvas, main.WINDOW_WIDTH, main.WINDOW_HEIGHT);

        return scene;
    }

    private static void drawMenu(Canvas canvas, int width, int height) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, height, width);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, height, width);
        gc.strokeRect(300, 150, 600, 100);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText("PLAY", width / 2, 225);
        gc.strokeRect(300, 350, 600, 100);
        gc.setFill(Color.BLACK);
        gc.fillText("LEVELS", width / 2, 425);
    }

    private static boolean isInsideRect(int rectX, int rectY, int rectWidth, int rectHeight, int x, int y) {
        return x > rectX && x < rectX + rectWidth && y < rectY + rectHeight && y > rectY;
    }
}
