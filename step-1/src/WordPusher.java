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

    public void pushWord() {
        if(isMinusNumber()){
            number = -number;
            changeDirection();
        }
        resizeNumberLen();
        swapByDirection();
        System.out.println(word);
    }

    private boolean isMinusNumber() {
        return (number < 0);
    }

    private void changeDirection() {
        if(direction.equalsIgnoreCase("L")){
            direction = "R";
        }
        if(direction.equalsIgnoreCase("R")){
            direction = "L";
        }
    }

    private void resizeNumberLen() {
        if(isGreaterThanWordLen()){
            number = number % word.length();
        }
    }

    private boolean isGreaterThanWordLen() {
        return word.length() < number;
    }

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