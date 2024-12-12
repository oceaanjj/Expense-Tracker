package user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import account.Login;

public class Savings {
    Scanner s = new Scanner(System.in);
    Login login = new Login();

    public void addSavings() {
        System.out.println("TRACK YOR SAVINGS");
        System.out.println("[1] START NEW SAVINGS CHALLENGE");
        System.out.println("[2] VIEW EXISTING SAVINGS CHALLENGE");
        System.out.println("[3] EXIT");

        int choice = s.nextInt();
        
        switch (choice) {
            case 1:
                startNewSavingsChallenge();
                break;
            case 2:
                viewExistingSavingsChallenge();
                break;
            case 3:
                return;
            default:
                break;
        }

        
    }

   public void startNewSavingsChallenge() {
    Scanner s = new Scanner(System.in);
    
    System.out.print("Enter Savings Name (Ex. Tuition): ");
    String name = s.nextLine();
    
    double goal = 0;
    while (true) {
        System.out.print("Enter your goal amount: ");
        if (s.hasNextDouble()) {
            goal = s.nextDouble();
            s.nextLine(); 
            if (goal > 0) break; 
            else System.out.println("Goal amount must be greater than zero.");
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            s.nextLine();
        }
    }
    
    String frequency;
    while (true) {
        System.out.print("Enter frequency (daily/weekly/monthly): ");
        frequency = s.nextLine().trim().toLowerCase();
        if (frequency.equals("daily") || frequency.equals("weekly") || frequency.equals("monthly")){
        break;
        }
        else {
            System.out.println("Invalid frequency. Please enter 'daily', 'weekly', or 'monthly'.");
        }
    }

    LocalDate today = LocalDate.now();
    LocalDate endDate = null;
    while (true) {
        System.out.print("Enter Date to finish (YYYY-MM-DD): ");
        String endDateStr = s.nextLine().trim();
        try {
            endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            if (endDate.isAfter(today)) break;
            else System.out.println("End date must be in the future.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
    
    long daysBetween = ChronoUnit.DAYS.between(today, endDate);
    System.out.println("You have " + daysBetween + " days to achieve your savings goal of " + goal + " (" + frequency + ").");

    saveSavings(login.getEmail(), name, goal, frequency, endDate);
    //System.out.println("Savings Challenge Summary:");
    /*System.out.println(name);
    System.out.println(goal);
    System.out.println(frequency);
    System.out.println(endDate);*/
}

    public void viewExistingSavingsChallenge() {
        System.out.println("VIEW EXISTING SAVINGS CHALLENGE");
    }
    




   

    public void saveSavings(String email, String name, double goal, String frequency, LocalDate endDate) {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFile = new File(savingsFolderPath, email + "_savings.txt");

        File savingsFolder = new File(savingsFolderPath);
        if (!savingsFolder.exists() && !savingsFolder.mkdirs()) {
            System.out.println("Failed to create savings directory.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile, true))) {
            writer.write("Savings Name: " + name + "\n");
            writer.write("Goal Amount: " + goal + "\n");
            writer.write("Frequency: " + frequency + "\n");
            writer.write("End Date: " + endDate + "\n");
            //writer.write("=================================\n");
            System.out.println("Savings entry saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save savings entry. Please try again.");
        }
    
    }
}
