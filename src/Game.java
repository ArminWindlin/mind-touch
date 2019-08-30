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
            String code = e.getCode().toString();

            switch (code) {
                case "LEFT":
                    ball1.setX(ball1.getX() - 1);
                    break;
                case "RIGHT":
                    ball1.setX(ball1.getX() + 1);
                    break;
                case "UP":
                    ball1.setY(ball1.getY() - 1);
                    break;
                case "DOWN":
                    ball1.setY(ball1.getY() + 100);
                    break;
                default:
            }

            //drawFrame(gc, ball1);
            animation3(gc, ball1);
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

    private void animation3(GraphicsContext gc, GameObject ball1) {
        int initialX = ball1.getX();
        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Clear the canvas
                ball1.setX(ball1.getX() + 2);
                gc.clearRect(0, 0, 1500, 900);
                drawFrame(gc, ball1);
                System.out.println(ball1.getX());
                System.out.println(ball1.getX() - initialX);
                System.out.println(currentNanoTime);
                if(ball1.getX() - initialX >= 100){
                    System.out.println("hey");
                this.stop();}
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
