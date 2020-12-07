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

    private boolean isOfLength3() {
        return values.length == VALUES_SIZE;
    }

    private boolean checkNumberRange() {
        int number = parseNumber(values[1]);
        return (-100 <= number) && (number < 100);
    }

    private int parseNumber(String number_str) {
        try {
            return Integer.parseInt(number_str);
        } catch (NumberFormatException nf) {
            throw new NumberFormatException();
        }
    }

    private boolean isDirection() {
        String direction = values[2];
        return isLeft(direction) || isRight(direction);
    }

    private boolean isLeft(String direction) {
        return direction.equalsIgnoreCase("L");
    }

    private boolean isRight(String direction) {
        return direction.equalsIgnoreCase("R");
    }
}
