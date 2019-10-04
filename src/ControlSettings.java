public class ControlSettings {

    private int up;
    private int down;
    private int left;
    private int right;

    ControlSettings(int[] controls) {
        this.up = controls[0];
        this.right = controls[1];
        this.down = controls[2];
        this.left = controls[3];
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

}
