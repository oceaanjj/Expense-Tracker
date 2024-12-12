package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private String email;
    private String password;
    private Scanner s;
    boolean loggedIn = false;

    public Login() {
        s = new Scanner(System.in);
    }

    // Setters and Getters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Method to handle user login with input prompts
    public boolean userLogin() {
        while (true) {
            try {
                System.out.print("Enter Email: ");
                email = s.nextLine().trim();
                setEmail(email);  // Store email
                break;  // Break loop once valid email is entered
            } catch (Exception e) {
                System.out.println("Invalid input for email. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                password = s.nextLine().trim();
                setPassword(password);  // Store password
                break;  // Break loop once valid password is entered
            } catch (Exception e) {
                System.out.println("Invalid input for password. Please try again.");
            }
        }

        // Step 2: Validate login credentials
       validateLogin(email, password);
       return loggedIn;
    }

    // Method to validate the user's credentials from the database (text file)
    private boolean validateLogin(String email, String password) {
        try {
            
            // Set the directory path for user accounts
            String directory = System.getProperty("user.dir") + "/src/database/accounts";
            File file = new File(directory, email + ".txt");

            // Check if the account file exists
            if (!file.exists()) {
                System.out.println("Login failed: Account does not exist.");
                return loggedIn = false;
            }

            // Read the account file
            ArrayList<String> userTxtFile = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userTxtFile.add(line.trim());
                }
            }

            // Verify account details
            if (userTxtFile.size() >= 2) {
                String fileEmail = userTxtFile.get(0);
                String filePassword = userTxtFile.get(1);

                if (fileEmail.equals(email) && filePassword.equals(password)) {
                    // Set email and password after successful login
                    setEmail(email);
                    setPassword(password);
                    System.out.println("Login successful!");
                    return loggedIn = true;
                } else {
                    System.out.println("Login failed: Incorrect email or password.");
                    return loggedIn = false;
                }
            } else {
                System.out.println("Account file is corrupted or incomplete.");
                return loggedIn = false;
            }
        } catch (IOException e) {
            System.out.println("Login failed: Error occurred.");
            return loggedIn = false;
        }
    }
}
