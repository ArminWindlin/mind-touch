public class Level {

    private GameBoard gameBoard;
    private int[][] grid;
    private int levelNumber;
    private ControlSettings controls;
    private boolean hasTimer;
    private int timerInSeconds;
    private long timerTimestamp;

    public Level(GameBoard gameBoard, int levelNumber, ControlSettings controls) {
        this.gameBoard = gameBoard;
        this.levelNumber = levelNumber;
        this.controls = controls;
        this.grid = gameBoard.getGrid();
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

}
