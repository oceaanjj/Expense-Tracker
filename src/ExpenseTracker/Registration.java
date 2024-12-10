package ExpenseTracker;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Registration {

    private final Scanner s = new Scanner(System.in);
    private String email;
    private String password;
    private String nickname;
    private double income;

    private LocalDate electricityDueDate;
    private LocalDate waterDueDate;
    private LocalDate rentDueDate;
    private LocalDate internetDueDate;

    private final DateTimeFormatter slashFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final DateTimeFormatter dashFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void startRegistration() {
        System.out.println("Registration \n");

        email = getEmail();
        password = getPassword();
        nickname = getNickname();
        income = getIncome();
        registerUtilityDueDates();
        saveAccountDetails();
    }

    private String getEmail() {
        while (true) {
            System.out.print("Enter your email: ");
            String input = s.nextLine();

            if (input.isEmpty()) {
                System.out.println("Email is required.");
            }
            else if (!input.contains("@") || !input.contains(".")) {
                System.out.println(" * REMINDER : Email should contain '@' and '.'");
            }
            else if (isEmailExists(input)) {
                System.out.println("This email is already registered.");
            }
            else {
                return input;
            }
        }
    }

    private String getPassword() {
        System.out.print("Enter your password: ");
        String registerpassword = s.nextLine();
        return registerpassword;
    }


    private String getNickname() {
        System.out.print("Enter your nickname: ");
        String registernickname = s.nextLine();
        return registernickname;
    }

    private double getIncome() {
        while (true) {
            System.out.print("Enter your monthly income: ");
            try {
                return Double.parseDouble(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please use / enter a numeric value.");
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
            System.out.print("Enter due date for " + utilityName + " (YYYY-MM-DD) or type 'skip': ");
            String input = s.nextLine();

            if (input.equalsIgnoreCase("skip")) {
                return null; 
            }

            try {
                return parseDate(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
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
        String baseDir = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        return baseDir + "/" + email + ".txt";
    }

    private void saveAccountDetails() {
        try {
            File folder = new File(System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS");
            if (!folder.exists() && !folder.mkdirs()) {
                System.out.println("Failed to create accounts directory.");
                return;
            }

            File accountFile = new File(getAccountFilePath(email));
            if (accountFile.exists()) {
                System.out.println("Account file already exists. Cannot overwrite.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountFile))) {
                writer.write(email + "\n");
                writer.write(password + "\n");
                writer.write(nickname + "\n");
                writer.write(income + "\n");

                if (electricityDueDate != null) {
                    writer.write(electricityDueDate + "\n");
                }
                else {
                    writer.write("N/A\n");
                }

                if (waterDueDate != null) {
                    writer.write(waterDueDate + "\n");
                }
                else {
                    writer.write("N/A\n");
                }

                if (rentDueDate != null) {
                    writer.write(rentDueDate + "\n");
                }
                else {
                    writer.write("N/A\n");
                }


                if (internetDueDate != null) {
                    writer.write(internetDueDate + "\n");
                }
                else {
                    writer.write("N/A\n");
                }

            
            }

            System.out.println("Account successfully created!");

        } catch (IOException e) {
            System.out.println("Failed Create Account. Please try again.");
        }
    }
}
