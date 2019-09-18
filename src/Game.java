import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.util.Objects;

class Game {

    private Main main;
    private Scene gameScene;
    private Canvas canvas;
    private GameView gameView;
    private GameController gameController;
    KeyLock keyLock;

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

        // draw initial grid
        gameView.drawGrid(gameController.getGrid());

        // to make sure only one key action runs at a time
        keyLock = new KeyLock();

        gameScene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (Objects.equals(code, "ESCAPE")) {
                main.goToMenu();
            }
            // Prevents start of new move before old move is done
            if (keyLock.isLocked()) {
                return;
            }
            gameView.drawGrid(gameController.getGrid());
            gameController.move(code);
            gameView.drawGridWithAnimation(gameController.getGrid());
        });

    }

}
