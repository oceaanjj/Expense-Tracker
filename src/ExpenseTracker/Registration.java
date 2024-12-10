package ExpenseTracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Registration {

    private Scanner s = new Scanner(System.in); 
    private String nickname;
    private String email;
    private String password;
    private double income;

    private LocalDate electricity;
    private LocalDate water;
    private LocalDate rent;
    private LocalDate internet;

    private final DateTimeFormatter dateFormatterSlash = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final DateTimeFormatter dateFormatterDash = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /*public Registration() {
    }*/

    public void startRegistration() {
        System.out.println("Registration");

        registerEmail();
        registerPassword();
        registerNickname();
        registerIncome();
        registerUtilityDueDates();
        writeToFile();
    }

    private void registerEmail() {
        while (true) {
            try {
                System.out.print("Enter Email: ");
                String email = s.nextLine();

                if (email == null || email.isEmpty()) {
                    System.out.println("Email is required.");
                }
                else if (!email.contains("@") || !email.contains(".")) {
                    throw new Exception();
                }
                else {
                    if (isEmailExist(email)) {
                        System.out.println("Email already exists.");
                    }
                    else {
                        this.email = email;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input for email. Please try again.");
            }
        }
    }

    private void registerPassword() {
        while (true) {
            try {
                System.out.print("Enter Password: ");
                String password = s.nextLine();
                this.password = password;
                break;
            } catch (Exception e) {
                System.out.println("Invalid input for password. Please try again.");
            }
        }
    }

    private void registerNickname() {
        while (true) {
            try {
                System.out.print("Enter Nickname: ");
                String nickname = s.nextLine();
                this.nickname = nickname;
                break;
            } catch (Exception e) {
                System.out.println("Invalid input for nickname. Please try again.");
            }
        }
    }

    private void registerIncome() {
        while (true) {
            try {
                System.out.print("Enter Income: ");
                double income = Double.parseDouble(s.nextLine());
                this.income = income;
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input for income. Please enter a valid number.");
            }
            catch (Exception e) {
                System.out.println("An error occurred while entering income. Please try again.");
            }
        }
    }

    private void registerUtilityDueDates() {
        handleUtilityDate("ELECTRICITY");
        handleUtilityDate("WATER");
        handleUtilityDate("RENT");
        handleUtilityDate("INTERNET");
    }

    private void handleUtilityDate(String utility) {
        while (true) {
            System.out.println(utility);
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String date = s.nextLine();
            if (isCorrectdate(date)) {
                switch (utility) {
                    case "ELECTRICITY":
                        this.electricity = parseDate(date);
                        break;
                    case "WATER":
                        this.water = parseDate(date);
                        break;
                    case "RENT":
                        this.rent = parseDate(date);
                        break;
                    case "INTERNET":
                        this.internet = parseDate(date);
                        break;
                    default:
                        break;
                }
                break;
            }
        }
    }

    private boolean isCorrectdate(String dateInput) {
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

    private boolean isEmailExist(String email) {
        String directory = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";
        File file = new File(directory, email + ".txt");

        return file.exists();
    }

    private void writeToFile() {
        try {
            String baseDir = System.getProperty("user.dir") + "/src/ExpenseTracker/ACCOUNTS";

            File folder = new File(baseDir);
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                if (!created) {
                    System.out.println("Failed to create folder !");
                    return;
                }
            }

            File file = new File(folder, email + ".txt");
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    System.out.println("Failed to create file: " + file.getName());
                    return;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(email + "\n" + password + "\n" + nickname +
                             "\n" + income + "\n" + formatDate(electricity) + "\n" + formatDate(water) +
                             "\n" + formatDate(rent) + "\n" + formatDate(internet) + "\n");
            }

            System.out.println("Account created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatDate(LocalDate date) {
        if (date != null) {
            return date.format(dateFormatterSlash);
        } else {
            return "N/A";
        }
    }
}
