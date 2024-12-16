package user;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Savings {

    /*
     * KULANG : 
     * INPUTS TRY CATCH
     * CHECK THE SCANNERS AND PRINT
     */
    private String email;
    private Scanner s = new Scanner(System.in);
    private String name;
    private double goal;
    private String frequency;
    LocalDate today = LocalDate.now();
    LocalDate endDate;
    //double savedSoFar;
    //String parts[];

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getGoal() {
        return goal;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }


    public void addSavings() {
        while (true) {
            System.out.println("TRACK YOUR SAVINGS");
            System.out.println("[1] START NEW SAVINGS");
            final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
            File savingsFile = new File(savingsFolderPath, email + ".txt");
        
            if (savingsFile.exists() && savingsFile.length() > 0) {
                System.out.println("[2] ADD TO EXISTING SAVINGS");
                System.out.println("[3] DELETE A SAVINGS");
            }
            System.out.println("[4] BACK");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
        
            switch (choice) {
                case 1:
                    startNewSavings();
                    break;
                case 2:
                    if (savingsFile.exists() && savingsFile.length() > 0) {
                        addToExistingSavings();
                    } else {
                        System.out.println("No existing savings found. Please start a new savings entry.");
                    }
                    break;
                case 3:
                    if (savingsFile.exists() && savingsFile.length() > 0) {
                        deleteSavings();
                    } else {
                        System.out.println("No savings entries found to delete.");
                    }
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    

    public void startNewSavings() {
        s.nextLine(); 
        System.out.print("Enter Savings Name (e.g. Tuition): ");
        String savingsName = s.nextLine().trim();
        setName(savingsName);

        while (true) {
            System.out.print("Enter your goal amount: ");
            if (s.hasNextDouble()) {
                double goalAmount = s.nextDouble();
                setGoal(goalAmount);
                if (getGoal() > 0) {
                    break;
                } else {
                    System.out.println("Goal amount must be greater than zero.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                s.next(); 
            }
        }

        while (true) {
            s.nextLine(); 
            System.out.print("Enter frequency (daily/weekly/monthly): ");
            String savingsFrequency = s.nextLine().toLowerCase().trim();
            setFrequency(savingsFrequency);
            if (getFrequency().equals("daily") || getFrequency().equals("weekly") || getFrequency().equals("monthly")) {
                break;
            } else {
                System.out.println("Invalid frequency. Please enter 'daily', 'weekly', or 'monthly'.");
            }
        }

        while (true) {
            System.out.print("Enter Date to finish (YYYY-MM-DD): ");
            String endDateStr = s.nextLine().trim();
            try {
                endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                if (endDate.isAfter(today)) {
                    break;
                } else {
                    System.out.println("End date must be in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        saveSavings(0.0); 
    }



    public void saveSavings(double savedSoFar) {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFolder = new File(savingsFolderPath);
        File savingsFile = new File(savingsFolder, email + ".txt");
    
        if (!savingsFolder.exists() && !savingsFolder.mkdirs()) {
            System.out.println("Failed to create savings directory.");
            return;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile, true))) {
            // Calculate remaining balance
            double remaining = getGoal() - savedSoFar;
            String status = remaining <= 0 ? "Goal Achieved" : "On Going";
    
            // Save the entry with proper formatting, including the remaining balance
            writer.write(String.format("| %-20s | %-15.2f | %-16.2f | %-15s | %-15s | %-17.2f | %-12s |\n",
                    getName(), getGoal(), savedSoFar, getFrequency(), endDate, Math.max(0, remaining), status));
            System.out.println("Savings entry saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save savings entry. Please try again.");
        }
    }
    
    


    public void addToExistingSavings() {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFile = new File(savingsFolderPath, email + ".txt");
    
        if (!savingsFile.exists()) {
            System.out.println("No savings file found for this account.");
            return;
        }
    
        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(savingsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read savings file. Please try again.");
            return;
        }
    
        List<String> savingsNames = new ArrayList<>();
        List<String[]> savingsData = new ArrayList<>();
        for (String line : fileLines) {
            if (line.startsWith("+") || line.startsWith("| Savings Name")) {
                continue; // Skip header lines
            }
            if (line.startsWith("|")) {
                String[] parts = line.split("\\|");
                String savingsName = parts[1].trim();
                String status = parts[6].trim();
    
                if (!"Goal Achieved".equalsIgnoreCase(status)) {
                    savingsNames.add(savingsName);
                    savingsData.add(parts);
                }
            }
        }
    
        if (savingsNames.isEmpty()) {
            System.out.println("No ongoing savings goals to update. Returning to the menu.");
            return;
        }
    
        System.out.println("Choose a savings to add to:");
        for (int i = 0; i < savingsNames.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + savingsNames.get(i));
        }
    
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        if (choice < 1 || choice > savingsNames.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    
        String selectedSavings = savingsNames.get(choice - 1);
        String[] selectedSavingsData = savingsData.get(choice - 1);
    
        double goal = Double.parseDouble(selectedSavingsData[2].trim());
        double savedSoFar = Double.parseDouble(selectedSavingsData[3].trim());
        double remaining = goal - savedSoFar;
    
        // Check if goal is already achieved
        if (remaining <= 0) {
            System.out.println("You already completed this savings goal. Returning to the menu.");
            return;
        }
    
        System.out.print("Enter the amount to add to " + selectedSavings + ": ");
        double amountToAdd = s.nextDouble();
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile))) {
            for (String line : fileLines) {
                if (line.contains(selectedSavings)) {
                    savedSoFar += amountToAdd;
                    remaining = goal - savedSoFar;
    
                    // Determine status
                    String newStatus = remaining <= 0 ? "Goal Achieved" : "On Going";
    
                    // Update the table row
                    line = String.format("| %-20s | %-15.2f | %-16.2f | %-15s | %-15s | %-17.2f | %-12s |",
                            selectedSavings, goal, savedSoFar, selectedSavingsData[4].trim(), selectedSavingsData[5].trim(), Math.max(0, remaining), newStatus);
    
                    // Print suggestion in console
                    String endDate = selectedSavingsData[5].trim();
                    String frequency = selectedSavingsData[4].trim();
                    System.out.println("Suggestion: " + generateSuggestion(remaining, savedSoFar, frequency, endDate));
                }
                writer.write(line + "\n");
            }
            System.out.println("Savings updated successfully!");
        } catch (IOException e) {
            System.out.println("Failed to update savings file. Please try again.");
        }
    }
    
    
    
    


private String generateSuggestion(double remaining, double savedSoFar, String frequency, String endDate) {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate targetDate = LocalDate.parse(endDate, formatter);
    long daysRemaining = ChronoUnit.DAYS.between(today, targetDate);

    if (remaining <= 0) {
        return "Congratulations! You've achieved your savings goal!";
    }

    if (daysRemaining <= 0) {
        return "The end date has passed. Please review your savings plan.";
    }

    double dailyTarget = remaining / Math.max(1, daysRemaining); 
    double weeklyTarget = dailyTarget * 7; 

 
    if (frequency.equalsIgnoreCase("daily")) {
        return String.format(
                "You need to save approximately %.2f daily to reach your goal by %s. Keep pushing forward!",
                dailyTarget, targetDate);
    } else if (frequency.equalsIgnoreCase("weekly")) {
        return String.format(
                "You need to save approximately %.2f weekly to reach your goal by %s. Stay consistent!",
                weeklyTarget, targetDate);
    } else if (frequency.equalsIgnoreCase("monthly")) {
        long monthsRemaining = ChronoUnit.MONTHS.between(today, targetDate);
        double monthlyTarget = remaining / Math.max(1, monthsRemaining); 
        return String.format(
                "You need to save approximately %.2f monthly to achieve your goal by %s. Keep going!",
                monthlyTarget, targetDate);
    }

    return "You're on track! Keep saving to achieve your goal by " + targetDate + ".";
}


    public void viewExistingSavings() {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFile = new File(savingsFolderPath, email + ".txt");

        if (!savingsFile.exists()) {
            System.out.println("No savings file found for this account.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(savingsFile))) {
            String line;
            System.out.println("Your Savings:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read savings file.");
        }
    }

    public void deleteSavings() {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFile = new File(savingsFolderPath, email + ".txt");
    
        if (!savingsFile.exists() || savingsFile.length() == 0) {
            System.out.println("No savings file found or the file is empty. Nothing to delete.");
            return;
        }
    
        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(savingsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read savings file. Please try again.");
            return;
        }
    
        List<String> savingsNames = new ArrayList<>();
        for (String line : fileLines) {
            if (line.startsWith("+") || line.startsWith("| Savings Name")) {
                continue; // Skip header lines
            }
            if (line.startsWith("|")) {
                String[] parts = line.split("\\|");
                String savingsName = parts[1].trim();
                savingsNames.add(savingsName);
            }
        }
    
        if (savingsNames.isEmpty()) {
            System.out.println("No savings entries found to delete.");
            return;
        }
    
        System.out.println("Choose a savings to delete:");
        for (int i = 0; i < savingsNames.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + savingsNames.get(i));
        }
    
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();
        if (choice < 1 || choice > savingsNames.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    
        String selectedSavings = savingsNames.get(choice - 1);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile))) {
            for (String line : fileLines) {
                if (!line.contains(selectedSavings)) {
                    writer.write(line + "\n");
                }
            }
            System.out.println("Savings \"" + selectedSavings + "\" deleted successfully!");
        } catch (IOException e) {
            System.out.println("Failed to update savings file after deletion. Please try again.");
        }
    }
    
}
