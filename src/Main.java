import Displays.TermsAndConditions;
import Displays.asciiArt;
import Displays.clearScreen;
import Displays.intro;
import Displays.loading;
import Displays.mainmenu;
import ExpenseTracker.Database;
import ExpenseTracker.login;
import ExpenseTracker.register;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String email = "", password = "", nickname = "";
        double income = 0.0;
        int choice = 0;
        char agree;
        String electricityDate, waterDate, rentDate, internetDate;

        TermsAndConditions terms = new TermsAndConditions();
        mainmenu mainmenu = new mainmenu();
        loading load = new loading();
        clearScreen clr = new clearScreen();
        register register = new register();
        Database db = new Database();
        login login = new login();
        asciiArt art = new asciiArt();
        intro intro = new intro();

        
        //start
        clr.clearScreen();

        art.display();
        load.loading();
        clr.clearScreen();

        //intro.display(); 
        //may error sa intro.display

        
       mainloop : while (true) {

            while (true) { 
                mainmenu.display();
                System.out.print("\n   Enter choice : ");
                
                try {
                    choice = s.nextInt();
                    s.nextLine(); 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please enter a valid number.");
                    clr.clearScreen();
                    s.nextLine(); 
                    continue;
                }
            

            switch (choice) {
                //registration
                case 1: 
                    clr.clearScreen();
                    System.out.println("Registration");

                    while (true) {
                        try {
                            System.out.print("Enter Email: ");
                            email = s.nextLine();

                            if (email == null){
                                System.out.println("Email is required.");
                            }
                            else if(!email.contains("@") || !email.contains(".")) {
                                throw new Exception();
                            }
                            else{
                                register.setEmail(email);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input for email. Please try again.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Enter Password: ");
                            password = s.nextLine();
                            register.setPassword(password);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for password. Please try again.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Enter Nickname: ");
                            nickname = s.nextLine();
                            register.setNickname(nickname);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input for nickname. Please try again.");
                        }
                    }

                    terms.display();

                    while (true) {
                        try {
                            System.out.print("\tDo you agree to the terms and conditions? (y/n): ");
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
                            register.setIncome(income);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for income. Please enter a valid number.");
                        } catch (Exception e) {
                            System.out.println("An error occurred while entering income. Please try again.");
                        }
                    }


                    /*utilities due dates keme */
                    
                    while (true) {
                        System.out.println("ELECTRICITY");
                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        electricityDate = s.nextLine();
                        if (register.isCorrectdate(electricityDate)) {
                            register.setElectricity(electricityDate);
                            break;
                        }
                    }
                    
                
                    while (true) {
                        System.out.println("WATER");
                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        waterDate = s.nextLine();
                        if (register.isCorrectdate(waterDate)) {
                            register.setWater(waterDate);
                            break;
                        }
                    }
                    
                  
                    while (true) {
                        System.out.println("RENT");
                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        rentDate = s.nextLine();
                        if (register.isCorrectdate(rentDate)) {
                            register.setRent(rentDate);
                            break;
                        }
                    }
                    
                  
                    while (true) {
                        System.out.println("INTERNET");
                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        internetDate = s.nextLine();
                        if (register.isCorrectdate(internetDate)) {
                            register.setInternet(internetDate);
                            break;
                        }
                    }



    
                    register.write();
                   
                    break;







                case 2: 
                    //login
                    clr.clearScreen();
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
                        
                       
                    } else {
                        
                    }
                    break;





                case 3: 
                    System.out.println("\n\tThnak you for using our application!");
                    break mainloop;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        }
    }
}