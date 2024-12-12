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
                setEmail(email);  
                break; 
            } catch (Exception e) {
                System.out.println("Invalid input for email. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                password = s.nextLine().trim();
                setPassword(password);  
                break;  
            } catch (Exception e) {
                System.out.println("Invalid input for password. Please try again.");
            }
        }

    
       validateLogin(email, password);
       return loggedIn;
    }

    
    private boolean validateLogin(String email, String password) {
        try {
            
            
            String directory = System.getProperty("user.dir") + "/src/database/accounts";
            File file = new File(directory, email + ".txt");

            
            if (!file.exists()) {
                System.out.println("Login failed: Account does not exist.");
                return loggedIn = false;
            }

            
            ArrayList<String> userTxtFile = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userTxtFile.add(line.trim());
                }
            }

          
            if (userTxtFile.size() >= 2) {
                String fileEmail = userTxtFile.get(0);
                String filePassword = userTxtFile.get(1);

                if (fileEmail.equals(email) && filePassword.equals(password)) {
                   
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
