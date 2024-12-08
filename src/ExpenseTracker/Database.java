package ExpenseTracker;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private String nickname;
    private String email;
    private String password;
    private double income;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    /**public void write() {
        try {
            BufferedWriter txt = new BufferedWriter(new FileWriter(getEmail() + ".txt", true));
            BufferedReader read = new BufferedReader(new FileReader(getEmail() + ".txt"));

            if (!read.ready()) {
                txt.write(getEmail() + "\n" + getPassword() +  "\n" + getNickname());
                txt.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }**/
    

    public void write() {
        try {

            //palitan ng path for windows if hindi gumagana
            String folderPath = System.getProperty("user.dir") + "/ExpenseTracker/ACCOUNTS";
            File folder = new File(folderPath); 

            if (!folder.exists()) {
                boolean created = folder.mkdir(); 
                if (!created) {
                    System.out.println("Failed to create Folder !");
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folderPath, getEmail() + ".txt"), true));
            writer.write(getEmail() + "\n" + getPassword() +  "\n" + getNickname());
            writer.close();
    
            System.out.println("Account created successfully !");
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFileToArrayList(String email) {
        ArrayList<String> usertxtfile = new ArrayList<>();
        try {

            //palitan ng path for windows if hindi gumagana
            String folderPath = System.getProperty("user.dir") + "/ExpenseTracker/ACCOUNTS";
            File file = new File(folderPath, email + ".txt");
            
            if (!file.exists()) {
                System.out.println("Login failed: Email not registered.");
                return usertxtfile;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                usertxtfile.add(line); 
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usertxtfile;
    }

    

    public void Budgeting() {
    }
}