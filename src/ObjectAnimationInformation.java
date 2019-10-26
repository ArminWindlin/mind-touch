class ObjectAnimationInformation {

    int x1;
    int y1;
    int x2;
    int y2;
    int type;
    int distance;
    int directionX;
    int directionY;


    ObjectAnimationInformation(int x1, int y1, int x2, int y2, int type) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.distance = 0;
        calculateDirection();
    }

    private void calculateDirection() {
        if (x1 < x2) {
            directionX = 1;
        } else if (x1 > x2) {
            directionX = -1;
        }
        if (y1 < y2) {
            directionY = 1;
        } else if (y1 > y2) {
            directionY = -1;
        }
    }
}
