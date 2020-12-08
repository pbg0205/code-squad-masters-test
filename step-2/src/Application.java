import java.util.List;

class Application extends Thread {
    private Cube cube;

    public Application() {
        this.cube = new Cube();
    }

    @Override
    public void run() {
        List<Command> commandList;
        while(true) {
            commandList = CommandsSeperator.makeCommands(InputView.inputValues());
            cube.move(commandList);
        }
    }
}
