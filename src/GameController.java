class GameController {

    private GameBoard gameBoard;
    private int[][] grid;
    private GameObject ball1;
    private GameObject ball2;

    GameController() {
        initialize();
    }

    private void initialize() {
        gameBoard = LevelData.getLevel(1);
        grid = gameBoard.getGrid();

        // get position of ball 1 and 2
        ball1 = gameBoard.getBall1();
        ball2 = gameBoard.getBall2();
    }

    void move(String keyCode) {
        switch (keyCode) {
            case "LEFT":
                moveLeft();
                break;
            case "RIGHT":
                moveRight();
                break;
            case "UP":
                moveUp();
                break;
            case "DOWN":
                moveDown();
                break;
            default:
        }
    }

    void moveRight() {
        // move ball 1
        int x = ball1.x;
        int y = ball1.y;
        if (x + 1 < grid[y].length) {
            grid[y][x] = 0;
            grid[y][x + 1] = 1;
            ball1.x++;
        }
        // move ball 2
        x = ball2.x;
        y = ball2.y;
        if (x - 1 >= 0) {
            grid[y][x] = 0;
            grid[y][x - 1] = 2;
            ball2.x--;
        }
    }

    void moveLeft() {
        // move ball 1
        int x = ball1.x;
        int y = ball1.y;
        if (x - 1 >= 0) {
            grid[y][x] = 0;
            grid[y][x - 1] = 1;
            ball1.x--;
        }
        // move ball 2
        x = ball2.x;
        y = ball2.y;
        if (x + 1 < grid[y].length) {
            grid[y][x] = 0;
            grid[y][x + 1] = 2;
            ball2.x++;
        }
    }

    void moveUp() {
        // move ball 1
        int x = ball1.x;
        int y = ball1.y;
        if (y - 1 >= 0) {
            grid[y][x] = 0;
            grid[y - 1][x] = 1;
            ball1.y--;
        }
        // move ball 2
        x = ball2.x;
        y = ball2.y;
        if (y - 1 >= 0) {
            grid[y][x] = 0;
            grid[y - 1][x] = 2;
            ball2.y--;
        }
    }

    void moveDown() {
        // move ball 1
        int x = ball1.x;
        int y = ball1.y;
        if (y + 1 < grid.length) {
            grid[y][x] = 0;
            grid[y + 1][x] = 1;
            ball1.y++;
        }
        // move ball 2
        x = ball2.x;
        y = ball2.y;
        if (y + 1 < grid.length) {
            grid[y][x] = 0;
            grid[y + 1][x] = 2;
            ball2.y++;
        }
    }

    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
