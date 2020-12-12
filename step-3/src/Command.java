enum Command {
    UPPER_LEFT("U"),
    UPPER_LEFT_X2("U2"),
    UPPER_RIGHT("U'"),
    LEFT_BELOW("L"),
    LEFT_BELOW_X2("L2"),
    LEFT_UPPER("L'"),
    FRONT_RIGHT("F"),
    FRONT_RIGHT_X2("F2"),
    FRONT_LEFT("F'"),
    RIGHT_UPPER("R"),
    RIGHT_UPPER_X2("R2"),
    RIGHT_BELOW("R'"),
    BACK_LEFT("B"),
    BACK_LEFT_X2("B2"),
    BACK_RIGHT("B'"),
    DOWN_RIGHT("D"),
    DOWN_RIGHT_X2("D2"),
    DOWN_LEFT("D'"),
    QUIT("Q");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Command of(String value) {
        for (Command command : Command.values()) {
            if(command.getValue().equalsIgnoreCase(value)) {
                return command;
            }
        }
        return null;
    }
}