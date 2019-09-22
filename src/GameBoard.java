public class GameBoard {

    private int[][] grid;

    GameBoard(int[][] grid) {
        this.grid = grid;
    }

    GameObject getObject1() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 1) return new GameObject(x, y);
            }
        }
        return new GameObject(1, 1);
    }

    GameObject getObject2() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 2) return new GameObject(x, y);
            }
        }
        return new GameObject(1, 1);
    }


    int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
