package ExpenseTracker;

import java.util.Scanner;

public class Confirmation {
    private final Scanner scanner = new Scanner(System.in);

    public boolean confirmAction(String message) {
        while (true) {
            System.out.print(message);
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                return true;
            } else if (confirmation.equals("n") || confirmation.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }
}
