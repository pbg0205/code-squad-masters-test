# codesquad-matsers-test

## 3단계 : 루빅스 큐브 구현하기

### 🖥초기 상태
- [x] 큐브는 W, B, G, Y, O, R의 6가지 색깔을 가지고 있다.
- [x] 큐브의 6면을 펼친 상태로 출력한다.
    ```
                    B B B  
                    B B B
                    B B B
    
     W W W     O O O     G G G     Y Y Y 
     W W W     O O O     G G G     Y Y Y 
     W W W     O O O     G G G     Y Y Y 
     
                    R R R 
                    R R R 
                    R R R 
    ```
  
### 🚀 기능 요구사항
- [x] 1.초기 출력 : 처음 시작하면 초기 상태를 출력한다.
- [x] 2.입력: 각 조잡법을 한 줄로 입력받는다.
- [x] 3.간단한 프롬프트(CLI에서 키보드 입력받기 전에 표시해주는 간단한 글자들-예:CUBE>)를 표시해준다.
- [x] 4.한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다.
- [x] 5.Q 입력을받으면 프로그램을 종료하고, 조작 받은 명령의 갯수를 출력시킨다.

### ➕ 추가 기능 요구사항
- [x] 1.프로그램 종료 시 경과 시간 출력
- [x] 2.큐브의 무작위 섞기 기능
- [x] 3.모든 면을 맞추면 축하 메시지와 함께 프로그램을 자동 종료

### 💻 기능 구현 화면
#### 1. 초기화면
```
[무작위 섞기 기능 구현 전]

                  B B B
                  B B B
                  B B B

W W W       O O O       G G G       Y Y Y 
W W W       O O O       G G G       Y Y Y 
W W W       O O O       G G G       Y Y Y 

                  R R R
                  R R R
                  R R R

CUBE> 
```
```
[무작위 섞기 기능 적용 후]

                  B B O
                  B B O
                  Y B B

G W W       O O B       G Y Y       W R R 
W W W       O O B       G G G       Y Y Y 
W W Y       R R B       G G O       R Y Y 

                  O O W
                  G R R
                  G R R

CUBE> 
```

#### 2.큐브 시계 방향 작동 화면
```
[U 기능 구현]                                           [U' 기능 구현]

CUBE> U                                                 CUBE> U'
명령어 : U                                              명령어 : U'
                  B B B                                                   B B B
                  B B B                                                   B B B
                  B B B                                                   B B B

O O O       G G G       Y Y Y       W W W               Y Y Y       W W W       O O O       G G G 
W W W       O O O       G G G       Y Y Y               W W W       O O O       G G G       Y Y Y 
W W W       O O O       G G G       Y Y Y               W W W       O O O       G G G       Y Y Y  

                  R R R                                                   R R R
                  R R R                                                   R R R
                  R R R                                                   R R R
```
```
[L 기능 구현]                                           [L' 기능 구현]

CUBE> L                                                 CUBE> L'
명령어 : L                                              명령어 : L'
                  W B B                                                   G B B
                  W B B                                                   G B B
                  W B B                                                   G B B

W W R       O O O       B G G       Y Y Y               W W B       O O O       R G G       Y Y Y  
W W R       O O O       B G G       Y Y Y               W W B       O O O       R G G       Y Y Y  
W W R       O O O       B G G       Y Y Y               W W B       O O O       R G G       Y Y Y 

                  G R R                                                   W R R     
                  G R R                                                   W R R     
                  G R R                                                   W R R     
```
```
[F 기능 구현]                                            [F' 기능 구현]                                          

CUBE> F                                                 CUBE> F
명령어 : F                                              명령어 : F
                  B B B                                                   B B B
                  B B B                                                   B B B
                  O O O                                                   Y Y Y

W W W       O O R       G G G       B Y Y               W W W       O O B       G G G       R Y Y 
W W W       O O R       G G G       B Y Y               W W W       O O B       G G G       R Y Y 
W W W       O O R       G G G       B Y Y               W W W       O O B       G G G       R Y Y 

                  Y Y Y                                                   O O O
                  R R R                                                   R R R
                  R R R                                                   R R R

```
```
[R 기능 구현]                                            [R' 기능 구현] 

CUBE> R                                                 CUBE> R'
명령어 : R                                              명령어 : R'
                  B B G                                                   B B W
                  B B G                                                   B B W
                  B B G                                                   B B W

B W W       O O O       G G R       Y Y Y               R W W       O O O       G G B       Y Y Y
B W W       O O O       G G R       Y Y Y               R W W       O O O       G G B       Y Y Y
B W W       O O O       G G R       Y Y Y               R W W       O O O       G G B       Y Y Y

                  R R W                                                   R R G
                  R R W                                                   R R G
                  R R W                                                   R R G
```
```
[B 기능 구현]

CUBE> B                                                 CUBE> B'
명령어 : B                                              명령어 : B'
                  Y Y Y                                                   O O O
                  B B B                                                   B B B
                  B B B                                                   B B B

W W W       B O O       G G G       Y Y R               W W W       R O O       G G G       Y Y B
W W W       B O O       G G G       Y Y R               W W W       R O O       G G G       Y Y B
W W W       B O O       G G G       Y Y R               W W W       R O O       G G G       Y Y B

                  R R R                                                   R R R
                  R R R                                                   R R R
                  O O O                                                   Y Y Y
```
```
[D 기능 구현]

CUBE> D                                                 CUBE> D'
명령어 : D                                              명령어 : D'
                  B B B                                                   B B B
                  B B B                                                   B B B
                  B B B                                                   B B B

W W W       O O O       G G G       Y Y Y               W W W       O O O       G G G       Y Y Y
W W W       O O O       G G G       Y Y Y               W W W       O O O       G G G       Y Y Y 
Y Y Y       W W W       O O O       G G G               Y Y Y       W W W       O O O       G G G  

                  R R R                                                   R R R
                  R R R                                                   R R R
                  R R R                                                   R R R

```

### 👩‍🏫구현 설명
- 클래스 구성
```
    1. Main             : 프로그램이 작동하는 main method 포함 클래스
    2. Application      : 평면 큐브 프로그램 작동 로직
    3. InputView        : 콘솔로 명령를 입력
    4. Command          : 명령에 대한 상수들의 집합
    5. ValueSeperator   : 입력받은 명령어를 구분 및 List<Command> 반환 기능
    6. Cube             : 명령을 받아 큐브의 위치를 변경
    7. CubeColor        : 큐브들의 각 면의 색깔 집합
    8. OperationCounter : 조작 갯수를 연산 및 확인 기능
    [추가 기능 클래스]
    8. Timer            : 경과 시간을 측정 기능
    9.RandomMixer       : 초기 큐브 섞는 기능
    10.WinnerChecker    : 승리 조건 확인 기능
```

- 큐브 구현 로직  
    1. 이번 미션은 전개도를 기준으로 큐브의 움직임을 표현하는 방식으로 구현했습니다.   
    2. 그래서 각 면의 위치를 기준으로 네 방향에서 접하는 면의 상태를 확인하고자 하였습니다.   
 

- 큐브 로직 구현   
    1. 미션2와 전체적인 클래스 구성이 유사합니다.   
    2. 미션3의 변경사항에 대해 중점적으로 설명하겠습니다.

1. Cube.java   
        (1) Cube 초기화
     ```java
        class Cube{
                public Cube() {
                    initCube();
                    initCubeFaces();
                }
                
                private void initCubeFaces() {
                    int faceNumber = 0;
                    for (CubeColor color : CubeColor.values()) {
                        initCubeFace(faceNumber++, color);
                    }
                }
        } 
    ```
     우선 요구사항에 있는 각 면의 색을 입력하기 위해 CubeColor enum을 선언 후, 해당 색으로 초기화할 수 있도록 하였습니다.
    <br><br>
    
    (2) cube 회전 로직 구현
     이번 미션의 핵심은 큐브의 전개도를 표현하는 방법이었습니다.
    큐브의 면에 따라서 움직이는 로직을 다르게 처리해주어야 하기 때문입니다. 그래서 우선적으로 큐브의 전개도와 각 면 주변의 4면의
     상태을 그려보았습니다.
    - 큐브 이미지 화면
         
     |전체 큐브 전개도|
     |--------------|
     |![큐브이미지](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_location.PNG)|   
     
    (2) 각 면의 4방향 면의 위치  
     
    U 기준 | F 기준 | D 기준 |
    ---|---|---|
    ![U기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_U_location.PNG)|![F기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_F_location.PNG)|![D기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_D_location.PNG)|
    
    L 기준 | R 기준 | B 기준 |
    ---|---|---|
    ![L기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_L_location.PNG)|![R기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_R_location.PNG)|![B기준](https://github.com/pbg0205/code-squad-masters-test/blob/step-3/step-3/image/cube_B_location.PNG)|
    
    우선 해당 면이 회전 방향에 따라 변하는 움직임을 정리하였습니다.
    ```
    시계 방향으로 회전
    - cube[faceNumber][0][0] → cube[faceNumber][0][2] → cube[faceNumber][2][2] → cube[faceNumber][2][0]
    - cube[faceNumber][1][0] → cube[faceNumber][0][1] → cube[faceNumber][1][2] → cube[faceNumber][2][1]
    
    반시계 방향으로 회전
    - cube[faceNumber][2][0] → cube[faceNumber][2][2] → cube[faceNumber][0][2] → cube[faceNumber][0][0]
    - cube[faceNumber][2][1] → cube[faceNumber][1][2] → cube[faceNumber][0][1] → cube[faceNumber][1][0]
    ```
    
    또한 해당 면의 회전과 동시에 주변의 면 또한 큐브 위치가 변경됩니다.   
    그래서 각 면 주변의 면의 상태를 확인하고 회전 시 달라지는 각 큐브의 위치를 정리한 후, 코드로 구현하였습니다.
    
    **<명령어에 따른 큐브의 이동방향>**
    ```
    U: 윗면 시계 방향 회전
    - cube[4][0][index] → cube[3][0][index] → cube[2][0][index] → cube[1][0][index]
    U': 윗면 반시계 방향 회전(U와 반대)
    - cube[1][0][index] → cube[2][0][index] → cube[3][0][index] → cube[4][0][index]
    ```
   ```
    F: 앞면 시계 방향 회전
    - cube[0][2][index]  → cube[4][index][0] → cube[5][0][2 - index] → cube[2][2 - index][2]
    F': 앞면 반시계 방향 회전(F와 반대)
    - cube[2][2 - index][2] → cube[5][0][2 - index] → cube[4][index][0] → cube[0][2][index]
    ```
   ```
    D : 아랫면 시계 방향 회전
    - cube[1][2][index] → cube[2][2][index] → cube[3][2][index] → cube[4][2][index]
    D' : 아랫면 반시계 방향 회전(D와 반대)
    - cube[4][2][index] → cube[3][2][index] → cube[2][2][index] → cube[1][2][index]
    ```
   ```
    L : 왼쪽 면 시계 방향 회전
    - cube[0][index][0] → cube[3][index][0] → cube[5][index][0] → cube[1][2 - index][2]
    L : 왼쪽 면 반시계 방향 회전
    - cube[1][2 - index][2] → cube[5][index][0] → cube[3][index][0] → cube[0][index][0]
    ```
   ```
    R : 오른 쪽 면 시계 방향 회전
    - cube[0][2 - index][2] → cube[1][index][0] → cube[5][2 - index][2] → cube[3][2 - index][2]
    R': 오른 쪽 면 반시계 방향 회전(R과 반대)
    - cube[3][2 - index][2] → cube[5][2 - index][2] → cube[1][index][0] → cube[0][2 - index][2]
    ```
   ```
    B : 뒤쪽 면 시계 방향 회전
    - cube[0][0][2 - index] → cube[2][index][0] → cube[5][2][index] → cube[4][2][2]
    B': 뒤쪽 면 반시계 방향 회전(B와 반대)
    - cube[4][2][2] → cube[5][2][index] → cube[2][index][0] → cube[0][0][2 - index]
    ```
    
    큐브들의 위치 변경을 정리한 내용들을 기준으로 코드로 그대로 값이 위치가 바뀌는 로직으로 구현하였습니다.
    변경 로직형태가 반복되므로 아래 예시 하나로 대체하겠습니다.
    ```java
    class Cube {
    //시계방향으로 해당 면 회전
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
    //F : 앞면 시계 방향 회전함과 동시에 주변 면 상태 변화 로직
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
    }
    ``` 
    <br><br>

2. OperationCounter.java   
    ```java
    class Cube {
       private OperationCounter operationCounter;
       
       private Cube() {
           initOperationCounter();
       }   
       
       private void initOperationCounter() {
           this.operationCounter = new OperationCounter();
       }
    }
    ```
    종료 시, 조작 갯수를 반환하는 요구사항을 구현하기 위해 생성한 클래스 입니다. operationCounter는 조작횟수를 집계하여야 하기
    때문에 Cube class의 composition으로 선언하였습니다.

   ```java
       public class OperationCounter {
           private int count;
       
           public OperationCounter() {
               this.count = 0;
           }
       
           public void addCount() {
               this.count++;
           }
       } 
   ```
    내부 로직은 조작 횟수를 집계하기 위한 연산을 추가하였습니다.
    <br><br>

   ```java
       class Cube {
               private void rotateByCommand(Command command) {
                       switch (command) {
                           //회전 로직
                       }
                       operationCounter.addCount();
                       printCommandAndStatus(command);
               }
       }
    ```
    해당 클래스를 구현 후, 회전이 완료됨을 처리하기 위해서 조작이 완료된 가장 마지막 위치에 addCount() method를 처리하였습니다.   
    <br><br>
    
**<추가 기능 클래스>**   
3. Timer
    ```java
    public class Timer {
        private long startTime;
        private long endTime;   
   
        /* ...초기화 코드들 */
   
        public void printFinishTime(){
            initEndTime();
            int elapsedSeconds = (int)((endTime - startTime) / 1000);
            System.out.printf("경과 시간: %02d %02d\n", elapsedSeconds / 60, elapsedSeconds % 60);
        }
    }
    ```
   (1) timer은 Application의 멤버 변수로 선언했습니다. 그리고 프로그램 동작 즉시 startTime을 초기화하여 시작 시점을 측정합니다.   
   (2) 프로그램이 종료되면 printFinishTime을 호출해서 endTime을 초기화 및 두 시간의 차로 경과 시간을 측정합니다.   
   (3) 측정된 시간은 miiliseconds이기 때문에 1000을 나눈 후, 경과시간을 분, 초로 나누기 위해 /(나누기 연산자)와 %(나머지 연산자)
   를 처리하였습니다.
   <br><br>
   
4. RandomMixer
    ```java
        public class RandomMixer {
            private static final int COMMANDS_SIZE_EXCEPT_QUIT = 11;
        
            public List<Command> getRandomList() {
                List<Command> commandList = new ArrayList<>();
                Command[] commands = Command.values();
                Random ramdomNumber;
        
                for (int count = 0; count < 10; count++) {
                    ramdomNumber = new Random();
                    commandList.add(commands[ramdomNumber.nextInt(COMMANDS_SIZE_EXCEPT_QUIT)]);
                }
        
                return commandList;
            }
        }
    ```
   기존의 큐브들을 뒤섞기 위해 RandomMixer를 선언했습니다.   
   큐브를 섞는 로직은 아래와 같습니다.  
    
   (1) getRandomList : 기존에 존재하는 Command의 명령들을 임의로 추출하여 List<Command>를 생성한다.   
   (2) cube에 전달해서 List<Command>들의 명령을 수행한다.   
   (3)위 명령을 수행한 큐브는 시작과 동시에 임의의 큐브 배열을 출력합니다.
   ```
                      W B B
                      W B B
                      W Y B
    
    W W R       O O O       B R G       Y Y Y 
    W W R       O O O       B G G       Y Y Y 
    O W O       B B G       Y G Y       W R R 
    
                      R O R
                      G R G
                      G R G
   ```
   
5. WinnerChecker
    ```java
    class WinnerChecker {
       /* 1. 승리조건 확인 로직 */
       public boolean checkWinner(String[][][] cube){}
       
       /* 2. 일치 색상 확인 */
       private String findColor(int faceNumber) {}
       
       /* 3. 해당 면이 같은 색상인지를 확인 */
       private boolean isSameColorFace(int faceNumber, String cubeColor) {}
    }
    ```
    WinnerChecker는 승리 조건을 탐색하기 위해 cube의 모든 요소들을 탐색합니다.  
    로직은 아래와 같습니다.   
    1. Cube class의 cube 상태를 매개인자로 전달받는다.   
    2. 각 면들의 첫번째 위치(cube[faceNumber][0][0])의 색상을 확인한다.  
    3. 확인된 색상이 모든 행열과 동일한 색상인지 탐색한다.   
        1. 만약 모든 색상일 경우 true를 반환   
        2. 다른 색상이 존재할 경우, false를 반환   
    
     만약 모든 큐브들의 면이 일치하게 될 경우 아래와 같이 축하메세지를 출력합니다.
     ```
        CUBE> D'
                         B B B
                         B B B
                         B B B
       
       W W W       O O O       G G G       Y Y Y 
       W W W       O O O       G G G       Y Y Y 
       W W W       O O O       G G G       Y Y Y 
       
                         R R R
                         R R R
                         R R R
   
        🥇큐브를 완성하셨습니다🥇
        경과 시간: 00:05
        경과시간: 1
        이용해주셔서 감사합니다. 뚜뚜뚜.
     ```