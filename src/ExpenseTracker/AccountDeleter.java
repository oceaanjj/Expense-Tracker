package ExpenseTracker;

import java.io.File;
import java.util.Scanner;

public class AccountDeleter extends TextFileModifier {
    private final Scanner s = new Scanner(System.in);
    private final Verification verifier = new Verification();

    public void deleteAccount() {
        if (verifier.verifyEmail(this) && verifier.verifyPassword(this)) {

            if (confirmAction("Are you sure you want to delete your account? (y/n): ")) {
                deleteFile();
            } else {
                System.out.println("Account deletion cancelled.");
            }
        } else {
            System.out.println("Verification failed. Account deletion aborted.");
        }
    }

    private void deleteFile() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File fileToDelete = new File(directory, getEmail() + ".txt");

        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("Account file deleted successfully.");
            } else {
                System.out.println("Failed to delete account file. Please try again.");
            }
        } else {
            System.out.println("Account file not found. Deletion failed.");
        }
    }

    private boolean confirmAction(String message) {
        while (true) {
            System.out.print(message);
            String confirmation = s.nextLine().trim().toLowerCase();
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
