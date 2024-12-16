package account;

import display.ClearScreen;
import display.TermsAndConditions;
import display.menu;
import display.registrationDisplay;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Registration {
    public static final String GREEN_TEXT = "\u001B[32m"; 
    public static final String RESET = "\u001B[0m";
    public static final String ORANGE_TEXT = "\u001B[38;5;214m";
    private final Scanner s = new Scanner(System.in);
    private String email;
    private String password;
    private String nickname;
    private double income;
    menu registration = new registrationDisplay();
    ClearScreen clr = new ClearScreen();

    private LocalDate electricityDueDate;
    private LocalDate waterDueDate;
    private LocalDate rentDueDate;
    private LocalDate internetDueDate;

    private final DateTimeFormatter slashFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final DateTimeFormatter dashFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void startRegistration() {
        //System.out.println("Registration \n");
        registration.display();

        email = getEmail();
        password = getPassword();
        nickname = getNickname();
        income = getIncome();
        registerUtilityDueDates();
        saveAccountDetails();
        
    }

    private String getEmail() {
        while (true) {
            System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter your email : " + RESET);
            String input = s.nextLine();

            if (input.isEmpty()) {
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t * Email is required." + RESET);
            }
            else if (!input.contains("@") || !input.contains(".")) {
                clr.clearScreen();
                registration.display();
                //System.out.println("\n\n");
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : Email should contain '@' and '.'" + RESET);
            }
            else if (isEmailExists(input)) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t * This email is already registered." + RESET);
            }
            else {
                return input;
            }
        }
    }

    private String getPassword() {
        clr.clearScreen();
        registration.display();
        System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter your password : " + RESET);
        String registerpassword = s.nextLine();
        return registerpassword;
    }


    private String getNickname() {
        clr.clearScreen();
        registration.display();
        System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter your nickname : " + RESET);
        String registernickname = s.nextLine();
        return registernickname;
    }

    private double getIncome() {
        while (true) {
            TermsAndConditions termsAndConditions = new TermsAndConditions();
            clr.clearScreen();
            termsAndConditions.display();
            clr.clearScreen();
            registration.display();
        
            while(true){
                System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter your monthly income : " + RESET);
                try {
                    return Double.parseDouble(s.nextLine());
            
                } catch (NumberFormatException e) {
                    clr.clearScreen();
                    registration.display();
                    System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t * Invalid input. Please use / enter a numeric value." + RESET);
                    continue;
                }
            }
        }
    }

    private void registerUtilityDueDates() {
        electricityDueDate = getUtilityDueDate("Electricity");
        waterDueDate = getUtilityDueDate("Water");
        rentDueDate = getUtilityDueDate("Rent");
        internetDueDate = getUtilityDueDate("Internet");
    }

    private LocalDate getUtilityDueDate(String utilityName) {
        while (true) {
            clr.clearScreen();
            registration.display();
            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* FORMAT : (YYYY-MM-DD) or type 'skip' " + RESET);
            System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter due date for " + utilityName + " : " + RESET);
            String input = s.nextLine();

            if (input.equalsIgnoreCase("skip")) {
                return null; 
            }

            try {
                return parseDate(input);
            } catch (DateTimeParseException e) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Invalid date format. Please try again." + RESET);
            }
        }
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        try {
            return LocalDate.parse(date, slashFormatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, dashFormatter);
        }
    }

    private boolean isEmailExists(String email) {
        File accountFile = new File(getAccountFilePath(email));
        return accountFile.exists();
    }

    private String getAccountFilePath(String email) {
        String baseDir = System.getProperty("user.dir") + "/src/database/accounts";
        return baseDir + "/" + email + ".txt";
    }

    private void saveAccountDetails() {
        try {
            File accountsFolder = new File(System.getProperty("user.dir") + "/src/database/accounts");
            File savingsFolder = new File(System.getProperty("user.dir") + "/src/database/savings");
            File needsFolder = new File(System.getProperty("user.dir") + "/src/database/needs");
            File wantsFolder = new File(System.getProperty("user.dir") + "/src/database/wants");
    
            if (!accountsFolder.exists() && !accountsFolder.mkdirs()) {
                System.out.println("Failed to create accounts directory.");
                return;
            }
    
            if (!savingsFolder.exists() && !savingsFolder.mkdirs()) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Error ! Please Try Again." + RESET);
                return;
            }

            if (!wantsFolder.exists() && !wantsFolder.mkdirs()) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Error ! Please Try Again."  + RESET);
                return;
            }

            if (!needsFolder.exists() && !needsFolder.mkdirs()) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Error ! Please Try Again." + RESET);
                return;
            }
    
            File accountFile = new File(getAccountFilePath(email));
            if (accountFile.exists()) {
                clr.clearScreen();
                registration.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Account file already exists. Can't overwrite." + RESET);
                return;
            }
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountFile))) {
                writer.write(email + "\n");
                writer.write(password + "\n");
                writer.write(nickname + "\n");
                writer.write(income + "\n");
    
                if (electricityDueDate != null) {
                    writer.write(electricityDueDate + "\n");
                } else {
                    writer.write("N/A\n");
                }
    
                if (waterDueDate != null) {
                    writer.write(waterDueDate + "\n");
                } else {
                    writer.write("N/A\n");
                }
    
                if (rentDueDate != null) {
                    writer.write(rentDueDate + "\n");
                } else {
                    writer.write("N/A\n");
                }
    
                if (internetDueDate != null) {
                    writer.write(internetDueDate + "\n");
                } else {
                    writer.write("N/A\n");
                }

                writer.write("false\n"); 
                writer.write("false\n"); 
                writer.write("false\n"); 
                writer.write("false\n"); 
            }
    
            File savingsFile = new File(savingsFolder, email + ".txt");
            /*if (!savingsFile.exists()) {
            savingsFile.createNewFile()
                if (savingsFile.createNewFile()) {
                    //System.out.println("Savings file successfully created!");
                } else {
                    //System.out.println("Failed to create savings file.");
                }
            }*/

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile, true))){


               
                writer.write("+----------------------+-----------------+------------------+-----------------+-----------------+-------------------+---------------+\n");
                writer.write("| Savings Name         | Goal Amount     | Saved So Far     | Frequency       | End Date        | Remaining Balance | Status        |\n");
                writer.write("+----------------------+-----------------+------------------+-----------------+-----------------+-------------------+---------------+\n");
               
    
                }
                catch (IOException e) {
            }

            File needsFile = new File(needsFolder, email + ".txt");
            /*if (!needsFile.exists()) {
                if (needsFile.createNewFile()) {
                    //System.out.println("Savings file successfully created!");
                } else {
                    //System.out.println("Failed to create savings file.");
                }
            }*/

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(needsFile, true))){

            //writer.write("Needs\n");
                writer.write("+-------------------------------------------------------------------------+\n");
                writer.write("| Detail               | Amount          | Information                    |\n");
                writer.write("+-------------------------------------------------------------------------+\n");

                


                } catch (IOException e) {
                    System.out.println("\t\t\t\t\t\t\t\tFailed to create needs file.");
            }


            File wantsFile = new File(wantsFolder, email + ".txt");
            /*if (!wantsFile.exists()) {
                if (wantsFile.createNewFile()) {
                    //System.out.println("Savings file successfully created!");
                } else {
                    //System.out.println("Failed to create savings file.");
                }
            }*/

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(wantsFile, true))){

                //writer.write("Needs\n");
                    writer.write("+-------------------------------------------------------------------------+\n");
                    writer.write("| Detail               | Amount          | Information                    |\n");
                    writer.write("+-------------------------------------------------------------------------+\n");    
    
                } catch (IOException e) {
                    System.out.println("Failed to create needs file.");
            }

            clr.clearScreen();
            registration.display();
            System.out.println(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tAccount successfully created!" + RESET);
            System.out.println("\n");
            System.out.println("\t\t\t\t\t\t\t\t   press enter to go back to main menu...");
            s.nextLine();
    
        } catch (IOException e) {
            System.out.println("Failed to create account or savings file. Please try again.");
        }
    }
    
}

