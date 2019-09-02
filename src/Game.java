import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

public class Game {

    private Main main;
    private DirectionIndicator dir;
    Group root;
    Scene gameScene;

    public Scene getGameScene() {
        return gameScene;
    }

    public Game(Main main) {
        this.main = main;
        this.start();
    }

    private void start() {
        root = new Group();
        gameScene = new Scene(root);

        Canvas canvas = new Canvas(1280, 680);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameObject ball1 = new GameObject(40, 8 * 40);

        // to make sure only one key action runs at a time
        KeyLock keyLock = new KeyLock();

        gameScene.setOnKeyPressed(e -> {
            if (keyLock.isLocked()) {
                return;
            }
            keyLock.lock();
            String code = e.getCode().toString();
            animation3(gc, ball1, code, keyLock);
            System.out.println(code);
            if (Objects.equals(code, "ESCAPE")) {
                main.goToMenu();
            }
        });

        drawFrame(gc, ball1);
    }


    private void drawFrame(GraphicsContext gc, GameObject ball1) {
        // clear the canvas
        gc.clearRect(0, 0, 1280, 680);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1280, 680);
        gc.setFill(Color.BLUE);
        gc.fillOval(ball1.getX(), ball1.getY(), 40, 40);

        // draw grid
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 32; j++) {
                gc.setStroke(Color.BLACK);
                gc.strokeRect(j * 40, i * 40, 40, 40);

            }

        }
    }

    private void animation3(GraphicsContext gc, GameObject ball1, String code, KeyLock keyLock) {

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
                ball1.setX(ball1.getX() + 4 * dir.getX());
                ball1.setY(ball1.getY() + 4 * dir.getY());
                drawFrame(gc, ball1);
                if (Math.abs(ball1.getX() - initialX) >= 40 || Math.abs(ball1.getY() - initialY) >= 40) {
                    this.stop();
                    keyLock.unlock();
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
            gc.fillRect(0, 0, 1280, 720);
            gc.setFill(Color.BLUE);
            gc.fillOval(xOld + xAnim, yOld + yAnim, 30, 30);
        }
    }

    private void animation2(GraphicsContext gc, GameObject ball1) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            ball1.setX(ball1.getX() + 1);
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 1280, 720);
            gc.setFill(Color.BLUE);
            gc.fillOval(ball1.getX(), ball1.getY(), 30, 30);

        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
