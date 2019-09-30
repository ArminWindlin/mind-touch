import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.util.Objects;

class Game {

    private Main main;
    private Scene gameScene;
    private Canvas canvas;
    private GameView gameView;
    private GameController gameController;
    KeyLock keyLock;
    KeyLock winLock;

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

        // view
        gameView = new GameView(main, this, canvas);

        // controller
        gameController = new GameController();

        // draw level
        gameView.drawLevel(gameController.getLevel());

        // to make sure only one key action runs at a time
        keyLock = new KeyLock();
        winLock = new KeyLock();

        gameScene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (Objects.equals(code, "ESCAPE")) {
                main.goToMenu();
            } else if (Objects.equals(code, "SPACE") || Objects.equals(code, "ENTER")) {
                if (gameController.hasBeenWon()) nextLevel();
            }

            // Prevents start of new move before old move is done
            if (keyLock.isLocked() || winLock.isLocked()) {
                return;
            }
            gameView.drawLevel(gameController.getLevel());
            gameController.move(code);
            gameView.drawGridWithAnimation(gameController.getGrid());

            if (gameController.hasBeenWon()) {
                winLock.lock();
                gameView.drawWin();
            }
        });

        gameScene.setOnMouseClicked(e -> {
            if (gameController.hasBeenWon()) nextLevel();

        });

    }

    private void nextLevel() {
        gameController.setLevel();
        gameView.drawGrid(gameController.getGrid());
        winLock.unlock();
    }

}
