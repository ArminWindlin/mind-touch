class Level {

    private GameBoard gameBoard;
    private int[][] grid;
    private int levelNumber;
    private ControlSettings controls;

    Level(GameBoard gameBoard, int levelNumber, ControlSettings controls) {
        this.gameBoard = gameBoard;
        this.levelNumber = levelNumber;
        this.controls = controls;
        this.grid = gameBoard.getGrid();
    }

    GameBoard getGameBoard() {
        return gameBoard;
    }

    int[][] getGrid() {
        return grid;
    }

    void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    int getLevelNumber() {
        return levelNumber;
    }

    void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    ControlSettings getControls() {
        return controls;
    }

    void setControls(ControlSettings controls) {
        this.controls = controls;
    }

}
