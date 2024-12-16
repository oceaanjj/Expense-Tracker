package account;

import display.LoginDisplay;
import display.clearScreen;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import user.Needs;

//needs a hello + nickname
public class Login {

    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String ORANGE_TEXT = "\u001B[38;5;214m";
    private String email;
    private String password;
    private Scanner s;
    boolean loggedIn = false;
    Needs needs = new Needs();
    clearScreen clr = new clearScreen();
    LoginDisplay loginDisplay = new LoginDisplay();

    public Login() {
        s = new Scanner(System.in);
    }

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

    public boolean userLogin() {
        while (true) {
            try {
                clr.clearScreen();
                loginDisplay.display();
                email = "";
                System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter Email: " + RESET);
                email = s.nextLine().trim();
                setEmail(email);
                break;
            } catch (Exception e) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Invalid input for email. Please try again." + RESET);
            }
        }

        while (true) {
            try {
                clr.clearScreen();
                loginDisplay.display();
                System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\tEnter Password: " + RESET);
                password = s.nextLine().trim();
                setPassword(password);
                break;
            } catch (Exception e) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Invalid input for password. Please try again." + RESET);
            }
        }

        validateLogin(email, password);
        return loggedIn;
    }

    private boolean validateLogin(String email, String password) {
        try {

            String directory = System.getProperty("user.dir") + "/src/database/accounts";
            File file = new File(directory, email + ".txt");

            if (!file.exists()) {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Login failed : Account does not exist." + RESET);
                return loggedIn = false;
            }

            ArrayList<String> userTxtFile = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userTxtFile.add(line.trim());
                }
            }

            if (userTxtFile.size() >= 2) {
                String fileEmail = userTxtFile.get(0);
                String filePassword = userTxtFile.get(1);

                if (fileEmail.equals(email) && filePassword.equals(password)) {

                    setEmail(email);
                    setPassword(password);
                    checkDueDates(userTxtFile);
                    clr.clearScreen();
                    loginDisplay.display();
                    System.out.println(GREEN_TEXT + "\t\t\t\t\t\t\t\t\t\tLogin successful!" + RESET);
                    //needs a hello + nickname
                    return loggedIn = true;
                } else {
                    clr.clearScreen();
                    loginDisplay.display();
                    System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\tLogin failed: Incorrect email or password." + RESET);
                    return loggedIn = false;
                }
            } else {
                clr.clearScreen();
                loginDisplay.display();
                System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\tAccount file is corrupted or incomplete." + RESET);
                return loggedIn = false;
            }
        } catch (IOException e) {
            clr.clearScreen();
            loginDisplay.display();
            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\tLogin failed: Error occurred." + RESET);
            return loggedIn = false;
        }
    }

    private void checkDueDates(ArrayList<String> userTxtFile) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String[] dueDates = {"Electricity", "Water", "Rent", "Internet"};

        for (int i = 4; i < userTxtFile.size() && i < 8; i++) {
            String dueDate = userTxtFile.get(i);
            String status = userTxtFile.get(i + 4);

            if (!dueDate.equalsIgnoreCase("n/a") && !status.equalsIgnoreCase("true")) {
                try {
                    LocalDate parsedDate = LocalDate.parse(dueDate, formatter);
                    MonthDay dueMonthDay = MonthDay.from(parsedDate);
                    MonthDay todayMonthDay = MonthDay.from(today);

                    LocalDate thisYearDueDate = parsedDate.withYear(today.getYear());
                    long daysUntilDue = java.time.temporal.ChronoUnit.DAYS.between(today, thisYearDueDate);

                    if (dueMonthDay.equals(todayMonthDay)) {
                        System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : " + dueDates[i - 4] + " payment is due today!" + RESET);
                    } else if (daysUntilDue > 0 && daysUntilDue <= 7) {
                        System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : " + dueDates[i - 4] + " payment is due in " + daysUntilDue + " day(s)." + RESET);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(ORANGE_TEXT + "* WARNING: Invalid due date format for " + dueDates[i - 4] + ". Please update your due dates." + RESET);
                }
            }
        }
    }

}
