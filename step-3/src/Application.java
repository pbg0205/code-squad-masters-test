import java.util.List;

class Application extends Thread {
    private Cube cube;
    private Timer timer;
    private WinnerChecker winnerChecker;

    Application() {
        init();
    }

    private void init() {
        this.cube = new Cube();
        this.winnerChecker = new WinnerChecker();
        this.timer = new Timer();
    }

    @Override
    public void run() {
        List<Command> commandList;
        boolean isFinished = false;

        cube.printStatus();

        while (!isFinished) {
            commandList = ValueSeperator.getCommandList(InputView.inputValues());
            isFinished = cube.rotate(commandList);
        }
        quit();
    }

    public void quit() {
        boolean isWinner = winnerChecker.checkWinner(cube.getCube());
        if (isWinner) {
            printFinshedMessage();
        }
        printFinishMessage();
        System.exit(0);
    }

    private void printFinshedMessage() {
        cube.printStatus();
        System.out.println("\n\uD83E\uDD47큐브를 완성하셨습니다\uD83E\uDD47");
    }

    private void printFinishMessage() {
        timer.printFinishTime();
        System.out.println("경과시간: " + cube.getCount());
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }
}