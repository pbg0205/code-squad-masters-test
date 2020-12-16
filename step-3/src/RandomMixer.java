import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMixer {
    private static final int COMMANDS_SIZE_EXCEPT_QUIT = 11;

    public List<Command> getRandomList() {
        List<Command> commandList = new ArrayList<>();
        Command[] commands = Command.values();
        Random ramdomNumber;

        for (int count = 0; count < 10; count++) {
            ramdomNumber = new Random();
            commandList.add(commands[ramdomNumber.nextInt(COMMANDS_SIZE_EXCEPT_QUIT)]);
        }

        return commandList;
    }
}
