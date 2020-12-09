enum CubeColor {
    BLUE("B"),
    WHITE("W"),
    ORANGE("O"),
    GRAY("G"),
    YELLOW("Y"),
    RED("R");

    private final String color;

    CubeColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}