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
    private double shopping;
    private double stuffs;
    private double gadgets;
    private double uncategorized;
    private double travel;
    private String email;

    public double getShopping() {
        return shopping;
    }

    public void setShopping(double shopping) {
        this.shopping = shopping;
    }

    public double getStuffs() {
        return stuffs;
    }

    public void setStuffs(double stuffs) {
        this.stuffs = stuffs;
    }

    public double getGadgets() {
        return gadgets;
    }

    public void setGadgets(double gadgets) {
        this.gadgets = gadgets;
    }

    public double getUncategorized() {
        return uncategorized;
    }

    public void setUncategorized(double uncategorized) {
        this.uncategorized = uncategorized;
    }

    public double getTravel() {
        return travel;
    }

    public void setTravel(double travel) {
        this.travel = travel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    /*
     * 
     * 
     * 
     * } }
     */

    public void wants() {
        Scanner s = new Scanner(System.in);

        wantsLoop: while (true) {
            System.out.println("WANTS");
            System.out.println("[1] SHOPPING");
            System.out.println("[2] STUFFS");
            System.out.println("[3] GADGETS");
            System.out.println("[4] TRAVEL");
            System.out.println("[5] UNCATEGORIZED");
            System.out.println("[6] EXIT");
            System.out.println("Enter your choice : ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("SHOPPING");
                    System.out.print("Enter the amount you spent on shopping :");
                    double shopping = s.nextDouble();
                    s.nextLine();
                    setShopping(shopping);
                    System.out.print("Enter name (e.g. clothes, accessories, etc.) :");
                    String name = s.nextLine();

                    updateUserFile("Shopping", shopping, name);
                    updateUserIncome(userIncome() - getShopping());
                    break;
                case 2:
                    System.out.println("STUFFS");
                    System.out.print("Enter the amount you spent on stuffs :");
                    double stuffs = s.nextDouble();
                    setStuffs(stuffs);
                    System.out.print("Enter name (e.g. books, furniture, etc.) :");
                    String stuffName = s.nextLine();
                    updateUserFile("Stuffs", stuffs, stuffName);
                    updateUserIncome(userIncome() - getStuffs());

                    break;
                case 3:
                    System.out.println("GADGETS");
                    System.out.print("Enter the amount you spent on gadgets :");
                    double gadgets = s.nextDouble();
                    setGadgets(gadgets);
                    System.out.print("Enter name (e.g. mobile, laptop, etc.) :");
                    String gadgetName = s.nextLine();
                    updateUserFile("Gadgets", gadgets, gadgetName);
                    updateUserIncome(userIncome() - getGadgets());

                    break;
                case 4:
                    System.out.println("TRAVEL");

                    System.out.print("Enter the amount you spent on travel :");
                    double travel = s.nextDouble();
                    setTravel(travel);
                    System.out.print("Enter name (e.g. flights, hotels, etc.) :");
                    String travelName = s.nextLine();
                    updateUserFile("Travel", travel, travelName);
                    updateUserIncome(userIncome() - getTravel());
                    break;
                case 5:
                    System.out.println("UNCATEGORIZED");

                    System.out.print("Enter the amount you spent on uncategorized :");
                    double uncategorized = s.nextDouble();
                    setUncategorized(uncategorized);
                    System.out.print("Enter name (e.g. gift, donation, etc.) :");
                    String uncategorizedName = s.nextLine();
                    updateUserFile("Uncategorized", uncategorized, uncategorizedName);
                    updateUserIncome(userIncome() - getUncategorized());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break wantsLoop;

                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }

        }

    }

    public double userIncome() {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");

        if (!file.exists()) {
            System.out.println("Login failed: Account does not exist.");
            System.out.println(getEmail());
            return -1;
        }

        ArrayList<String> userTxtFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println("Error reading file.");
            return -1;
        }

        if (userTxtFile.size() < 4) {
            System.out.println("Error: File does not contain enough lines.");
            return -1;
        }

        String incomeLine = userTxtFile.get(3);

        try {

            return Double.parseDouble(incomeLine);
        } catch (NumberFormatException e) {
            System.out.println("Error: The income value is not a valid number.");
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
            System.out.println("Error reading file.");
            return false;
        }

        if (userTxtFile.size() < 4) {
            System.out.println("Error: File does not contain enough lines.");
            return false;
        }

        userTxtFile.set(3, String.valueOf(newIncome));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : userTxtFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            return false;
        }

        System.out.println("Income updated successfully!");
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
            System.out.println("Error reading file.");
            return;
        }

        // Append the new row
        String newRow = String.format("| %-20s | %-15.2f | %-30s |", detailType, amount, additionalInfo);
        userTxtFile.add(newRow);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : userTxtFile) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            System.out.println("" + getEmail());
        }

        System.out.println(detailType + " has been successfully added to the table.");
    }

}

// *MABABAWASAN DAPAT YUNG NASA INCOME ( ACCOUNT TEXT FILE line 4) */
// KUNG WALA NA YUNG INCOME, DAPAT DI NA MAKAPAG ADD NG WANTS, also sa needs(
// ako na sa needs and savings wag niyo na galawin)
