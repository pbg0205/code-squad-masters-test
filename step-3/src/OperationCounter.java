public class OperationCounter {
    private int count;

    public OperationCounter() {
        this.count = 0;
    }

    public void addCount() {
        this.count++;
    }

    public int getCount() {
        return count;
    }
}