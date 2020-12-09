import java.util.List;

class Application extends Thread {
    private Cube cube;

    Application() {
        this.cube = new Cube();
    }

    @Override
    public void run() {
        List<Command> commandList;

        while(true) {
            cube.printStatus();
            commandList = ValueSeperator.getCommandList(InputView.inputValues());
            cube.rotate(commandList);
        }
    }
}