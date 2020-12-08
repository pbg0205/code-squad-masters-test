import java.util.*;

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

    public void move(List<Command> commandList) {
        for (Command command : commandList) {
            moveByCommand(command);
        }
    }

    private void moveByCommand(Command command) {
        switch (command) {
            case TOP_LEFT:
                moveByDirection(0, -1, Direction.HORIZONTAL);
                break;
            case TOP_RIGHT:
                moveByDirection(0, 1, Direction.HORIZONTAL);
                break;
            case RIGHT_UPPER:
                moveByDirection(2, -1, Direction.VERTICAL);
                break;
            case RIGHT_BELOW:
                moveByDirection(2, 1, Direction.VERTICAL);
                break;
            case LEFT_BELOW:
                moveByDirection(0, 1, Direction.VERTICAL);
                break;
            case LEFT_UPPER:
                moveByDirection(0, -1, Direction.VERTICAL);
                break;
            case DOWN_RIGHT:
                moveByDirection(2, 1, Direction.HORIZONTAL);
                break;
            case DOWN_LEFT:
                moveByDirection(2, -1, Direction.HORIZONTAL);
                break;
            case QUIT:
                quit();
                break;
        }
        System.out.println(command.getValue());
        printStatus();
    }

    /*
     * @param axis          시작 행 또는 열의 인덱스를 나타내는 매개변수
     * @param movingNumber  위, 아래 또는 오른쪽, 왼쪽으로 이동 수를 나타내는 매개변수
     * @param direction     진행방향을 나타내는 매개변수
     */
    private void moveByDirection(int axis, int movingNumber, Direction direction) {
        Map<Integer, String> map = getLocationMap(axis, movingNumber, direction);
        int otherAxis;
        String cubePosition;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            otherAxis = entry.getKey();
            cubePosition = entry.getValue();

            if (direction == Direction.HORIZONTAL) {
                this.cube[axis][otherAxis] = cubePosition;
            }
            if (direction == Direction.VERTICAL) {
                this.cube[otherAxis][axis] = cubePosition;
            }
        }
    }

    /*
     * 원하는 위치에 전달할 인덱스와 cubePosition을 map에 저장한다.
     * @param axis          시작 행 또는 열의 인덱스를 나타내는 매개변수
     * @param movingNumber  위, 아래 또는 오른쪽, 왼쪽으로 이동 수를 나타내는 매개변수
     * @param direction     진행방향을 나타내는 매개변수
     */
    private Map<Integer, String> getLocationMap(int axis, int movingNumber, Direction direction) {
        Map<Integer, String> map = new HashMap<>();
        int index;

        for (int count = 0; count < CUBE_SIZE; count++) {
            index = getIndexBycount(count, movingNumber);
            if (direction == Direction.HORIZONTAL) {
                map.put(index, this.cube[axis][count]);
            }
            if (direction == Direction.VERTICAL) {
                map.put(index, this.cube[count][axis]);
            }
        }
        return map;
    }

    /*
     * 이동방향에 따라서 참조하는 인덱스를 계산하는 메서드
     */
    private int getIndexBycount(int count, int movingNumber) {
        int index = count + movingNumber;
        if (index == CUBE_SIZE) {
            index = 0;
        }

        if (index < 0) {
            index = CUBE_SIZE - 1;
        }
        return index;
    }

    private void quit() {
        System.exit(0);
    }

    private void printStatus() {
        String format = "";
        for (int row = 0; row < CUBE_SIZE; row++) {
            format += String.format("%s %s %s\n", cube[row][0], cube[row][1], cube[row][2]);
        }
        System.out.println(format);
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