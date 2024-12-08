package ExpenseTracker;

import java.io.File;
import java.util.ArrayList;

public class login {
    private String email;
    private String password;
    private TemporaryDatabase db = new TemporaryDatabase();


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean userLogin(String email, String password) {
        try {
            //palitan ng path for windows if hindi gumagana
            String folderPath = System.getProperty("user.dir") + "/ExpenseTracker/ACCOUNTS";
            File file = new File(folderPath, getEmail() + ".txt");
            if (!file.exists()) {
                System.out.println("Login failed: Email not registered.");
                return false;
            }

            ArrayList<String> userTxtFile = db.readFileToArrayList(getEmail());

            if (userTxtFile.size() >= 3) {
                String userEmail = userTxtFile.get(0); 
                String userPassword = userTxtFile.get(1); 
                if (userEmail.equals(getEmail()) && userPassword.equals(getPassword())) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Login failed: Incorrect email or password.");
                    return false;
                }
            } else {
                System.out.println("Account does't exist.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login failed: Error occurred.");
            return false;
            
        }
    }

}