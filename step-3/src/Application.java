import java.util.List;

class Application extends Thread {
    private Cube cube;
    private Timer timer;
    private FinishChecker finishChecker;

    Application() {
        init();
    }

    private void init() {
        this.cube = new Cube();
        this.finishChecker = new FinishChecker();
        this.timer = new Timer();
    }

    @Override
    public void run() {
        List<Command> commandList;
        boolean isFinished = false;

        cube.printStatus();

        while (!isFinished) {
            commandList = ValueSeperator.getCommandList(InputView.inputValues());
            isFinished = cube.rotateAndPrint(commandList);
        }
        quit();
    }

    public void quit() {
        boolean isWinner = finishChecker.isFinished(cube.getCube());
        if (isWinner) {
            printFinshedMessage();
        }
        printFinishMessage();
        System.exit(0);
    }

    private void printFinshedMessage() {
        System.out.println("\uD83E\uDD47큐브를 완성하셨습니다\uD83E\uDD47");
    }

    private void printFinishMessage() {
        timer.printFinishTime();
        System.out.println("경과시간: " + cube.getCount());
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }
}