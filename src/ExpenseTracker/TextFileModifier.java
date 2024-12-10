package ExpenseTracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TextFileModifier {
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

                File newFileName = new File(directory, getNewEmail() + ".txt");
                if (oldFileName.renameTo(newFileName)) {
                    System.out.println("Email changed successfully.");
                }
                else {
                    System.out.println("Failed to change Email. Please try again.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't change Email. Please try again.");
        }
    }

    public void ChangePassword() {
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

    public void ChangeIncome() {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");

        if (file.exists()) {
            try {
                List<String> lines = new ArrayList<>(Files.readAllLines(file.toPath()));                   
                if (lines.size() > 3) {
                    lines.set(3, String.valueOf(getMonthlyIncome())); 
                }

                Files.write(file.toPath(), lines);
                System.out.println("Income changed successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Can't change Income. Please try again.");
        }
    }
}
