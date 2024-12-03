package ExpenseTracker;

import java.io.*;
import java.util.ArrayList;

public class Database {
    String nickname, email, password;
    double income;

    public void write(String email, String password) {
        try {
            BufferedWriter txt = new BufferedWriter(new FileWriter(email + ".txt", true));
            BufferedReader read = new BufferedReader(new FileReader(email + ".txt"));

            if (!read.ready()) {
                txt.write(nickname + "\n" + email + "\n" + password);
                txt.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String email, String password, String nickname, double income) {
        try {

            //palitan mo yung path ng folderPath sa path ng folder mo baks
            String folderPath = "/Users/macbookpro15/Downloads/casestudy/expenses-tracker/src/ExpenseTracker/ACCOUNTS";
            File folder = new File(folderPath); 

            if (!folder.exists()) {
                boolean created = folder.mkdir(); 
                if (!created) {
                    System.out.println("Failed to create Folder !");
                    return; 
                }
            }

            File file = new File(folder, email + ".txt"); 
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    
            writer.write(nickname + "\n" + email + "\n" + password + "\n" + income);
            writer.close();
    
            System.out.println("Account created successfully !");
    
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFileToArrayList(String email) {
        ArrayList<String> usertxtfile = new ArrayList<>();
        try {
            String folderPath = "/Users/macbookpro15/Downloads/casestudy/expenses-tracker/src/ExpenseTracker/ACCOUNTS";
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
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return usertxtfile;
    }
}
