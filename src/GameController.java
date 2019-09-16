import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

import java.util.Objects;

class GameController {

    private int[][] boardGrid;

    GameController() {
        initialize();
    }

    private void initialize() {
        boardGrid = LevelData.getLevel(1);
    }


    public int[][] getBoardGrid() {
        return boardGrid;
    }

    public void setBoardGrid(int[][] boardGrid) {
        this.boardGrid = boardGrid;
    }
}
