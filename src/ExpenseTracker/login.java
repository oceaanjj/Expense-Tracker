package ExpenseTracker;

import java.io.File;
import java.util.ArrayList;

public class login extends Database {


    public boolean userLogin(String email, String password) {
        try {

            //palitan mo ulit path ng folder
            String folderPath = "C:\\Users\\csdmi\\Documents\\GitHub\\Expense-Tracker\\src\\ExpenseTracker\\ACCOUNTS";
            File file = new File(folderPath, email + ".txt");
            if (!file.exists()) {
                System.out.println("Login failed: Email not registered.");
                return false;
            }

            ArrayList<String> userTxtFile = readFileToArrayList(email);

            if (userTxtFile.size() >= 3) {
                String userEmail = userTxtFile.get(0); 
                String userPassword = userTxtFile.get(1); 
                        if (userEmail.equals(email) && userPassword.equals(password)) {
                            System.out.println("Login successful!");
                            return true;
                        }
                         else {
                             System.out.println("Login failed: Incorrect email or password.");
                             return false;
                        }
            } 
            else {
                System.out.println("Login failed: Invalid file format.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login failed: Error occurred.");
            return false;
            
        }
    }

    public void Email(String email) {
            this.email = email;
    }

    public void Password(String password) {
        this.password = password;
    }
}