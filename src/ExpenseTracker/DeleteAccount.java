package ExpenseTracker;

import java.io.File;

public class DeleteAccount {

    public void delete(String email) {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, email + ".txt"); 

        if (file.delete()) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Failed to delete account.");
        }
    }
    
}
