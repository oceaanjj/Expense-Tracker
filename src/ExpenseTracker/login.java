package ExpenseTracker;

import java.io.File;
import java.util.ArrayList;

public class login extends Database {


    public void userLogin(String email, String password) {
        try {

            //palitan mo ulit path ng folder
            String folderPath = "/Users/macbookpro15/Downloads/casestudy/expenses-tracker/src/ExpenseTracker/ACCOUNTS";
            File file = new File(folderPath, email + ".txt");
            if (!file.exists()) {
                System.out.println("Login failed: Email not registered.");
                return;
            }

            ArrayList<String> userTxtFile = readFileToArrayList(email);

            if (userTxtFile.size() >= 3) {
                String userEmail = userTxtFile.get(0); 
                String userPassword = userTxtFile.get(1); 
                        if (userEmail.equals(email) && userPassword.equals(password)) {
                            System.out.println("Login successful!");
                        }
                         else {
                            System.out.println("Login failed: Incorrect email or password.");
                        }
            } 
            else {
                System.out.println("Login failed: Invalid file format.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login failed: Error occurred.");
        }
    }

    public void Email(String email) {
            this.email = email;
    }

    public void Password(String password) {
        this.password = password;
    }
}