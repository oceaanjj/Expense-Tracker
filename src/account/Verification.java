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
    private final Scanner scanner = new Scanner(System.in);

    public boolean verifyEmail(AccountEditor account) {
        while (true) {
            System.out.print("Enter your registered email: ");
            String inputEmail = scanner.nextLine();

            account.setEmail(inputEmail);

            if (isCorrectEmail(account)) {
                return true; 
            } else {
                System.out.println("Email does not match the registered email. Please try again.");
            }
        }
    }

    public boolean verifyPassword(AccountEditor account) {
        while (true) {
            System.out.print("Enter your account password: ");
            String inputPassword = scanner.nextLine();

            account.setPassword(inputPassword);

            if (isCorrectPassword(account)) {
                return true; 
            } else {
                System.out.println("Incorrect password. Please try again.");
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
                System.out.println("File does not contain sufficient data.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
