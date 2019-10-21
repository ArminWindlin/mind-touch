public class Level {

    private GameBoard gameBoard;
    private int[][] grid;
    private int levelNumber;
    private ControlSettings controls;
    private boolean hasTimer;
    private int timerInSeconds;

    public Level(GameBoard gameBoard, int levelNumber, ControlSettings controls) {
        this.gameBoard = gameBoard;
        this.levelNumber = levelNumber;
        this.controls = controls;
        this.grid = gameBoard.getGrid();
        this.hasTimer = false;
    }

    public Level(GameBoard gameBoard, int levelNumber, ControlSettings controls, int timerInSeconds) {
        this.gameBoard = gameBoard;
        this.levelNumber = levelNumber;
        this.controls = controls;
        this.grid = gameBoard.getGrid();
        this.hasTimer = false;
        this.timerInSeconds = timerInSeconds;
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

}