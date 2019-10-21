import java.io.*;

class GameController {

    private Level level;
    private GameBoard gameBoard;
    private int[][] grid;
    private GameObject octagon1;
    private GameObject octagon2;
    private TeleportMove teleportMove;
    private boolean teleporting;
    private boolean hasWon;
    private boolean hasLost;
    private int currentLevel;
    final int maxLevel = 7;

    GameController() {
        initialize();
    }

    private void initialize() {
        // initialize variables
        hasWon = false;
        hasLost = false;
        teleporting = false;

        // load level based on progress of player
        currentLevel = getProgress();
        setLevel();
    }

    int move(String keyCode) {
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

        if (levelHasBeenWon()) {
            int saveProgress = currentLevel + 1;
            if (saveProgress > 3) saveProgress = maxLevel;
            saveProgress(saveProgress);
            hasWon = true;
        }

        if (!hasLost && !hasWon && teleporting) return 2;
        return 1;
    }

    private boolean levelHasBeenWon() {
        return (Math.abs(octagon1.x - octagon2.x) == 1 && octagon1.y == octagon2.y) ||
                (Math.abs(octagon1.y - octagon2.y) == 1 && octagon1.x == octagon2.x);
    }

    private boolean levelHasBeenLost() {
        return (octagon1.x + 1 < grid[octagon1.y].length && grid[octagon1.y][octagon1.x + 1] == 7) ||
                (octagon1.x - 1 >= 0 && grid[octagon1.y][octagon1.x - 1] == 7) ||
                (octagon1.y + 1 < grid.length && grid[octagon1.y + 1][octagon1.x] == 7) ||
                (octagon1.y - 1 >= 0 && grid[octagon1.y - 1][octagon1.x] == 7) ||
                (octagon2.x + 1 < grid[octagon1.y].length && grid[octagon2.y][octagon2.x + 1] == 7) ||
                (octagon2.x - 1 >= 0 && grid[octagon2.y][octagon2.x - 1] == 7) ||
                (octagon2.y + 1 < grid.length && grid[octagon2.y + 1][octagon2.x] == 7) ||
                (octagon2.y - 1 >= 0 && grid[octagon2.y - 1][octagon2.x] == 7);
    }

    private void moveRight(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        // If possible to move, make move
        if (x + 1 < grid[y].length && (grid[y][x + 1] == 0 || grid[y][x + 1] == 8)) {
            if (grid[y][x + 1] == 8) teleportPreparation(y, x + 1, gameObject);
            grid[y][x] = 0;
            grid[y][x + 1] = gameObject.type;
            gameObject.x++;
        }
    }

    private void moveLeft(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        // If possible to move, make move
        if (x - 1 >= 0 && (grid[y][x - 1] == 0 || grid[y][x - 1] == 8)) {
            if (grid[y][x - 1] == 8) teleportPreparation(y, x - 1, gameObject);
            grid[y][x] = 0;
            grid[y][x - 1] = gameObject.type;
            gameObject.x--;
        }
    }

    private void moveUp(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        // If possible to move, make move
        if (y - 1 >= 0 && (grid[y - 1][x] == 0 || grid[y - 1][x] == 8)) {
            if (grid[y - 1][x] == 8) teleportPreparation(y - 1, x, gameObject);
            grid[y][x] = 0;
            grid[y - 1][x] = gameObject.type;
            gameObject.y--;
        }
    }

    private void moveDown(GameObject gameObject) {
        int x = gameObject.x;
        int y = gameObject.y;
        // If possible to move, make move
        if (y + 1 < grid.length && (grid[y + 1][x] == 0 || grid[y + 1][x] == 8)) {
            if (grid[y + 1][x] == 8) teleportPreparation(y + 1, x, gameObject);
            grid[y][x] = 0;
            grid[y + 1][x] = gameObject.type;
            gameObject.y++;
        }
    }

    void setLevel() {
        level = LevelData.getLevel(currentLevel);
        gameBoard = level.getGameBoard();
        grid = gameBoard.getGrid();
        // get position of octagon 1 and 2
        octagon1 = gameBoard.getObject1();
        octagon2 = gameBoard.getObject2();
    }

    void nextLevel() {
        hasWon = false;
        currentLevel++;
        // level restriction
        if (currentLevel > maxLevel) currentLevel = 1;
        setLevel();
    }

    void restartLevel() {
        hasLost = false;
        setLevel();
    }

    void teleportPreparation(int y, int x, GameObject gameObject) {
        grid[y][x] = 0;
        teleporting = true;
        // find second teleporter
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 8) {
                    teleportMove = new TeleportMove(x, y, j, i, gameObject);
                    break;
                }
            }
        }
    }

    void teleport() {
        teleporting = false;
        grid[teleportMove.y1][teleportMove.x1] = 0;
        grid[teleportMove.y2][teleportMove.x2] = teleportMove.gameObject.type;
        teleportMove.gameObject.x = teleportMove.x2;
        teleportMove.gameObject.y = teleportMove.y2;
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

    private int getProgress() {
        String fileName = "progress.txt";
        String line = "1";

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return Integer.parseInt(line);
    }

    private void saveProgress(int level) {
        String fileName = "progress.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(level));

            bufferedWriter.close();

        } catch (IOException ex) {
            System.out.println("Error writing to file");
        }
    }
}
