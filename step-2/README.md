# codesquad-matsers-test

## 2단계: 평면 큐브 구현하기

### 🖥초기 화면
- [x] 초기화면은 3 X 3의 2차원 배열은 아래와 같이 초기화한다.
    ```
    R R W
    G C W
    G B B
    ```

### 🚀 기능 요구사항
- [x] 1.입력 : 처음 시작하면 초기 상태를 출력한다.
- [x] 2.간단한 프롬프트(CLI에서 키보드 입력받기 전에 표시해주는 간단한 글자들-예:CUBE>)를 표시해준다.
- [x] 3.한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다.
- [x] 4.사용자 입력은 받아서 아래의 동작을 하는 프로그램을 구현하시오.
    ```
    > U  가장 윗줄을 왼쪽으로 한 칸 밀기 RRW -> RWR
    > U' 가장 윗줄을 오른쪽으로 한 칸 밀기 RRW -> WRR
    > R  가장 오른쪽 줄을 위로 한 칸 밀기 WWB -> WBW
    > R' 가장 오른쪽 줄을 아래로 한 칸 밀기 WWB -> BWW
    > L  가장 왼쪽 줄을 아래로 한 칸 밀기 RGG -> GRG (L의 경우 R과 방향이 반대임을 주의한다.)
    > L' 가장 왼쪽 줄을 위로 한 칸 밀기 RGG -> GGR
    > B  가장 아랫줄을 오른쪽으로 한 칸 밀기 GBB -> BGB (B의 경우도 U와 방향이 반대임을 주의한다.)
    > B' 가장 아랫줄을 왼쪽으로 한 칸 밀기 GBB -> BBG
    > Q  Bye~를 출력하고 프로그램을 종료한다.
    ```
  
### 💻 기능 구현 화면
```
    R R W
    G C W
    G B B

    CUBE> URLB
    U
    R W R
    G C W
    G B B
    
    R
    R W W
    G C B
    G B R
    
    L
    G W W
    R C B
    G B R
    
    B
    G W W
    R C B
    R G B
```
```
    R R W
    G C W
    G B B
    
    CUBE> U'R'L'B'
    U'
    W R R
    G C W
    G B B
    
    R'
    W R B
    G C R
    G B W
    
    L'
    G R B
    G C R
    W B W
    
    B'
    G R B
    G C R
    B W W
```

### 👩‍🏫구현 설명
- 클래스 구성
```
    1. Main             : 프로그램이 작동하는 main method를 가지고 있는 클래스이다.
    2. Application      : 평면 큐브 프로그램 작동 로직을 담당한다.
    3. InputView        : 콘솔로 명령를 입력받는다.
    4. Command          : 명령에 대한 상수들을 모은 enum 클래스이다.
    5. CommandSeperator : 입력받은 명령어를 구분해서 List<Command>로 반환하는 기능.
    6. Cube             : 명령을 받아 큐브의 위치를 변경한다.
    7. Direction        : 작동 방향을 나타내는 상수들을 모아 enum 클래스이다.
```
- 구현 로직
1. Application.java
    ```java
        class Application extends Thread {
            private Cube cube;
        
            public Application() {
                this.cube = new Cube();
            }
        
            @Override
            public void run() {
                List<Command> commandList;
                System.out.println(cube);
                while(true) {
                    commandList = CommandsSeperator.makeCommands(InputView.inputValues());
                    cube.move(commandList);
                }
            }
        }
    ```
     Application클래스는 평면 큐브 프로그램을 전체 작동 로직을 담당하는 클래스입니다.
     해당 평면 큐브 프로그램의 로직을 아래와 같습니다.
     1. 콘솔을 통해 명령어를 입력받는다.
     2. 입력받은 명령어들을 CommandSeperator로 구분시켜 List로 반환한다.
     3. 해당 명령 리스트들을 전달받은 큐브는 명령어에 맞에 큐브를 움직인다.
     <br><br>
 2. InputView.java
     ```java
        import java.util.Scanner;
        
        public class InputView {
            private static final Scanner SCANNER = new Scanner(System.in);
        
            public static String inputValues() {
                System.out.print("CUBE> ");
                return SCANNER.nextLine();
            }
        }
    ```
    InputView 클래스는 1단계. 단어 밀어내기와 동일한 방식으로 구현하였습니다.   
    1. Scanner를 이용한 입력 방식을 선택했습니다.
    2. static 변수의 경우, 프로그램이 종료되기 전까지 반복해서 호출하는 점에서   
    프로그램 내 공용으로 변수로 선언하여 사용하는 것이 적합하다고 판단했습니다.
     <br><br>
 3. Command.java
     ```java
       public enum Command {
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
    ```
    Command.java는 명령어에 대한 값들을 모아둔 enum 클래스 입니다. 제가 명령어를 enum class로 작성한 이유는 크게 두가지 입니다.
    1. 명령어 별도의 상수로 처리하여 작동명 명확하게 파악하고,
    2. 명령어와 관련된 상수들을 한 공간에 관리하기 위함입니다.   
    
    그리고 기존 명령어와 명령어 상수값을 매칭시키기 위해서 열거형 상수에 기존 명령어의 값을 지정했습니다.
    ```java
        public static Command of(String value) {
            for (Command command : Command.values()) {
                if(command.getValue().equals(value)) {
                    return command;
                }
            }
            return null;
        }
    ```
    프로그램 내 기존의 명령어로 열거형 상수를 찾아야 하는 경우가 발생합니다. 이를 해결하기 위해 foreach문으로 각 열거형 상수들의
     지정 값을 확인하는 'of' 정적 함수를 선언하였습니다.
    - of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드.
 
 4. CommandSeperator.java           
 
     CommandSeperator.java는 크게 두가지 로직을 처리하게 됩니다.
    1. 콘솔로 입력된 명령어들을 탐색하며 기존 명령어를 구분한다.(UU'R -> U   U'  R)
    2. 구분된 기존 명령어와 Command(enum)의 열거형 상수와 맵핑한 새로운 열거형 상수 리스트를 반환한다.   
   
    ```
        public static List<Command> makeCommands(String values) {
            List<String> valuesList = seperateValues(values); //1번 과정
            return toCommandList(valuesList); //2번 과정
        }
    ```    
    
    1.콘솔로 입력된 명령어들을 탐색하며 기존 명령어를 구분한다.(UU'R -> U   U'  R)
    ```
        private static List<String> seperateValues(String values) {
            List<String> valuesList = new ArrayList<>();
            int valueLen = values.length();
            String command;
    
            for (int index = 0; index < valueLen; index++) {
                if (index == valueLen - 1) {
                    command = values.substring(index, index + 1);
                    valuesList.add(command);
                    continue;
                }
                if (values.charAt(index + 1) == '\'') {
                    command = values.substring(index, index + 2);
                    valuesList.add(command);
                    index += 1;
                    continue;
                }
                command = values.substring(index, index + 1);
                valuesList.add(command);
            }
    
            return valuesList;
        }
    ```
    해당 로직은 아래와 같이 진행합니다.
    1. 기존(single quote(-))일 경우, 한 글자의 명령어만 valuesList에 저장한다.
    2. 만약 index기준으로 뒤에 sigle quote(+)라면 single quote를 포함한 문자를 valuesList에 저장한다.
    3. index가 가장 마지막에 위치해 있을 경우는 단 하나의 명령어만 존재할 경우이므로, 해당 문자를 valueList에 저장한다.

    2.콘솔로 입력된 명령어들을 탐색하며 기존 명령어를 구분한다.(UU'R -> U   U'  R)
    ```
        private static List<Command> toCommandList(List<String> valuesList) {
            List<Command> commandList = new ArrayList<>();
            Command command;
    
            for (String value : valuesList) {
                command = Command.of(value);
                commandList.add(command);
            }
            return commandList;
        }
    ```
    1번 과정의 결과를 argument로 받아 해당 로직을 처리합니다. 이전의 Command class에서 지정된 값으로 적합한 열거형 상수를 
    반환하는 메서드를 말씀드렸습니다. 해당 메서드를 이용해서 해당 열거형 상수를 반환하여 commandList에 추가합니다.
    
5. Cube
 Cube class는 실제 큐브가 움직이는 로직을 담담하는 클래스입니다. 주요 로직은 CommandSeperator로 부터 받은 CommandList를 받아
 각 명령어에 맞춰 움직임을 처리합니다.
    ```java
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
       }
    ```
    우선 문제의 요구사항에 맞게 이차원 배열을 초기화하도록 합니다.
    <br>
    <br>
    
    ```java
       public class Cube{
           public void move(List<Command> commandList) {
               for (Command command : commandList) {
                   moveByCommand(command);
               }
           }
       }
   ```
   그리고 commandList를 argument로 받을 경우, 각각의 command에 따라 그에 맞는 로직을 처리하도록 합니다.
    <br>
    <br>
   
   ```java
   public class Cube {
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
   }
   ```   
   명령에 따른 로직을 구분하기 위해 switch문을 사용했습니다. 해당 커맨드는 각각의 요소들을 찾아 로직을 진행합니다.
   움직임 처리 완료 후, 현재 처리된 명령어와 cube의 현재 상태를 출력하는 메소드인 printStatus()를 하단에 처리했습니다.
  
   ```
   public class Cube {
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
   }
   ```
    위 로직은 시작 행(혹은 열), 움직임 방향, 진행 방향을 체크해서 상하좌우에 따른 로직을 한 메서드에서 처리할 수 있도록 했습니다.
      1. HashMap을 통해 Key값으로 입력될 인덱스 위치, vlaue값으로 입력될 큐브 색을 저장한다.
      2. entrySet을 이용해서 map을 순회한다.
      3. 작동 방향을 통해 입력할 위치를 파악해서 큐브 색을 입력한다.
  
  6. Direction
  ```java
    public enum Direction {
        VERTICAL,
        HORIZONTAL
    }
  ```
 해당 enum은 위의cube 로직에서 방향성을 나타내는 상수들의 enum class 입니다.