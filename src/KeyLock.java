public class KeyLock {
    private boolean isLocked;

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public KeyLock(){
        this.isLocked = false;
    }
}
