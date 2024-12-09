package ExpenseTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class UpdateAccount {
    private String email;
    private String password;
    private double monthlyIncome;
    private String newEmail;
    private String newPassword;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }   

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void ChangeEmail() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File oldFileName = new File(directory, getEmail() + ".txt");  
        
        if (oldFileName.exists()) {
            try {
  
                List<String> txtfiledata = new ArrayList<>(Files.readAllLines(oldFileName.toPath()));
                if (!txtfiledata.isEmpty()) {
                    txtfiledata.set(0, getNewEmail()); 
                }
                Files.write(oldFileName.toPath(), txtfiledata);
    
                System.out.println("Email content updated successfully.");
    
                File newFileName = new File(directory, getNewEmail() + ".txt");
                if (oldFileName.renameTo(newFileName)) {
                    System.out.println("File renamed successfully.");
                } else {
                    System.out.println("Failed to rename the file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't change Email. Please try again.");
        }
    }
    


        public void ChangePassword(String newPassword) {
            String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
            File file = new File(directory, getEmail() + ".txt");
            
            if (file.exists()) {
                try {                
                    List<String> lines = new ArrayList<>(Files.readAllLines(file.toPath()));                   
                    if (lines.size() > 1) {
                        lines.set(1, getNewPassword()); 
                    }

                
                    Files.write(file.toPath(), lines);
                    System.out.println("Password changed successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Can't change Password. Please try again.");
            }
        }

        public boolean isCorrectEmail() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");

        return file.exists();
        }

        public boolean isCorrectPassword() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");
    
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line1 = reader.readLine(); 
            String line2 = reader.readLine();  
            reader.close();
    
            if (line1 != null && line2 != null) {
                return line2.equals(getPassword());  
            } else {
                System.out.println("File does not contain sufficient data.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
        }


    
}
