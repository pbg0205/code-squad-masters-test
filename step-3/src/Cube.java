import java.util.List;

class Cube {
    private static final int FACE_NUMBER = 6;
    private static final int CUBE_SIZE = 3;

    private OperationCounter operationCounter;
    private String[][][] cube;

    public Cube() {
        initCube();
        initCubeFaces();
        initOperationCounter();
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

    private void initOperationCounter() {
        this.operationCounter = new OperationCounter();
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

    public void rotate(List<Command> commandList) {
        for (Command command : commandList) {
            rotateByCommand(command);
        }
    }

    private void rotateByCommand(Command command) {
        switch (command) {
            case UPPER_LEFT:
                RotateClockwise(0);
                rotateUpperLeft();
                operationCounter.addCount();
                break;
            case UPPER_RIGHT:
                RotateAnticlockwise(0);
                rotateUpperRight();
                operationCounter.addCount();
                break;
            case LEFT_BELOW:
                RotateClockwise(2);
                rotateLeftBelow();
                operationCounter.addCount();
                break;
            case LEFT_UPPER:
                RotateAnticlockwise(2);
                rotateLeftUpper();
                operationCounter.addCount();
                break;
            case FRONT_RIGHT:
                RotateClockwise(3);
                rotateFrontRight();
                operationCounter.addCount();
                break;
            case FRONT_LEFT:
                RotateAnticlockwise(3);
                rotateFrontLeft();
                operationCounter.addCount();
                break;
            case RIGHT_UPPER:
                RotateClockwise(4);
                rotateRightUpper();
                operationCounter.addCount();
                break;
            case RIGHT_BELOW:
                RotateAnticlockwise(4);
                rotateRightBelow();
                operationCounter.addCount();
                break;
            case BACK_LEFT:
                RotateClockwise(1);
                rotateBackLeft();
                operationCounter.addCount();
                break;
            case BACK_RIGHT:
                RotateAnticlockwise(1);
                rotateBackRight();
                operationCounter.addCount();
                break;
            case DOWN_RIGHT:
                RotateClockwise(5);
                rotateDownRight();
                operationCounter.addCount();
                break;
            case DOWN_LEFT:
                RotateAnticlockwise(5);
                rotateDownLeft();
                operationCounter.addCount();
                break;
            case QUIT:
                quit();
                break;
        }
    }

    private void RotateClockwise(int faceNumber) {
        String temp;

        temp = cube[faceNumber][2][0];
        cube[faceNumber][2][0] = cube[faceNumber][2][2];
        cube[faceNumber][2][2] = cube[faceNumber][0][2];
        cube[faceNumber][0][2] = cube[faceNumber][0][0];
        cube[faceNumber][0][0] = temp;

        temp = cube[faceNumber][2][1];
        cube[faceNumber][2][1] = cube[faceNumber][1][2];
        cube[faceNumber][1][2] = cube[faceNumber][0][1];
        cube[faceNumber][0][1] = cube[faceNumber][1][0];
        cube[faceNumber][1][0] = temp;
    }

    private void RotateAnticlockwise(int faceNumber) {
        String temp;

        temp = cube[faceNumber][0][0];
        cube[faceNumber][0][0] = cube[faceNumber][0][2];
        cube[faceNumber][0][2] = cube[faceNumber][2][2];
        cube[faceNumber][2][2] = cube[faceNumber][2][0];
        cube[faceNumber][2][0] = temp;

        temp = cube[faceNumber][1][0];
        cube[faceNumber][1][0] = cube[faceNumber][0][1];
        cube[faceNumber][0][1] = cube[faceNumber][1][2];
        cube[faceNumber][1][2] = cube[faceNumber][2][1];
        cube[faceNumber][2][1] = temp;
    }

    private void rotateUpperLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[1][0][index];
            cube[1][0][index] = cube[2][0][index];
            cube[2][0][index] = cube[3][0][index];
            cube[3][0][index] = cube[4][0][index];
            cube[4][0][index] = temp;
        }
    }

    private void rotateUpperRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[4][0][index];
            cube[4][0][index] = cube[3][0][index];
            cube[3][0][index] = cube[2][0][index];
            cube[2][0][index] = cube[1][0][index];
            cube[1][0][index] = temp;
        }
    }

    private void rotateLeftBelow() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[1][2-index][2];
            cube[1][2-index][2] = cube[5][index][0];
            cube[5][index][0] = cube[3][index][0];
            cube[3][index][0] = cube[0][index][0];
            cube[0][index][0] = temp;
        }
    }

    private void rotateLeftUpper() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][index][0];
            cube[0][index][0] = cube[3][index][0];
            cube[3][index][0] = cube[5][index][0];
            cube[5][index][0] = cube[1][2-index][2];
            cube[1][2-index][2] = temp;
        }
    }

    private void rotateFrontRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[2][2-index][2];
            cube[2][2-index][2] = cube[5][0][2-index];
            cube[5][0][2-index] = cube[4][index][0];
            cube[4][index][0] = cube[0][2][index];
            cube[0][2][index] = temp;
        }
    }

    private void rotateFrontLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][2][index];
            cube[0][2][index] = cube[4][index][0];
            cube[4][index][0] = cube[5][0][2-index];
            cube[5][0][2-index] = cube[2][2-index][2];
            cube[2][2-index][2] = temp;
        }
    }

    private void rotateRightUpper() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[3][2 - index][2];
            cube[3][2 - index][2] = cube[5][2 - index][2];
            cube[5][2 - index][2] = cube[1][index][0];
            cube[1][index][0] = cube[0][2 - index][2];
            cube[0][2 - index][2] = temp;
        }
    }

    private void rotateRightBelow() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][2 - index][2];
            cube[0][2 - index][2] = cube[1][index][0];
            cube[1][index][0] = cube[5][2 - index][2];
            cube[5][2 - index][2] = cube[3][2 - index][2];
            cube[3][2 - index][2] = temp;
        }
    }

    private void rotateBackLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[4][2 - index][2];
            cube[4][2][2] = cube[5][index][0];
            cube[5][index][0] = cube[2][index][0];
            cube[2][index][0] = cube[0][0][2 - index];
            cube[0][0][2 - index] = temp;
        }
    }

    private void rotateBackRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][0][2 - index];
            cube[0][0][2 - index] = cube[2][index][0];
            cube[2][index][0] = cube[5][index][0];
            cube[5][index][0] = cube[4][2 - index][2];
            cube[4][2 - index][2] = temp;
        }
    }

    private void rotateDownRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[1][2][index];
            cube[1][2][index] = cube[2][2][index];
            cube[2][2][index] = cube[3][2][index];
            cube[3][2][index] = cube[4][2][index];
            cube[4][2][index] = temp;
        }
    }

    private void rotateDownLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[4][2][index];
            cube[4][2][index] = cube[3][2][index];
            cube[3][2][index] = cube[2][2][index];
            cube[2][2][index] = cube[1][2][index];
            cube[1][2][index] = temp;
        }
    }

    private void quit() {
        operationCounter.printCount();
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
        System.exit(0);
    }

    public void printStatus() {
        printUpperOrBelow(0);
        printSides();
        printUpperOrBelow(5);
    }

    private void printSides() {
        for (int row = 0; row < CUBE_SIZE; row++) {
            System.out.printf("%s %s %s %5s %s %s %s %5s %s %s %s %5s %s %s %s \n",
                    cube[1][row][0], cube[1][row][1], cube[1][row][2], " ",
                    cube[2][row][0], cube[2][row][1], cube[2][row][2], " ",
                    cube[3][row][0], cube[3][row][1], cube[3][row][2], " ",
                    cube[4][row][0], cube[4][row][1], cube[4][row][2]);
        }
        System.out.println();
    }

    private void printUpperOrBelow(int faceNumber) {
        for (int row = 0; row < CUBE_SIZE; row++) {
            System.out.printf("%17s %s %s %s\n",
                    " ", cube[faceNumber][row][0], cube[faceNumber][row][1], cube[faceNumber][row][2]);
        }
        System.out.println();
    }
}