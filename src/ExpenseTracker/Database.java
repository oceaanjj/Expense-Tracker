package ExpenseTracker;
import java.io.*;

public class Database {
    String nickname, email, password;
    double income;

    public void write(String email, String password) {
        try {
            BufferedWriter txt = new BufferedWriter(new FileWriter(email + ".txt" , true));
            BufferedReader read = new BufferedReader(new FileReader(email + ".txt"));

            if(!read.ready()) {
                txt.write(nickname + "\n" + email + "\n" + password);
                txt.close();
            }   
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void write(String email, String password, String nickname, double income) {
        try {
            //palitan mo yungpath ng folder mo baks
            String folderPath = "/Users/macbookpro15/Downloads/Expense Tracker/src/Accounts";
            File folder = new File(folderPath); 
    
            /*if (!folder.exists()) {
                boolean created = folder.mkdir(); 
                if (!created) {
                    System.out.println("Failed to create Folder !");
                    return; 
                }
            }*/

            File file = new File(folder, email + ".txt"); 
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    
            writer.write(nickname + "\n" + email + "\n" + password);
            writer.close();
    
            System.out.println("Account created successfully !");
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
