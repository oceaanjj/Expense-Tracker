package user;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    Scanner s = new Scanner(System.in);
    userMenuDisplay menu = new userMenuDisplay();
    Login login = new Login();
    Needs needs = new Needs(login);

    public void startExpenseTracker() {
        System.out.println("Welcome to the Expense Tracker!");

        while (true) {
            menu.display();
            System.out.println("Enter choice: ");

            try {
                int choice = s.nextInt();
                s.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        needs.addNeeds(this);
                        break;
                    case 2:
                        addSavings();
                        break;
                    case 3:
                        addWants();
                        break;
                    case 4:
                        System.out.println("Exiting the Expense Tracker. Goodbye!");
                        return; // Exit the application
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine(); // Clear invalid input
            }
        }
    }

    public void addSavings() {
        System.out.println("This feature is not implemented yet.");
    }

    public void addWants() {
        System.out.println("This feature is not implemented yet.");
    }
}
