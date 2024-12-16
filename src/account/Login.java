package account;

import display.ClearScreen;
import display.LoginDisplay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import user.Needs;

//needs a hello + nickname

public class Login {
    public static final String GREEN_TEXT = "\u001B[32m"; 
    public static final String RESET = "\u001B[0m";
    public static final String ORANGE_TEXT = "\u001B[38;5;214m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    private String email;
    private String password;
    private Scanner s;
    boolean loggedIn = false;
    Needs needs = new Needs();
    ClearScreen clr = new ClearScreen();
    LoginDisplay loginDisplay = new LoginDisplay();

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


    public boolean userLogin() {
        
        while (true) {
            try {
                clr.clearScreen();
                loginDisplay.display();
                
                email = "";
                System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tEnter Email: " + RESET);
                email = s.nextLine().trim();
                setEmail(email);
                
                System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tEnter Password: " + RESET);
                password = s.nextLine().trim();
                setPassword(password);
    
           
                if (validateLogin(getEmail(), getPassword())) {
                    return true; 
                } else {
                    clr.clearScreen();
                    loginDisplay.display();
                    System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t    * Login failed: Incorrect email or password. " + RESET);
                    System.out.print("\t\t\t\t\t\t\t\t       do you want to try again? (yes/no) : ");
                    String tryAgain = s.nextLine();

                        if (tryAgain.equalsIgnoreCase("no")) {
                            return false;
                        }
                        else if (tryAgain.equalsIgnoreCase("yes")) {
                            continue;
                        }
                }
            } catch (Exception e) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* An error occurred during login. Please try again." + RESET);
            }
        }
    }
    
    

    
    private boolean validateLogin(String email, String password) {
        try {
            String directory = System.getProperty("user.dir") + "/src/database/accounts";
            File file = new File(directory, email + ".txt");
    

            if (!file.exists()) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Login failed: Account does not exist." + RESET);
                return false;
            }
    
        
            ArrayList<String> userTxtFile = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userTxtFile.add(line.trim()); 
                }
            }
    
  
            if (userTxtFile.size() < 2) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Account file is corrupted or incomplete." + RESET);
                return false;  
            }
    
     
            String fileEmail = userTxtFile.get(0);
            String filePassword = userTxtFile.get(1);
    
        
    
            if (fileEmail.equals(email) && filePassword.equals(password)) {
                setEmail(email);
                setPassword(password);
                checkDueDates(userTxtFile);
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(GREEN_TEXT + "\t\t\t\t\t\t\t\t\t\tLogin successful!" + RESET);
                System.out.println("\n\t\t\t\t\t\t\t\t\t   press enter to continue....");
                s.nextLine();
                return true;  
            } else {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Login failed: Incorrect email or password." + RESET);
                return false;  
            }
    
        } catch (IOException e) {
            clr.clearScreen();
            loginDisplay.display();
            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Login failed: Error occurred." + RESET);
            return false;  
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
                        System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : " + dueDates[i - 4] + " payment is due today!" + RESET);
                    }
                    else if (daysUntilDue > 0 && daysUntilDue <= 7) {
                        System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : " + dueDates[i - 4] + " payment is due in " + daysUntilDue + " day(s)." + RESET);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(ORANGE_TEXT + "* WARNING: Invalid due date format for " + dueDates[i - 4] + ". Please update your due dates." + RESET);
                }
            }
        }
    }

    public void displayUserName(String email) {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");
        
        if (!file.exists()) {
            System.out.println(ORANGE_TEXT + "* Error: Account file does not exist for email: " + email + RESET);
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = null;
            for (int i = 0; i < 3; i++) {
                name = reader.readLine();
                if (name == null) {
                    System.out.println(ORANGE_TEXT + "* Error: Account file does not contain enough lines." + RESET);
                    return;
                }
            }
            
            // Display the name from line 3
            System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t\t\t\t\tWelcome, " + name + "!" + RESET);
        } catch (IOException e) {
            System.out.println(ORANGE_TEXT + "* Error: Unable to read the account file for email: " + email + RESET);
        }
    }
    
    

    
}
