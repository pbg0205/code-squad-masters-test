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

### 💻 기능 구현 화면
#### 1. 초기화면
```
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

#### 2.큐브 시계 방향 작동 화면
```
CUBE> ULFRBD

명령어 : U
                  B B B
                  B B B
                  B B B

O O O       G G G       Y Y Y       W W W 
W W W       O O O       G G G       Y Y Y 
W W W       O O O       G G G       Y Y Y 

                  R R R
                  R R R
                  R R R

명령어 : L
                  W B B
                  W B B
                  O B B

O O R       O O G       B Y Y       W W W 
W W R       O O G       B G G       Y Y Y 
W W R       O O G       B G G       Y Y Y 

                  Y R R
                  G R R
                  G R R

명령어 : F
                  W B B
                  W B B
                  G G G

O O R       O O Y       B B B       O W W 
W W R       O O R       G G Y       B Y Y 
W W R       O O R       G G Y       B Y Y 

                  Y Y W
                  G R R
                  G R R

명령어 : R
                  W B B
                  W B Y
                  G G Y

G O R       O O Y       B B W       B B O 
B W R       O O R       G G R       Y Y W 
B W R       O O R       G G R       Y Y W 

                  Y Y W
                  G R W
                  G R O

명령어 : B
                  O W W
                  W B Y
                  G G Y

B B G       B O Y       B B W       B B O 
W W O       B O R       G G R       Y Y W 
R R R       W O R       G G R       Y Y G 

                  O Y W
                  O R W
                  O R O

명령어 : D
                  O W W
                  W B Y
                  G G Y

B B G       B O Y       B B W       B B O 
W W O       B O R       G G R       Y Y W 
W O R       G G R       Y Y G       R R R 

                  O O O
                  R R Y
                  O W W

```

3. 시계 반대 방향 작동 화면
```
CUBE> U'L'F'R'B'D'

명령어 : U'
                  B B B
                  B B B
                  B B B

Y Y Y       W W W       O O O       G G G 
W W W       O O O       G G G       Y Y Y 
W W W       O O O       G G G       Y Y Y 

                  R R R
                  R R R
                  R R R

명령어 : L'
                  O B B
                  G B B
                  G B B

Y Y B       W O O       R O O       G G G 
W W B       W O O       R G G       Y Y Y 
W W B       W O O       R G G       Y Y Y 

                  W R R
                  W R R
                  Y R R

명령어 : F'
                  O B B
                  G B B
                  G Y Y

Y Y B       W O B       O G G       R G G 
W W B       W O B       O G G       R Y Y 
W W B       W O G       R R R       W Y Y 

                  O O O
                  W R R
                  Y R R

명령어 : R'
                  O B W
                  G B W
                  G Y Y

R Y B       W O B       O G B       G Y Y 
R W B       W O B       O G B       G Y Y 
O W B       W O G       R R Y       R R W 

                  O O G
                  W R G
                  Y R R

명령어 : B'
                  W W W
                  G B W
                  G Y Y

B B B       O O B       O G B       G Y O 
Y W W       W O B       O G B       G Y B 
R R O       Y O G       R R Y       R R W 

                  W O G
                  Y R G
                  Y R R

명령어 : D'
                  W W W
                  G B W
                  G Y Y

B B B       O O B       O G B       G Y O 
Y W W       W O B       O G B       G Y B 
R R W       R R O       Y O G       R R Y 

                  G G R
                  O R R
                  W Y Y

```

### 👩‍🏫구현 설명
- 클래스 구성
```
    1. Main             : 프로그램이 작동하는 main method 포함 클래스
    2. Application      : 평면 큐브 프로그램 작동 로직
    3. InputView        : 콘솔로 명령를 입력
    4. Command          : 명령에 대한 상수들의 집합
    5. ValueSeperator   : 입력받은 명령어를 구분해서 List<Command>로 반환하는 기능
    6. Cube             : 명령을 받아 큐브의 위치를 변경
    7. CubeColor        : 큐브들의 각 면의 색들의 상수
    8. OperationCounter : 조작 갯수를 연산 및 확인 기능
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
    ```
    [명령어에 따른 큐브의 이동방향]
    
    U: 윗면 시계 방향 회전
    - cube[4][0][index] → cube[3][0][index] → cube[2][0][index] → cube[1][0][index]
    U': 윗면 반시계 방향 회전(U와 반대)
    - cube[1][0][index] → cube[2][0][index] → cube[3][0][index] → cube[4][0][index]
    
    F: 앞면 시계 방향 회전
    - cube[0][2][index]  → cube[4][index][0] → cube[5][0][2 - index] → cube[2][2 - index][2]
    F': 앞면 반시계 방향 회전(F와 반대)
    - cube[2][2 - index][2] → cube[5][0][2 - index] → cube[4][index][0] → cube[0][2][index]
    
    D : 아랫면 시계 방향 회전
    - cube[4][2][index] → cube[3][2][index] → cube[2][2][index] → cube[1][2][index]
    D' : 아랫면 반시계 방향 회전(D와 반대)
    - cube[1][2][index] → cube[2][2][index] → cube[3][2][index] → cube[4][2][index]
    
    L : 왼쪽 면 시계 방향 회전
    - cube[0][index][0] → cube[3][index][0] → cube[5][index][0] → cube[1][2 - index][2]
    L : 왼쪽 면 반시계 방향 회전
    - cube[1][2 - index][2] → cube[5][index][0] → cube[3][index][0] → cube[0][index][0]
    
    R : 오른 쪽 면 시계 방향 회전
    - cube[0][2 - index][2] → cube[1][index][0] → cube[5][2 - index][2] → cube[3][2 - index][2]
    R': 오른 쪽 면 반시계 방향 회전(R과 반대)
    - cube[3][2 - index][2] → cube[5][2 - index][2] → cube[1][index][0] → cube[0][2 - index][2]
    
    B : 뒤쪽 면 시계 방향 회전
    - cube[0][0][2 - index] → cube[2][index][0] → cube[5][index][0] → cube[4][2][2]
    B': 뒤쪽 면 반시계 방향 회전(B와 반대)
    - cube[4][2][2] → cube[5][index][0] → cube[2][index][0] → cube[0][0][2 - index]
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
       
           public void printCount(){
               System.out.println("조작횟수: " + this.count);
           }
       } 
   ```
    내부 로직은 조작 횟수를 집계하기 위해
    1. 횟수더하기
    2. 횟수 출력
    두가지에 관한 로직만 추가하여 구현했습니다.
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