package ExpenseTracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class register {
    private LocalDate electricity;
    private LocalDate water;
    private LocalDate rent;
    private LocalDate internet;

    private String nickname;
    private String email;
    private String password;
    private double income;

    private final DateTimeFormatter dateFormatterSlash = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final DateTimeFormatter dateFormatterDash = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setElectricity(String electricity) {
        if (isCorrectdate(electricity)) {
            this.electricity = parseDate(electricity);  
        }
    }

    public String getElectricity() {
        return formatDate(electricity);
    }

    public void setWater(String water) {
        if (isCorrectdate(water)) {
            this.water = parseDate(water);  
        }
    }

    public String getWater() {
        return formatDate(water);
    }

    public void setRent(String rent) {
        if (isCorrectdate(rent)) {
            this.rent = parseDate(rent);  
        }
    }

    public String getRent() {
        return formatDate(rent);
    }

    public void setInternet(String internet) {
        if (isCorrectdate(internet)) {
            this.internet = parseDate(internet); 
        }
    }

    public String getInternet() {
        return formatDate(internet);
    }


    public boolean isCorrectdate(String dateInput) {
        try {
            parseDate(dateInput);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input for date. Please try again. (e.g 2021/01/01 or 2021-01-01)");
            return false;
        }
    }


    private LocalDate parseDate(String dateInput) {
        try {
            return LocalDate.parse(dateInput, dateFormatterSlash);  
        } catch (DateTimeParseException j) {
            try {
                return LocalDate.parse(dateInput, dateFormatterDash);  
            } catch (DateTimeParseException jo) {
                throw new DateTimeParseException("Invalid date format", dateInput, 0);
            }
        }
    }


    private String formatDate(LocalDate date) {
        if (date != null) {
            return date.format(dateFormatterSlash);  
        } else {
            return "N/A";  
        }
    }

    public void write() {
        try {
            //palitan mo yung path na to sa path if hindi nagana sa windows
            String baseDir = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
            
    
            File folder = new File(baseDir);
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                if (!created) {
                    System.out.println("Failed to create folder !");
                    return;
                }
            }
    

            File file = new File(folder, getEmail() + ".txt");
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    System.out.println("Failed to create file: " + file.getName());
                    return;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { 
                writer.write(getEmail() + "\n" + getPassword() + "\n" + getNickname() + 
                             "\n" + getIncome() + "\n" + getElectricity() + "\n" + getWater() + 
                             "\n" + getRent() + "\n" + getInternet() + "\n");
            }
    
            System.out.println("Account created successfully!");
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isEmailExist(String email) {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, email + ".txt"); 

            if(file.exists()){
                return true;
            }
        return false;
    }


}
