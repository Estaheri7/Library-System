import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TestParser testParser = new TestParser();
        while (true) {
            String[] command = scanner.nextLine().split("[#|]");
            if (command[0].equals("finish")) {
                break;
            }
            testParser.parse(command);
        }
    }
}
