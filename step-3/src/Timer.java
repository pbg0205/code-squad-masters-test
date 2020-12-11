public class Timer {
    private long startTime;
    private long endTime;

    public Timer() {
        initStartTime();
    }

    private void initStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    private void initEndTime() {
        this.endTime = System.currentTimeMillis();
    }

    public void printFinishTime(){
        initEndTime();
        int elapsedSeconds = (int)((endTime - startTime) / 1000);
        System.out.printf("경과 시간: %02d:%02d\n", elapsedSeconds / 60, elapsedSeconds % 60);
    }
}
