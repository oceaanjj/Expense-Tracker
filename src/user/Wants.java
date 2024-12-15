package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wants {
    private String email;
    private double shopping;
    private double stuffs;
    private double gadgets;
    private double travel;
    private double uncategorized;

    public void setShopping(double shopping) {
        this.shopping = shopping;
    }

    public double getShopping() {
        return shopping;
    }

    public void setStuffs(double stuffs) {
        this.stuffs = stuffs;
    }

    public double getStuffs() {
        return stuffs;
    }

    public void setGadgets(double gadgets) {
        this.gadgets = gadgets;
    }

    public double getGadgets() {
        return gadgets;
    }

    public void setTravel(double travel) {
        this.travel = travel;
    }

    public double getTravel() {
        return travel;
    }

    public void setUncategorized(double uncategorized) {
        this.uncategorized = uncategorized;
    }

    public double getUncategorized() {
        return uncategorized;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void wants() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("WANTS");
            System.out.println("[1] SHOPPING");
            System.out.println("[2] STUFFS");
            System.out.println("[3] GADGETS");
            System.out.println("[4] TRAVEL");
            System.out.println("[5] UNCATEGORIZED");
            System.out.println("[6] BACK");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("SHOPPING");
                    System.out.print("Enter the amount you spent on shopping: ");
                    double shopping = s.nextDouble();
                    setShopping(shopping);
                    s.nextLine();
                    System.out.print("Enter details (e.g. clothes, accessories): ");
                    String shoppingDetails = s.nextLine();

                    updateUserIncome(userIncome() - getShopping());
                    updateUserFile("Shopping", shopping, shoppingDetails);
                    break;

                case 2:
                    System.out.println("STUFFS");
                    System.out.print("Enter the amount you spent on stuffs: ");
                    double stuffs = s.nextDouble();
                    setStuffs(stuffs);
                    s.nextLine();
                    System.out.print("Enter details (e.g. furniture, toys): ");
                    String stuffsDetails = s.nextLine();

                    updateUserIncome(userIncome() - getStuffs());
                    updateUserFile("Stuffs", stuffs, stuffsDetails);
                    break;

                case 3:
                    System.out.println("GADGETS");
                    System.out.print("Enter the amount you spent on gadgets: ");
                    double gadgets = s.nextDouble();
                    setGadgets(gadgets);
                    s.nextLine();
                    System.out.print("Enter details (e.g. phone, tablet): ");
                    String gadgetsDetails = s.nextLine();

                    updateUserIncome(userIncome() - getGadgets());
                    updateUserFile("Gadgets", gadgets, gadgetsDetails);
                    break;

                case 4:
                    System.out.println("TRAVEL");
                    System.out.print("Enter the amount you spent on travel: ");
                    double travel = s.nextDouble();
                    setTravel(travel);
                    s.nextLine();
                    System.out.print("Enter details (e.g. vacation, road trip): ");
                    String travelDetails = s.nextLine();

                    updateUserIncome(userIncome() - getTravel());
                    updateUserFile("Travel", travel, travelDetails);
                    break;

                case 5:
                    System.out.println("UNCATEGORIZED");
                    System.out.print("Enter the amount you spent on uncategorized wants: ");
                    double uncategorized = s.nextDouble();
                    setUncategorized(uncategorized);
                    s.nextLine();
                    System.out.print("Enter details: ");
                    String uncategorizedDetails = s.nextLine();

                    updateUserIncome(userIncome() - getUncategorized());
                    updateUserFile("Uncategorized", uncategorized, uncategorizedDetails);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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

    private void updateUserFile(String detailType, double amount, String additionalInfo) {
        String directory = System.getProperty("user.dir") + "/src/database/wants";
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
            System.out.println("Error ! Can't save changes. Please try again.");
            return;
        }

        String newRow = String.format("| %-20s | %-15.2f | %-30s |", detailType, amount, additionalInfo);
        userTxtFile.add(newRow);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : userTxtFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error ! Can't save changes. Please try again.");
        }

        System.out.println(detailType + " has been successfully added to the table.");
    }
}
