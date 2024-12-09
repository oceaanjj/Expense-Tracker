package ExpenseTracker;


import java.io.*;

public class DeleteAccount {
    private String email;
    private String password;

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

    public void delete() {
        //change ulet path if hindi naganda sa windows
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, getEmail() + ".txt");

        if (file.exists()) {

            if (file.delete()) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Failed to delete account. Please try again.");
            }
        } else {

            System.out.println("Inccorect email. Please try again.");
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


