class FinishChecker {
    private static final int FACE_NUMBER = 6;
    private static final int CUBE_SIZE = 3;
    private String[][][] cube;

    private void initCube(String[][][] cube) {
        this.cube = cube;
    }

    public boolean isFinished(String[][][] cube) {
        initCube(cube);

        for (int faceNumber = 0; faceNumber < FACE_NUMBER; faceNumber++) {
            String cubeColor = findColor(faceNumber);
            if(!isSameColorFace(faceNumber, cubeColor)){
                return false;
            }
        }
        return true;
    }

    private String findColor(int faceNumber) {
        for (CubeColor color : CubeColor.values()) {
            if(this.cube[faceNumber][0][0].equals(color.getColor())){
                return color.getColor();
            }
        }
        return null;
    }

    private boolean isSameColorFace(int faceNumber, String cubeColor) {
        for (int row = 0; row < CUBE_SIZE; row++) {
            if(!isSameColor(faceNumber, row, cubeColor)){
                return false;
            }
        }
        return true;
    }

    private boolean isSameColor(int faceNumber, int row, String cubeColor) {
        for (int col = 0; col < CUBE_SIZE; col++) {
            if(!cube[faceNumber][row][col].equals(cubeColor)){
                return false;
            }
        }
        return true;
    }
}
