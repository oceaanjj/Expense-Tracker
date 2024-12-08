package ExpenseTracker;

import java.io.*;
import java.util.ArrayList;

public class TemporaryDatabase {
    
    //nag sstore ng data from txt file to arraylist(kada isang line isng index)
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

    
}