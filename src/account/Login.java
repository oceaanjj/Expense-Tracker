package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Login {
    private String email;
    private String password;
  
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
        

        //change niyo path into windows if hindi gumagana login (Account doesn't exist ket meron) 
        String directory = System.getProperty("user.dir") + "/src/database/accounts";
        File file = new File(directory, getEmail() + ".txt"); 

       
        if (!file.exists()) {
            System.out.println("Login failed : Account does not exist.");
            return false;
        }

        ArrayList<String> userTxtFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            userTxtFile = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                userTxtFile.add(line.trim());
            }
        }

  
        if (userTxtFile.size() >= 2) {
            String fileEmail = userTxtFile.get(0);
            String filePassword = userTxtFile.get(1); 
          

            if (fileEmail.equals(getEmail()) && filePassword.equals(getPassword())) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed: Incorrect email or password.");
                return false;
            }
        } else {
            System.out.println("Account file is corrupted or incomplete.");
            return false;
        }
    } catch (IOException e) {
        System.out.println("Login failed : Error occurred.");
        return false;
    }
}

    
    
}