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

    public void pushWWord() {
        if(!isPositiveNumberOrZero()){
            number = -number;
        }
        process();
        System.out.println(word);
    }

    private boolean isPositiveNumberOrZero() {
        return (number >= 0);
    }

    private void process() {
        if(isGreaterThanWordLen()){
            number = number % word.length();
        }
        swapWord();
    }

    private boolean isGreaterThanWordLen() {
        return word.length() < number;
    }

    private void swapWord() {
        String leftWord = word.substring(0, number);
        String rightWord = word.substring(number);
        word = rightWord + leftWord;
    }
}