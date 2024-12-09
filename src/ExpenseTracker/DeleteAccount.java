package ExpenseTracker;

import java.io.*;

public class DeleteAccount {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void delete() {
        //change ulet path if hindi naganda sa windows
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");

        if (file.exists()) {

            if (file.delete()) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Failed to delete account. Please try again.");
            }
        } else {

            System.out.println("Inccorect email. Please try again.");
        }
    }

    public boolean isCorrectEmail() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");

        return file.exists();

    }
}

