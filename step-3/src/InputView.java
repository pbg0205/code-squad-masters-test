import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputValues() {
        System.out.print("CUBE> ");
        return SCANNER.nextLine();
    }
}