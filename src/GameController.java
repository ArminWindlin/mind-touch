class GameController {

    private GameBoard gameBoard;
    private int[][] grid;
    private GameObject ball1;

    GameController() {
        initialize();
    }

    private void initialize() {
        gameBoard = LevelData.getLevel(1);
        grid = gameBoard.getGrid();

        // get position of ball 1
        ball1 = gameBoard.getBall1();
        System.out.println(ball1.x);
    }

    void move(String keyCode) {
        switch (keyCode) {
            case "LEFT":
                break;
            case "RIGHT":
                moveRight();
                break;
            case "UP":
                break;
            case "DOWN":
                break;
            default:
        }
    }

    void moveRight() {
        int x = ball1.x;
        int y = ball1.y;
        grid[y][x] = 0;
        grid[y][x + 1] = 1;
        ball1.x++;
    }

    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
