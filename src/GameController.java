class GameController {

    private Level level;
    private GameBoard gameBoard;
    private int[][] grid;
    private GameObject octagon1;
    private GameObject octagon2;
    private boolean hasWon;
    private boolean hasLost;
    private int currentLevel;

    GameController() {
        initialize();
    }

    private void initialize() {
        currentLevel = 1;
        setLevel();
    }

    void move(String keyCode) {
        int moveObject2 = -1;

        switch (keyCode) {
            case "LEFT":
                moveLeft(octagon1);
                moveObject2 = level.getControls().getLeft();
                break;
            case "RIGHT":
                moveRight(octagon1);
                moveObject2 = level.getControls().getRight();
                break;
            case "UP":
                moveUp(octagon1);
                moveObject2 = level.getControls().getUp();
                break;
            case "DOWN":
                moveDown(octagon1);
                moveObject2 = level.getControls().getDown();
                break;
            default:
        }

        switch (moveObject2) {
            case 0:
                moveUp(octagon2);
                break;
            case 1:
                moveRight(octagon2);
                break;
            case 2:
                moveDown(octagon2);
                break;
            case 3:
                moveLeft(octagon2);
                break;
            default:
        }

        if (levelHasBeenLost()) hasLost = true;

        if (levelHasBeenWon()) hasWon = true;
    }

    private boolean levelHasBeenWon() {
        return (Math.abs(octagon1.x - octagon2.x) == 1 && octagon1.y == octagon2.y) ||
                (Math.abs(octagon1.y - octagon2.y) == 1 && octagon1.x == octagon2.x);
    }

    private boolean levelHasBeenLost() {
        boolean lost = false;
        if (grid[octagon1.y][octagon1.x + 1] == 7)
            lost = true;
        return (Math.abs(octagon1.x - octagon2.x) == 1 && octagon1.y == octagon2.y) ||
                (Math.abs(octagon1.y - octagon2.y) == 1 && octagon1.x == octagon2.x);
    }

    private void moveRight(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        if (x + 1 < grid[y].length && grid[y][x + 1] == 0) {
            grid[y][x] = 0;
            grid[y][x + 1] = gameObject.type;
            gameObject.x++;
        }
    }

    private void moveLeft(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        if (x - 1 >= 0 && grid[y][x - 1] == 0) {
            grid[y][x] = 0;
            grid[y][x - 1] = gameObject.type;
            gameObject.x--;
        }
    }

    private void moveUp(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        if (y - 1 >= 0 && grid[y - 1][x] == 0) {
            grid[y][x] = 0;
            grid[y - 1][x] = gameObject.type;
            gameObject.y--;
        }
    }

    private void moveDown(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        if (y + 1 < grid.length && grid[y + 1][x] == 0) {
            grid[y][x] = 0;
            grid[y + 1][x] = gameObject.type;
            gameObject.y++;
        }
    }

    void setLevel() {
        level = LevelData.getLevel(currentLevel);
        gameBoard = level.getGameBoard();
        grid = gameBoard.getGrid();
        hasWon = false;

        // get position of octagon 1 and 2
        octagon1 = gameBoard.getObject1();
        octagon2 = gameBoard.getObject2();

        currentLevel++;

        // level restriction
        if (currentLevel > 3) currentLevel = 1;
    }

    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    Level getLevel() {
        return level;
    }

    boolean hasBeenWon() {
        return hasWon;
    }

    boolean hasBeenLost() {
        return hasLost;
    }
}
