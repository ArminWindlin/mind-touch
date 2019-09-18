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
        System.out.println("hey");
    }

    void moveRight() {
        int x = ball1.x;
        int y = ball1.y;
        if (x + 1 >= grid[y].length) return;
        grid[y][x] = 0;
        grid[y][x + 1] = 1;
        ball1.x++;
    }

    void moveLeft() {
        int x = ball1.x;
        int y = ball1.y;
        if (x - 1 < 0) return;
        grid[y][x] = 0;
        grid[y][x - 1] = 1;
        ball1.x--;
    }

    void moveUp() {
        int x = ball1.x;
        int y = ball1.y;
        if (y - 1 < 0) return;
        grid[y][x] = 0;
        grid[y - 1][x] = 1;
        ball1.y--;
    }

    void moveDown() {
        int x = ball1.x;
        int y = ball1.y;
        if (y + 1 >= grid.length) return;
        grid[y][x] = 0;
        grid[y + 1][x] = 1;
        ball1.y++;
    }

    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
