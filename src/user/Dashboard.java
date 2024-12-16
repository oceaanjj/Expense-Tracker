package user;

import java.io.*;

public class Dashboard{
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void displayDashboard () {
        String baseDir = System.getProperty("user.dir") + "/src/database";

        String savingsPath = baseDir + "/savings/" + getEmail() + ".txt";
        String wantsPath = baseDir + "/wants/" + getEmail() + ".txt";
        String needsPath = baseDir + "/needs/" + getEmail() + ".txt";

        displayFileContents(new File(savingsPath), "SAVINGS");
        displayFileContents(new File(wantsPath), "WANTS");
        displayFileContents(new File(needsPath), "NEEDS");
    }


    private void displayFileContents(File file, String type) {
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                System.out.println(type + ":");
                int lineCount = 0;
                String line;
                boolean hasData = false;

                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    System.out.println(line);

                    if (lineCount > 3 && !line.trim().isEmpty()) {
                        hasData = true;
                    }
                }
                if (lineCount <= 3 || !hasData) {
                    System.out.println("No data found.");
                }

                System.out.println(); 
            } catch (IOException e) {
                System.err.println("Error reading file " + file.getAbsolutePath() + ": " + e.getMessage());
            }
        } else {
            System.out.println(type + " file not found: " + file.getAbsolutePath());
        }
    }
}