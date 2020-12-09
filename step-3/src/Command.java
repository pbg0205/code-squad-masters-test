enum Command {
    UPPER_LEFT("U"),
    UPPER_RIGHT("U'"),
    LEFT_BELOW("L"),
    LEFT_UPPER("L'"),
    FRONT_RIGHT("F"),
    FRONT_LEFT("F'"),
    RIGHT_UPPER("R"),
    RIGHT_BELOW("R'"),
    BACK_LEFT("B"),
    BACK_RIGHT("B'"),
    DOWN_RIGHT("D"),
    DOWN_LEFT("D'");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Command of(String value) {
        for (Command command : Command.values()) {
            if(command.getValue().equals(value)) {
                return command;
            }
        }
        return null;
    }
}
