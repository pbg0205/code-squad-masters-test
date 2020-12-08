public enum  Command {
    TOP_LEFT("U"),
    TOP_RIGHT("U'"),
    RIGHT_UPPER("R"),
    RIGHT_BELOW("R'"),
    LEFT_BELOW("L"),
    LEFT_UPPER("L'"),
    DOWN_RIGHT("B"),
    DOWN_LEFT("B'"),
    QUIT("Q");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
