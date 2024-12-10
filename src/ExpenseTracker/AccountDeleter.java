package ExpenseTracker;

import java.io.File;
import java.util.Scanner;

public class AccountDeleter extends TextFileModifier {
    private final Scanner s = new Scanner(System.in);
    private final Verification verifier = new Verification();
    private final Confirmation confirm = new Confirmation();

    public void deleteAccount() {
        if (verifier.verifyEmail(this) || verifier.verifyPassword(this)) {

            if (confirm.confirmAction("Are you sure you want to delete your account? (y/n): ")) {
                deleteFile();
            } else {
                System.out.println("Account deletion cancelled.");
            }
        }
        else {
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
        }
        else {
            System.out.println("Account file not found. Deletion failed.");
        }
    }

    
}
