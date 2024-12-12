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

public class Savings {
    /**
     * need read the file and display the savings
     */
    private String email;
    private Scanner s = new Scanner(System.in);
    private String name;
    private double goal;
    private String frequency;
    //private String endDateStr;
    //private LocalDate endDate;


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

    /*public void setEndDate(String endDateStr) {
            this.endDateStr = endDateStr;
    }

    public String getEndDate() {
        return endDateStr;
    }*/


    public void addSavings() {
        System.out.println("TRACK YOUR SAVINGS");
        System.out.println("[1] START NEW SAVINGS ");
        System.out.println("[2] VIEW EXISTING SAVINGS ");
        System.out.println("[3] EXIT");
        System.out.print("Enter your choice: ");
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                startNewSavings();
                break;
            case 2:
                viewExistingSavings();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void startNewSavings() {
        s.nextLine();
        System.out.print("Enter Savings Name (Ex. Tuition): ");
        String savingsName = s.nextLine().trim();
        setName(savingsName);

        //double goal;
        while (true) {
            System.out.print("Enter your goal amount: ");
            if (s.hasNextDouble()) {
               Double goalAmount = s.nextDouble();
                setGoal(goalAmount);
                if (getGoal() > 0){
                    break;
                }
                else {
                    System.out.println("Goal amount must be greater than zero.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                s.next(); 
            }
        }

        //String frequency;
        while (true) {
            s.nextLine();
            System.out.print("Enter frequency (daily/weekly/monthly): ");
            String savingsfrequency = s.nextLine().toLowerCase().trim();
            setFrequency(savingsfrequency);
            if (getFrequency().equals("daily") || getFrequency().equals("weekly") || getFrequency().equals("monthly"))
            {
                break;
            }
            else {
                System.out.println("Invalid frequency. Please enter 'daily', 'weekly', or 'monthly'.");
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate endDate;
        while (true) {
            System.out.print("Enter Date to finish (YYYY-MM-DD): ");
            String endDateStr = s.nextLine().trim();
            try {
                endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                if (endDate.isAfter(today)){ 
                    break;
                }
                else {
                    System.out.println("End date must be in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        long totalPeriods;
        switch (getFrequency()) {
            case "daily":
                totalPeriods = ChronoUnit.DAYS.between(today, endDate);
                break;
            case "weekly":
                totalPeriods = ChronoUnit.WEEKS.between(today, endDate);
                break;
            case "monthly":
                totalPeriods = ChronoUnit.MONTHS.between(today, endDate);
                break;
            default:
                totalPeriods = 0; 
                break;
        }

        //double amountPerPeriod = getGoal() / totalPeriods;


        //notes sa user
        /*System.out.println("You have " + totalPeriods + " " + getFrequency() + " period(s) to save " + getGoal());
        System.out.println("You need to save " + amountPerPeriod + " per " + getFrequency());*/

        saveSavings(endDate);
    }


    public void saveSavings(LocalDate endDate) {
        final String savingsFolderPath = System.getProperty("user.dir") + "/src/database/savings";
        File savingsFolder = new File(savingsFolderPath);
        File savingsFile = new File(savingsFolder, email + ".txt");

        if (!savingsFolder.exists() && !savingsFolder.mkdirs()) {
            System.out.println("Failed to create savings directory.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savingsFile, true))) {
            writer.write("Savings Name: " + getName() + "\n");
            writer.write("Goal Amount: " + getGoal() + "\n");
            writer.write("Frequency: " + getFrequency()+ "\n");
            writer.write("End Date: " + endDate + "\n");
            writer.write("---------------\n");
            System.out.println("Savings entry saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save savings entry. Please try again.");
        }
    }



    public void viewExistingSavings() {
        System.out.println("VIEW EXISTING SAVINGS CHALLENGE");
        
    }
}
