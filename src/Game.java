import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Scene scene1 = new Scene(root);

        Canvas canvas = new Canvas(1500, 900);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameObject ball1 = new GameObject(500, 500);

        scene1.setOnKeyPressed(e -> {
            int xOld = ball1.getX();
            int yOld = ball1.getY();
            int action = 0;
            String code = e.getCode().toString();


            //drawFrame(gc, ball1);
            animation3(gc, ball1, code);
        });

        drawFrame(gc, ball1);

        window = primaryStage;
        window.setTitle("MindTouch");
        window.setScene(scene1);
        window.setOnCloseRequest(e -> closeProgram());
        window.show();
    }

    private void drawFrame(GraphicsContext gc, GameObject ball1) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1500, 900);
        gc.setFill(Color.BLUE);
        gc.fillOval(ball1.getX(), ball1.getY(), 30, 30);
    }

    private void animation2(GraphicsContext gc, GameObject ball1) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            ball1.setX(ball1.getX() + 1);
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 1500, 900);
            gc.setFill(Color.BLUE);
            gc.fillOval(ball1.getX(), ball1.getY(), 30, 30);

        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void animation3(GraphicsContext gc, GameObject ball1, String code) {

        DirectionIndicator dir = new DirectionIndicator(0, 0);

        switch (code) {
            case "LEFT":
                dir.setX(-1);
                break;
            case "RIGHT":
                dir.setX(1);
                break;
            case "UP":
                dir.setY(-1);
                break;
            case "DOWN":
                dir.setY(1);
                break;
            default:
        }

        int initialX = ball1.getX();
        int initialY = ball1.getY();
        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ball1.setX(ball1.getX() + 2 * dir.getX());
                ball1.setY(ball1.getY() + 2 * dir.getY());
                // clear the canvas
                gc.clearRect(0, 0, 1500, 900);
                drawFrame(gc, ball1);
                if (Math.abs(ball1.getX() - initialX) >= 40 || Math.abs(ball1.getY() - initialY) >= 40) {
                    this.stop();
                }
            }
        };
        animationTimer.start();
        new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            //animationTimer.stop();
        })).play();

    }

    private void drawAnimation(GraphicsContext gc, GameObject ball1, int xOld, int yOld) {
        int xNew = ball1.getX();
        int yNew = ball1.getY();
        int xDif = xNew - xOld;
        int yDif = yOld - yNew;

        int dif = Math.abs(xNew) > Math.abs(yNew) ? xNew : yNew;

        int xAnim = 0;
        int yAnim = 0;
        for (int i = 0; i < dif; i++) {
            if (xDif > 0) {
                xAnim++;
            } else {
                xAnim--;
            }
            if (yDif > 0) {
                yAnim++;
            } else {
                yAnim--;
            }
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 1500, 900);
            gc.setFill(Color.BLUE);
            gc.fillOval(xOld + xAnim, yOld + yAnim, 30, 30);
        }
    }

    private void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
