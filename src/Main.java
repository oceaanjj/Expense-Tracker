import ExpenseTracker.Database;
import ExpenseTracker.login;
import ExpenseTracker.menu;
import ExpenseTracker.register;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String email = "", password = "", nickname = "";
        double income = 0.0;
        int choice = 0;
        Scanner s = new Scanner(System.in);
        menu menu = new menu();
        register register = new register();
        Database db = new Database();
        login login = new login();

       mainloop : while (true) { 
            //menu.mainmenu();
            /**System.out.print("\n   Enter choice : ");
            int choice = s.nextInt();*/

            while (true) { 
                menu.mainmenu();
                System.out.print("\n   Enter choice : ");
                
                try {
                    choice = s.nextInt();
                    s.nextLine(); 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please enter a valid number.");
                    s.nextLine(); 
                    continue;
                }
            

            switch (choice) {
                //registration
                case 1: 
                    menu.clearScreen();
                    System.out.println("Registration");

                    while (true) {
                        try {
                            System.out.print("Enter Email: ");
                            email = s.nextLine();
                            db.setEmail(email);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for email. Please try again.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Enter Password: ");
                            password = s.nextLine();
                            db.setPassword(password);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for password. Please try again.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Enter Nickname: ");
                            nickname = s.nextLine();
                            db.setNickname(nickname);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for nickname. Please try again.");
                        }
                    }

                    menu.termsAndConditions();
                    char agree;

                    while (true) {
                        try {
                            System.out.print("Do you agree to the terms and conditions? (y/n): ");
                            agree = s.nextLine().toLowerCase().charAt(0);
                            if (agree == 'y' || agree == 'n') {
                                break;
                            } else {
                                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                    }

                            if (agree == 'n') {
                                System.out.println("You must agree to the terms and conditions to proceed.");
                                break;
                            }

                    while (true) {
                        try {
                            System.out.print("Enter Income: ");
                            income = Double.parseDouble(s.nextLine());
                            db.setIncome(income);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for income. Please enter a valid number.");
                        } catch (Exception e) {
                            System.out.println("An error occurred while entering income. Please try again.");
                        }
                    }

                    db.write();
                    System.out.println("Registration successful!");
                    break;

                case 2: 
                    //login
                    menu.clearScreen();
                    System.out.println("Login");

                    while (true) {
                        try {
                            System.out.print("Enter Email: ");
                            email = s.nextLine();
                            login.setEmail(email);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for email. Please try again.");
                        }
                    }

                        while (true) {
                            try {
                                System.out.print("Enter Password: ");
                                password = s.nextLine();
                                login.setPassword(password);
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid input for password. Please try again.");
                            }
                        }

                    boolean loginStatus = login.userLogin(login.getEmail(), login.getPassword());
                    if (loginStatus) {
                        //System.out.println("Login successful!"); nagdodouble print ng login
                       
                    } else {
                        System.out.println("Login failed. Please check your credentials.");
                    }
                    break;

                case 3: 
                    System.out.println("Thnak you for using our application!");
                    break mainloop;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        }
    }
}