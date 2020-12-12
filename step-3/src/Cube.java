import java.util.List;

class Cube {
    private static final int FACE_NUMBER = 6;
    private static final int CUBE_SIZE = 3;

    private OperationCounter operationCounter;
    private WinnerChecker winnerChecker;
    private String[][][] cube;

    public Cube() {
        initCube();
        initCubeFaces();
        initFinishChecker();
        initOperationCounter();
        initRotate(new RandomMixer().getRandomList());
    }

    private void initFinishChecker() {
        winnerChecker = new WinnerChecker();
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

    public String[][][] getCube() {
        return cube;
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

    private void initRotate(List<Command> commandList) {
        for (Command command : commandList) {
            if (command == null) {
                continue;
            }
            rotateByCommand(command);
        }
    }

    public boolean rotate(List<Command> commandList) {
        boolean exit;

        for (Command command : commandList) {
            if (command == null) {
                continue;
            }
            exit = rotateByCommand(command);

            if(winnerChecker.checkWinner(getCube())){
                return true;
            }

            if(exit) {
                return true;
            }

            if(!exit) {
                operationCounter.addCount();
                printCommandAndStatus(command);
            }
        }
        return false;
    }

    private boolean rotateByCommand(Command command) {
        switch (command) {
            case UPPER_LEFT:
                rotateClockwise(0);
                rotateUpperLeft();
                return false;
            case UPPER_LEFT_X2:
                rotateUpperLeft180();
                return false;
            case UPPER_RIGHT:
                rotateAnticlockwise(0);
                rotateUpperRight();
                return false;
            case LEFT_BELOW:
                rotateClockwise(2);
                rotateLeftBelow();
                return false;
            case LEFT_BELOW_X2:
                rotateLeftBelow180();
                return false;
            case LEFT_UPPER:
                rotateAnticlockwise(2);
                rotateLeftUpper();
                return false;
            case FRONT_RIGHT:
                rotateClockwise(3);
                rotateFrontRight();
                return false;
            case FRONT_RIGHT_X2:
                rotateFrontRight180();
                return false;
            case FRONT_LEFT:
                rotateAnticlockwise(3);
                rotateFrontLeft();
                return false;
            case RIGHT_UPPER:
                rotateClockwise(4);
                rotateRightUpper();
                return false;
            case RIGHT_UPPER_X2:
                rotateRightUpper180();
                return false;
            case RIGHT_BELOW:
                rotateAnticlockwise(4);
                rotateRightBelow();
                return false;
            case BACK_LEFT:
                rotateClockwise(1);
                rotateBackLeft();
                return false;
            case BACK_LEFT_X2:
                rotateBackLeft180();
                return false;
            case BACK_RIGHT:
                rotateAnticlockwise(1);
                rotateBackRight();
                return false;
            case DOWN_RIGHT:
                rotateClockwise(5);
                rotateDownRight();
                return false;
            case DOWN_RIGHT_X2:
                rotateDownRight180();
                return false;
            case DOWN_LEFT:
                rotateAnticlockwise(5);
                rotateDownLeft();
                return false;
            case QUIT:
                return true;
        }
        return false;
    }

    private void rotateUpperLeft180() {
        rotateClockwise(0);
        rotateUpperLeft();
    }

    private void rotateLeftBelow180() {
        rotateClockwise(2);
        rotateLeftBelow();
    }

    private void rotateFrontRight180() {
        rotateClockwise(3);
        rotateFrontRight();
    }

    private void rotateRightUpper180() {
        rotateClockwise(4);
        rotateRightUpper();
    }

    private void rotateBackLeft180() {
        rotateClockwise(1);
        rotateBackLeft();
    }

    private void rotateDownRight180() {
        rotateClockwise(5);
        rotateDownRight();
    }

    private void printCommandAndStatus(Command command) {
        System.out.println("명령어 : " + command.getValue());
        printStatus();
    }

    private void rotateClockwise(int faceNumber) {
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

    private void rotateAnticlockwise(int faceNumber) {
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
            temp = cube[1][2 - index][2];
            cube[1][2 - index][2] = cube[5][index][0];
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
            cube[5][index][0] = cube[1][2 - index][2];
            cube[1][2 - index][2] = temp;
        }
    }

    private void rotateFrontRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[2][2 - index][2];
            cube[2][2 - index][2] = cube[5][0][2 - index];
            cube[5][0][2 - index] = cube[4][index][0];
            cube[4][index][0] = cube[0][2][index];
            cube[0][2][index] = temp;
        }
    }

    private void rotateFrontLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][2][index];
            cube[0][2][index] = cube[4][index][0];
            cube[4][index][0] = cube[5][0][2 - index];
            cube[5][0][2 - index] = cube[2][2 - index][2];
            cube[2][2 - index][2] = temp;
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
            cube[4][2 - index][2] = cube[5][2][index];
            cube[5][2][index] = cube[2][index][0];
            cube[2][index][0] = cube[0][0][2 - index];
            cube[0][0][2 - index] = temp;
        }
    }

    private void rotateBackRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[0][0][2 - index];
            cube[0][0][2 - index] = cube[2][index][0];
            cube[2][index][0] = cube[5][2][index];
            cube[5][2][index] = cube[4][2 - index][2];
            cube[4][2 - index][2] = temp;
        }
    }

    private void rotateDownRight() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[4][2][index];
            cube[4][2][index] = cube[3][2][index];
            cube[3][2][index] = cube[2][2][index];
            cube[2][2][index] = cube[1][2][index];
            cube[1][2][index] = temp;
        }
    }

    private void rotateDownLeft() {
        String temp;
        for (int index = 0; index < CUBE_SIZE; index++) {
            temp = cube[1][2][index];
            cube[1][2][index] = cube[2][2][index];
            cube[2][2][index] = cube[3][2][index];
            cube[3][2][index] = cube[4][2][index];
            cube[4][2][index] = temp;
        }
    }

    public int getCount() {
        return operationCounter.getCount();
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