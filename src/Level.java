import java.util.Date;

public class Level {

    private GameBoard gameBoard;
    private int[][] grid;
    private int levelNumber;
    private ControlSettings controls;
    private boolean hasTimer;
    private int timerInSeconds;
    private long timerTimestamp;

    public Level(GameBoard gameBoard, int levelNumber, ControlSettings controls, boolean hasTimer, int timerInSeconds) {
        this.gameBoard = gameBoard;
        this.levelNumber = levelNumber;
        this.controls = controls;
        this.grid = gameBoard.getGrid();
        this.hasTimer = hasTimer;
        this.timerInSeconds = timerInSeconds;
        this.timerTimestamp = 0;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public ControlSettings getControls() {
        return controls;
    }

    public void setControls(ControlSettings controls) {
        this.controls = controls;
    }

    public boolean hasTimer() {
        return hasTimer;
    }

    public void setHasTimer(boolean hasTimer) {
        this.hasTimer = hasTimer;
    }

    public int getTimerInSeconds() {
        return timerInSeconds;
    }

    public void setTimerInSeconds(int timerInSeconds) {
        this.timerInSeconds = timerInSeconds;
    }

    public long getTimerTimestamp() {
        return timerTimestamp;
    }

    public void setTimerTimestamp(long timerDate) {
        this.timerTimestamp = timerDate;
    }
}