package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Needs {
    /**
     * need to stop the due date reminder if the user already paid the bill in utilities
     */
    private String email;
    private double food;
    private double transportation;
    private double bills;

    public void setFood(double food) {
        this.food = food;
    }

    public double getFood() {
        return food;
    }

    public void setTransportation(double transportation) {
        this.transportation = transportation;
    }

    public double getTransportation() {
        return transportation;
    }

    public void setBills(double bills) {
        this.bills = bills;
    }

    public double getBills() {
        return bills;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void needs(){
    
    while(true){
        Scanner s = new Scanner(System.in);
        System.out.println("NEEDS");
        System.out.println("[1] FOOD");
        System.out.println("[2] TRANSPORTATION");
        System.out.println("[3] BILLS");
        System.out.println("[4] EXIT");
        System.out.println("Enter your choice : ");
        int choice = s.nextInt();

                switch(choice){
                    case 1:
                        System.out.println("FOOD");
                        System.out.print("Enter the amount you spent on food : ");
                        double food = s.nextDouble();
                        setFood(food);
                        System.out.print("Enter name (e.g. fries, burger etc.) : ");
                        s.nextLine();
                        String name = s.nextLine();

                        updateUserIncome(userIncome() - getFood());
                        break;
                    case 2:
                        System.out.println("TRANSPORTATION");
                        System.out.print("Enter the amount you spent on transportation : ");
                        double transportation = s.nextDouble();
                        setTransportation(transportation);
                        s.nextLine();
                        System.out.print("Enter name (e.g. bus, taxi etc.) : ");
                        String name1 = s.nextLine();

                        updateUserIncome(userIncome() - getTransportation());
                        break;
                    case 3:
                        System.out.println("BILLS");
                        System.out.println("[1] ELECTRICITY");
                        System.out.println("[2] WATER");
                        System.out.println("[3] INTERNET");
                        System.out.println("[4] RENT");
                        System.out.println("[5] EXIT");
                        System.out.println("Enter your choice : ");
                        int choice1 = s.nextInt();

                                        switch(choice1){
                                            case 1:
                                                System.out.println("ELECTRICITY");
                                                System.out.print("Enter the amount you spent on electricity : ");
                                                double bills = s.nextDouble();
                                                setBills(bills);
                                                updateUserIncome(userIncome() - getBills());
                                                s.nextLine();
                                                break;
                                            case 2:
                                                System.out.println("WATER");
                                                System.out.print("Enter the amount you spent on water : ");
                                                bills = s.nextDouble();
                                                setBills(bills);
                                                updateUserIncome(userIncome() - getBills());
                                                break;
                                            case 3:
                                                System.out.println("INTERNET");
                                                System.out.print("Enter the amount you spent on internet : ");
                                                bills = s.nextDouble();
                                                setBills(bills);
                                                updateUserIncome(userIncome() - getBills());
                                                break;
                                            case 4:
                                                System.out.println("RENT");
                                                System.out.print("Enter the amount you spent on rent : ");
                                                bills = s.nextDouble();
                                                setBills(bills);
                                                updateUserIncome(userIncome() - getBills());

                                                break;
                                            case 5:
                                                System.out.println("EXIT");
                                                break;
                                        }
                        
                        
                    

                       
                        break;
                    case 4:
                        System.out.println("EXIT");
                        return;
                }
    }//end of loop
    
}


    public double userIncome() {
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt");

        if (!file.exists()) {
            System.out.println("Login failed: Account does not exist.");
            return - 1; 
        }
    
        ArrayList<String> userTxtFile = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());  
            }
        } catch (IOException ex) {
            System.out.println("Error reading file.");
            return - 1; 
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

            

    
}

