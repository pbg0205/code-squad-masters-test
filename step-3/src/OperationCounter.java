public class OperationCounter {
    private int count;

    public OperationCounter() {
        this.count = 0;
    }

    public void addCount() {
        this.count++;
    }

    public void printCount(){
        System.out.println("조작횟수: " + this.count);
    }
}
