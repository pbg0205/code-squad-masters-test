# codesquad-matsers-test

## 1단계: 단어 밀어내기 구현하기

### 🚀 기능 요구사항
- [x] 1.입력: 사용자로부터 단어 하나, 정수 숫자 하나, L 또는 R을 입력받는다.
- [x] 2.주어진 단어를 L이면 숫자 갯수만큼 왼쪽으로, R이면 오른쪽으로 밀어낸다.
- [x] 3.밀려나간 단어는 반대쪽으로 채워진다.

### ✍ 입출력 요구사항
- #### ⌨ 입력
- [x] 1.정수 하나의 범위는 -100 <= N < 100으로 한다.
- [x] 2.L과 R은 대소문자 모두 입력이 가능하다.

- #### 🖥 출력
- [x] 1.연산된 결과를 표시한다.

### 💻 기능 구현 화면
```
> apple 3 L 
leapp
```
```
> banana 6 R
banana
```

```
> carrot -1 r
arrotc
```
### 👩‍🏫구현 설명
- 클래스 구성
```
    1. Main             : 프로그램이 작동하는 클래스이다.
    2. Application      : 단어 밀어내기 프로그램 작동 로직을 담당한다.
    3. InputView        : 콘솔로 명령를 입력받는다.
    4. InputValidator   : 입력한 명령어의 유효성을 확인한다.
    5. WordPusher       : 입력한 단어를 밀어내는 로직을 처리한다.
```
- 구현 로직
1. Application.java
    ```java
    class Application extends Thread {
        @Override
        public void run() {
            String[] commands;
            boolean isValid;
    
            do {
                commands = InputView.inputCommands();
                isValid = new InputValidator(commands).validate();
            } while (!isValid);
    
            new WordPusher(commands).pushWord();
        }
    }
    ```
     Application 클래스는 단어 밀러내기 프로그램 작동 로직을 담당하는 클래스입니다.   
     해당 클래스는 크게 3가지 로직을 처리합니다.
     1. 콘솔을 통해 명령어를 입력받는다.
     2. 입력한 명령어의 유효성 검사를 한다.
        1. 명령의 유효성이 입증되었다면 아래 로직을 처리한다.
        2. 유효성이 입증되지 않을 경우, 다시 콘솔로 명령어를 입력받는다.
     3. 명령어의 유효성이 확인되면 단어를 밀어낸다.
     <br><br>
 2. InputView.java
     ```java
        public class InputView {
            private static final Scanner SCANNER = new Scanner(System.in);
        
            public static String[] inputCommands() {
                System.out.print("> " );
                return SCANNER.nextLine().split(" ");
            }
        }
    ```
    - InputView 클래스는 콘솔로 명령어를 입력받기 위한 클래스 입니다.
        1. Scanner를 통해 입력 방식을 선택했습니다.
        2. static 변수의 경우, 프로그램이 종료되기 전까지 반복해서 호출해야 미뤄보아 
        프로그램 내 공통으로 사용하기 위한 static 변수를 선언하는 것이 적합하다고 판단했습니다.
         <br><br>
 3. InputValidator.java
    ```java
        public class InputValidator {
            private static int VALUES_SIZE = 3;
            private String[] values;
        
            public InputValidator(String[] values) {
                this.values = values;
            }
        
            public boolean validate() {
                try {
                    return validateAll();
                } catch (Exception e) {
                    return false;
                }
            }
        
            private boolean validateAll() {
                return (isOfLength3() && checkNumberRange() && isDirection());
            }
            ...
    }
    ```
    - InputValidator는 입력한 명령에 대한 유효성 검사는 하는 클래스입니다.   
    해당 클래스는 3가지 유효성 검사를 진행합니다.
        1. 명령어 요소 갯수 확인(명령어 갯수 = 3)
        2. 숫자 범위를 확인(-100 <= 숫자 < 100)
        3. 방향값 유효성 검사   
    
        위 3가지 유효성을 확인한 후, 유효성 여부를 Application.java에 전달하여 유효한 명령에 한해서 다음 로직을 
        진행할 수 있습니다.
         <br><br>
4. WordPusher.java
    - WordPusher는 입력한 단어를 밀어내는 로직을 담당하는 클래스입니다.   
    해당 로직은 아래와 같이 진행합니다.
    ```
        1. 단어와 숫자, 방향 변수를 초기화한다.
        2. 숫자의 상태를 확인하여 로직처리 방식을 결정한다.
            - 양수일 경우, 기존 방향과 동일하게 로직을 처리한다.
            - 음수일 경우,  방향을 변경하고 숫자를 양수로 변경한다.
              (음수일 경우는 방향이 반대(L→R)일 경우의 로직과 동일하다.)
            - 숫자 길이 >= 글자 길이일 경우, 숫자를 글자길이로 나눠 나머지 값만 남도록 처리한다.
              (숫자가 크더라도 나머지 값 기준으로 처리하는 방식과 동일하기 때문입니다.)
        3. 결정된 방향과 인덱스를 통해 글자의 위치를 변경한다.
    ```
   1. 단어와 숫자, 방향 변수를 초기화 한다.
        ```java
           public class WordPusher {
               private String word;
               private int number;
               private String direction;
           
               public WordPusher(String[] commands) {
                   initValues(commands);
               }
           
               private void initValues(String[] commands) {
                   this.word = commands[0];
                   this.number = Integer.parseInt(commands[1]);
                   this.direction = commands[2];
               }
           ...
           }
        ```
       입력받은 명령어는 InputView.java에서 String 배열 형태로 받습니다.   
       String 배열를 인덱스 기준으로 단어, 숫자, 방향 변수를 초기화 하였습니다.
   
   2. 숫자의 상태를 확인하여 로직처리 방식을 결정한다.
        ```java
         public class WordPusher {
              public void pushWord() {
                  if(isMinusNumber()){
                      number = -number;
                      changeDirection();
                  }
                  resizeNumberLen();
                  swapByDirection();
                  System.out.println(word);
              }
      
              private void changeDirection() {
                  if(direction.equalsIgnoreCase("L")){
                      direction = "R";
                  }
                  if(direction.equalsIgnoreCase("R")){
                      direction = "L";
                  }
              }
          }
      ```
      앞서 말씀드린 것과 같이 음수일 경우,   
      방향을 변경하는 로직과 일치하기 때문에 chanageDirection() method로 방향을 변경하였습니다.   
       
        ```java
             public class WordPusher {
                  private void resizeNumberLen() {
                      if(isGreaterThanWordLen()){
                          number = number % word.length();
                      }
                  }
                  private boolean isGreaterThanWordLen() {
                      return word.length() < number;
                  }
              }
        ```
      또한 글자 밀어내는 로직은 인덱스를 기준으로 글자를 밀어냅니다.   
      숫자를 글자 길이로 나눈 나머지를 인덱스로 참조하기 위해
      resizeNumberLen()을 사용해 나머지 값으로 숫자를 반환하는 로직을 구현하였습니다.
      
    3. 결정된 방향과 인덱스를 통해 글자의 위치를 변경한다.
    ```java
   public class WordPusher {
       private void swapByDirection() {
            if(direction.equalsIgnoreCase("L")){
                swapWord(number);
            }
            if(direction.equalsIgnoreCase("R")){
                swapWord(word.length() - number);
            }
        }
    
        private void swapWord(int index) {
            String leftWord = word.substring(0, index);
            String rightWord = word.substring(index);
            word = rightWord + leftWord;
        }
   }
    ```
   마지막으로 방향에 따라서 인덱스를 설정해 글자 위치를 변경합니다.   
   왼쪽 방향일 경우, 왼쪽을 기준으로 해서 인덱스를 참조해야 하므로 나머지를 그대로 사용합니다.   
   하지만 오른 쪽 방향일 경우, 전체 길이에서 나머지 값을 빼준 값이 오른쪽 기준 인덱스이기 때문에   
   word.length() - number로 처리하여 오른쪽 인덱스 값을 참조하도록 하였습니다.