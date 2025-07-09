import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();
        scanner.close();
        if (number > 2 && number % 2 == 0) {
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
}