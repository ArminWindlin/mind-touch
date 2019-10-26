class ControlSettings {

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

    int getUp() {
        return up;
    }

    int getDown() {
        return down;
    }

    int getLeft() {
        return left;
    }

    int getRight() {
        return right;
    }

}
