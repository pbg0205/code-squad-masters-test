import java.util.ArrayList;
import java.util.List;

public class CommandsSeperator {
    public static List<Command> makeCommands(String values) {
        List<String> valuesList = seperateValues(values);
        return toCommandList(valuesList);
    }

    private static List<String> seperateValues(String values) {
        List<String> valuesList = new ArrayList<>();
        int valueLen = values.length();
        String command;

        for (int index = 0; index < valueLen; index++) {
            if (index == valueLen - 1) {
                command = values.substring(index, index + 1);
                valuesList.add(command);
                continue;
            }
            if (values.charAt(index + 1) == '\'') {
                command = values.substring(index, index + 2);
                valuesList.add(command);
                index += 1;
                continue;
            }
            command = values.substring(index, index + 1);
            valuesList.add(command);
        }

        return valuesList;
    }

    private static List<Command> toCommandList(List<String> valuesList) {
        List<Command> commandList = new ArrayList<>();
        Command command;

        for (String value : valuesList) {
            command = Command.of(value);
            commandList.add(command);
        }
        return commandList;
    }
}