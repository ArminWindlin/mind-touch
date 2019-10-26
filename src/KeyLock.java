class KeyLock {
    private boolean isLocked;

    boolean isLocked() {
        return isLocked;
    }

    void lock() {
        isLocked = true;
    }

    void unlock() {
        isLocked = false;
    }

    KeyLock(){
        this.isLocked = false;
    }
}
