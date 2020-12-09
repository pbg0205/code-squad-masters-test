class Cube {
    private static final int FACE_NUMBER = 6;
    private static final int CUBE_SIZE = 3;

    private String[][][] cube;

    public Cube() {
        initCube();
        initCubeFaces();
    }

    private void initCube() {
        this.cube = new String[FACE_NUMBER][CUBE_SIZE][CUBE_SIZE];
    }

    private void initCubeFaces() {
        int faceNumber = 0;
        for (CubeColor color : CubeColor.values()) {
            initCubeFace(faceNumber++, color);
        }
    }

    private void initCubeFace(int faceNumber, CubeColor color) {
        for (int row = 0; row < CUBE_SIZE; row++) {
            initCubeFaceColumn(faceNumber, color, row);
        }
    }

    private void initCubeFaceColumn(int faceNumber, CubeColor color, int row) {
        for (int col = 0; col < CUBE_SIZE; col++) {
            this.cube[faceNumber][row][col] = color.getColor();
        }
    }
}