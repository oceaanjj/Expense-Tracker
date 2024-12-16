package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import display.Date;

        /*
            This class is used to start the expense tracker
            dito lahat nilalagay lahat ng classes na ginawa natin under user folder
        */
public class userMain {

    private String email;
    Date day = new Date();
    LocalDate todayDate = LocalDate.now();
    double newIncome;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void startExpenseTracker(Savings savings, Needs needs, Wants wants) {
        Scanner s = new Scanner(System.in);
        boolean normal = false;
        boolean trial = true;

        // check if its first day of the month
        if (day.isFirstDayOfMonth(todayDate)) {
            firstDayofMonth();
            normal = true;

        } else {
            normal = true;
        }
        if (normal) {
            System.out.println("START TRACKING YOUR EXPENSES");
            System.out.println("[1] NEEDS");
            System.out.println("[2] WANTS");
            System.out.println("[3] SAVINGS");
            System.out.println("[4] EXIT");
            System.out.println("Enter your choice : ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    // Needs needs = new Needs();
                    needs.needs();
                    break;
                case 2:
                    // Wants wants = new Wants();
                    wants.wants();
                    break;
                case 3:
                    // Savings savings = new Savings(); kailangan iget yung instance sa main para
                    // hindi mag null yung email
                    savings.addSavings();
                    break;
                case 4:
                    System.out.println("EXIT");
                    break;
            }
        }

    }

    public void firstDayofMonth() {
        Scanner s = new Scanner(System.in);
        System.out.println("Important: Today is the first day of the month, Please enter your income this month.");
        System.out.println("Your remaining income last month is " + userIncome());
        System.out.println("================================================");
        System.out.print("Enter a new month income: ");
        newIncome = s.nextDouble();
        double temp = userIncome() + newIncome;
        updateUserIncome(userIncome() + newIncome);
        System.out.println("================================================");
        System.out.println("Your balance/income for this month is " + temp);

        System.out.println("================================================");
        System.out.println("Current Status of your bills");
        System.out.println("Electricity: NOT PAID");
        System.out.println("Water: NOT PAID");
        System.out.println("Internet: NOT PAID");
        System.out.println("Rent: NOT PAID");
        System.out.println("================================================");

        // revert the bills to False
        updateBillStatus("Electricity", false);
        updateBillStatus("Water", false);
        updateBillStatus("Internet", false);
        updateBillStatus("Rent", false);

    }

    public double userIncome() {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");

        if (!file.exists()) {
            System.out.println("Login failed: Account does not exist.");
            return -1;
        }

        ArrayList<String> userTxtFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return -1;
        }

        if (userTxtFile.size() < 4) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return -1;
        }

        String incomeLine = userTxtFile.get(3);

        try {
            return Double.parseDouble(incomeLine);
        } catch (NumberFormatException e) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return -1;
        }
    }

    public boolean updateUserIncome(double newIncome) {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");

        if (!file.exists()) {
            System.out.println("Account does not exist.");
            System.out.println(getEmail());
            return false;
        }

        ArrayList<String> userTxtFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return false;
        }

        if (userTxtFile.size() < 4) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return false;
        }

        userTxtFile.set(3, String.valueOf(newIncome));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : userTxtFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error ! Can't save changes. Please try again.");
            return false;
        }

        return true;
    }

    private void updateBillStatus(String billType, boolean status) {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");

        if (!file.exists()) {
            System.out.println("Account does not exist.");
            return;
        }

        ArrayList<String> userTxtFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println("Error reading file.");
            return;
        }

        int statusIndex = -1;
        switch (billType) {
            case "Electricity":
                statusIndex = 8;
                break;
            case "Water":
                statusIndex = 9;
            case "Internet":
                statusIndex = 10;
            case "Rent":
                statusIndex = 11;
                break;
        }

        if (statusIndex >= 0 && statusIndex < userTxtFile.size()) {
            userTxtFile.set(statusIndex, String.valueOf(status));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : userTxtFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}