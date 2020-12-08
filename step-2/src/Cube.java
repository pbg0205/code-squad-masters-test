import java.util.Arrays;

public class Cube {
    private static final int CUBE_SIZE = 3;
    private String[][] cube;

    public Cube() {
        initCube();
    }

    private void initCube() {
        this.cube = new String[][]{{"R", "R", "W"},
                {"G", "C", "W"},
                {"G", "B", "B"}};
    }

    @Override
    public String toString() {
        String format = "";

        for (int row = 0; row < CUBE_SIZE; row++) {
            format += String.format("%s %s %s\n", cube[row][0], cube[row][1], cube[row][2]);
        }
        return format;
    }
}
