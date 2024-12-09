package ExpenseTracker;

import java.util.Scanner;

public class Registration {

    private Scanner s;
    private register register;

    public Registration(Scanner scanner, register register) {
        this.s = scanner;
        this.register = register;
    }

    public void startRegistration() {
        System.out.println("Registration");

        registerEmail();
        registerPassword();
        registerNickname();
        //handleTermsAndConditions();
        registerIncome();
        registerUtilityDueDates();
        register.write();
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
                    if (register.isEmailExist(email)) {
                        System.out.println("Email already exists.");
                    }
                    else {
                        register.setEmail(email);
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
                register.setPassword(password);
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
                register.setNickname(nickname);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input for nickname. Please try again.");
            }
        }
    }

    /**private void handleTermsAndConditions() {
        terms.display();

        while (true) {
            try {
                System.out.print("\tDo you agree to the terms and conditions? (y/n): ");
                char agree = s.nextLine().toLowerCase().charAt(0);

                if (agree == 'y' || agree == 'n') {
                    if (agree == 'n') {
                        System.out.println("You must agree to the terms and conditions to proceed.");
                        break;
                    }
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }*/

    private void registerIncome() {
        while (true) {
            try {
                System.out.print("Enter Income: ");
                double income = Double.parseDouble(s.nextLine());
                register.setIncome(income);
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
            if (register.isCorrectdate(date)) {
                if (utility.equals("ELECTRICITY")) {
                    register.setElectricity(date);
                }
                else if (utility.equals("WATER")) {
                    register.setWater(date);
                }
                else if (utility.equals("RENT")) {
                    register.setRent(date);
                }
                else if (utility.equals("INTERNET")) {
                    register.setInternet(date);
                }
                break;
            }
        }
    }
}

