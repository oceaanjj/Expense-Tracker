package user;

import account.Login;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Needs {
    Scanner s = new Scanner(System.in);
    Login login;

    public Needs(Login login) {
        this.login = login;
    }

    public void addNeeds() {
        if (login.getEmail() == null || login.getEmail().isEmpty()) {
            System.out.println("Error: You must log in before adding needs.");
            return;
        }

        int amount;
        String date = LocalDate.now().toString();
        String description;
        String category;

        while (true) {
            System.out.println("TRACK YOUR NEEDS");
            System.out.println("[1] Foods ");
            System.out.println("[2] Transportation ");
            System.out.println("[3] Utility Bill");
            System.out.println("[4] Rent");
            System.out.println("[5] Internet");
            System.out.println("[6] Exit");
            System.out.println("Enter choice: ");
            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    category = "Foods";
                    System.out.println("Enter Amount: ");
                    amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Description: ");
                    description = s.nextLine();

                    saveToFile(login.getEmail(), category, amount, date, description);
                    break;
                case 2:
                    category = "Transportation";
                    System.out.println("Enter Amount: ");
                    amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Description: ");
                    description = s.nextLine();

                    saveToFile(login.getEmail(), category, amount, date, description);
                    break;
                case 3:
                    category = "Utility Bill";
                    System.out.println("Enter Amount: ");
                    amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Description: ");
                    description = s.nextLine();

                    saveToFile(login.getEmail(), category, amount, date, description);
                    break;
                case 4:
                    category = "Rent";
                    System.out.println("Enter Amount: ");
                    amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Description: ");
                    description = s.nextLine();

                    saveToFile(login.getEmail(), category, amount, date, description);
                    break;
                case 5:
                    category = "Internet";
                    System.out.println("Enter Amount: ");
                    amount = s.nextInt();
                    s.nextLine();

                    System.out.println("Enter Description: ");
                    description = s.nextLine();

                    saveToFile(login.getEmail(), category, amount, date, description);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void saveToFile(String email, String category, int amount, String date, String description) {
        if (email == null || email.isEmpty()) {
            System.out.println("Error: Email is not set. Please log in first.");
            return;
        }

        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, email + ".txt");

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Error: File for email '" + email + "' does not exist.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("\n"); // Add a newline for separation
            writer.write(date + " "); // Separate values with spaces or a delimiter
            writer.write(category + " ");
            writer.write(amount + " ");
            writer.write(description);
            System.out.println("Expense saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save expense. Error: " + e.getMessage());
        }
    }
}
