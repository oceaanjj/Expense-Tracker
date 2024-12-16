package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

    /*
        This class is used to verify the email and password of the user
        (if alam nila yung password and email na ginaagamit nila for security purposes)
        mostly used in our account updater (MyAccount)
    */

public class Verification {  

    public static final String GREEN_TEXT = "\u001B[32m"; 
    public static final String RESET = "\u001B[0m";
    public static final String ORANGE_TEXT = "\u001B[38;5;214m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    private final Scanner scanner = new Scanner(System.in);

    public boolean verifyEmail(AccountEditor account) {
        while (true) {
            System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tEnter your registered email : " + RESET);
            String inputEmail = scanner.nextLine();

            account.setEmail(inputEmail);

            if (isCorrectEmail(account)) {
                return true; 
            } else {
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\t* Email does not match the registered email. Please try again." + RESET);
            }
        }
    }

    public boolean verifyPassword(AccountEditor account) {
        while (true) {
            System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tEnter your account password : " + RESET);
            String inputPassword = scanner.nextLine();

            account.setPassword(inputPassword);

            if (isCorrectPassword(account)) {
                return true; 
            } else {
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\t* Incorrect password. Please try again." + RESET);
            }
        }
    }

    private boolean isCorrectEmail(AccountEditor account) {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, account.getEmail() + ".txt");

        return file.exists();
    }

    private boolean isCorrectPassword(AccountEditor account) {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, account.getEmail() + ".txt");
    
        try {
            String line1;
            String line2;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                line1 = reader.readLine();
                line2 = reader.readLine();
            }
    
            if (line1 != null && line2 != null) {
                return line2.equals(account.getPassword()); 
            } else {
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\t* File does not contain sufficient data." + RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
