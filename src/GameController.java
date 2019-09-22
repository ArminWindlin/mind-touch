class GameController {

    private GameBoard gameBoard;
    private int[][] grid;
    private GameObject octagon1;
    private GameObject octagon2;
    private boolean hasBeenWon;
    private int currentLevel;

    GameController() {
        initialize();
    }

    private void initialize() {
        currentLevel = 1;
        setLevel();
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
        if (levelHasBeenWon()) hasBeenWon = true;
    }

    private boolean levelHasBeenWon() {
        return (Math.abs(octagon1.x - octagon2.x) == 1 && octagon1.y == octagon2.y) ||
                (Math.abs(octagon1.y - octagon2.y) == 1 && octagon1.x == octagon2.x);
    }

    void moveRight() {
        // move ball 1
        int x = octagon1.x;
        int y = octagon1.y;
        if (x + 1 < grid[y].length && grid[y][x + 1] == 0) {
            grid[y][x] = 0;
            grid[y][x + 1] = 1;
            octagon1.x++;
        }
        // move ball 2
        x = octagon2.x;
        y = octagon2.y;
        if (x - 1 >= 0 && grid[y][x - 1] == 0) {
            grid[y][x] = 0;
            grid[y][x - 1] = 2;
            octagon2.x--;
        }
    }

    void moveLeft() {
        // move ball 1
        int x = octagon1.x;
        int y = octagon1.y;
        if (x - 1 >= 0 && grid[y][x - 1] == 0) {
            grid[y][x] = 0;
            grid[y][x - 1] = 1;
            octagon1.x--;
        }
        // move ball 2
        x = octagon2.x;
        y = octagon2.y;
        if (x + 1 < grid[y].length && grid[y][x + 1] == 0) {
            grid[y][x] = 0;
            grid[y][x + 1] = 2;
            octagon2.x++;
        }
    }

    void moveUp() {
        // move ball 1
        int x = octagon1.x;
        int y = octagon1.y;
        if (y - 1 >= 0 && grid[y - 1][x] == 0) {
            grid[y][x] = 0;
            grid[y - 1][x] = 1;
            octagon1.y--;
        }
        // move ball 2
        x = octagon2.x;
        y = octagon2.y;
        if (y - 1 >= 0 && grid[y - 1][x] == 0) {
            grid[y][x] = 0;
            grid[y - 1][x] = 2;
            octagon2.y--;
        }
    }

    void moveDown() {
        // move ball 1
        int x = octagon1.x;
        int y = octagon1.y;
        if (y + 1 < grid.length && grid[y + 1][x] == 0) {
            grid[y][x] = 0;
            grid[y + 1][x] = 1;
            octagon1.y++;
        }
        // move ball 2
        x = octagon2.x;
        y = octagon2.y;
        if (y + 1 < grid.length && grid[y + 1][x] == 0) {
            grid[y][x] = 0;
            grid[y + 1][x] = 2;
            octagon2.y++;
        }
    }

    void setLevel() {
        gameBoard = LevelData.getLevel(currentLevel);
        grid = gameBoard.getGrid();
        hasBeenWon = false;

        // get position of octagon 1 and 2
        octagon1 = gameBoard.getObject1();
        octagon2 = gameBoard.getObject2();

        currentLevel++;

        // level restriction
        if (currentLevel > 2) currentLevel = 1;
    }

    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    boolean hasBeenWon() {
        return hasBeenWon;
    }
}
