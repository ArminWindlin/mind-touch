import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

import java.util.Objects;

class Game {

    private Main main;
    private Scene gameScene;
    private Canvas canvas;
    private GameView gameView;

    Scene getGameScene() {
        return gameScene;
    }

    Game(Main main) {
        this.main = main;
        this.start();
    }

    private void start() {
        Group root = new Group();
        gameScene = new Scene(root);

        canvas = new Canvas(main.WINDOW_WIDTH, main.WINDOW_HEIGHT);
        root.getChildren().add(canvas);

        gameView = new GameView(main, canvas);

        GameObject ball1 = new GameObject(40, 8 * 40);
        GameObject ball2 = new GameObject(28 * 40, 8 * 40);

        // to make sure only one key action runs at a time
        KeyLock keyLock = new KeyLock();

        gameScene.setOnKeyPressed(e -> {
            if (keyLock.isLocked()) {
                return;
            }
            keyLock.lock();
            String code = e.getCode().toString();
            animation3(ball1, ball2, code, keyLock);
            System.out.println(code);
            if (Objects.equals(code, "ESCAPE")) {
                main.goToMenu();
            }
        });

        gameView.drawFrame(ball1, ball2);
    }

    private void animation3(GameObject ball1, GameObject ball2, String code, KeyLock keyLock) {

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
                ball2.setX(ball2.getX() + 4 * dir.getX() * -1);
                ball2.setY(ball2.getY() + 4 * dir.getY());
                gameView.drawFrame(ball1, ball2);
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


}
