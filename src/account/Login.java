package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import display.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import user.Needs;

public class Login {
    private String email;
    private String password;
    private Scanner s;
    boolean loggedIn = false;
    Needs needs = new Needs();






    public Login() {
        s = new Scanner(System.in);
    }

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
                    checkDueDates(userTxtFile);
                    System.out.println("Login successful!");
                    return loggedIn = true;
                }
                else {
                    System.out.println("Login failed: Incorrect email or password.");
                    return loggedIn = false;
                }
            }
            else {
                System.out.println("Account file is corrupted or incomplete.");
                return loggedIn = false;
            }
        } catch (IOException e) {
            System.out.println("Login failed: Error occurred.");
            return loggedIn = false;
        }
    }



    private void checkDueDates(ArrayList<String> userTxtFile) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String[] dueDates = { "Electricity", "Water", "Rent", "Internet" };

        for (int i = 4; i < userTxtFile.size() && i < 8; i++) {
            String dueDate = userTxtFile.get(i);
            String status = userTxtFile.get(i + 4);

            if (!dueDate.equalsIgnoreCase("n/a") && !status.equalsIgnoreCase("true")) {
                try {
                    LocalDate parsedDate = LocalDate.parse(dueDate, formatter);
                    MonthDay dueMonthDay = MonthDay.from(parsedDate);
                    MonthDay todayMonthDay = MonthDay.from(today);

                    LocalDate thisYearDueDate = parsedDate.withYear(today.getYear());
                    long daysUntilDue = java.time.temporal.ChronoUnit.DAYS.between(today, thisYearDueDate);

                    if (dueMonthDay.equals(todayMonthDay)) {
                        System.out.println("Reminder: " + dueDates[i - 4] + " payment is due today!");
                    } else if (daysUntilDue > 0 && daysUntilDue <= 7) {
                        System.out.println(
                                "Reminder: " + dueDates[i - 4] + " payment is due in " + daysUntilDue + " day(s).");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Warning: Invalid due date format for " + dueDates[i - 4]);
                }
            }
        }
    }
    

    
}
