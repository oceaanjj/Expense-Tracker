import DISPLAY.ClearScreen;
import DISPLAY.LoadingMenu;
import DISPLAY.MyAccount;
import DISPLAY.TermsAndConditions;
import DISPLAY.UserMenu;
import DISPLAY.asciiArt;
import DISPLAY.intro;
import DISPLAY.mainmenu;
import ExpenseTracker.AccountDeleter;
import ExpenseTracker.AccountUpdater;
import ExpenseTracker.Registration;
import ExpenseTracker.TemporaryDatabase;
import ExpenseTracker.login;
import ExpenseTracker.register;
import java.util.*;

public class Main {
    /***
     * upate case 4 in account delete
     * 
     * */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String email = "", password = "", nickname = "";
        double income = 0.0;
        int choice = 0;
        char agree;
        String confirmation;
        String registeredEmail, registeredPassword;
        //String electricityDate, waterDate, rentDate, internetDate;

        TermsAndConditions terms = new TermsAndConditions();
        mainmenu mainmenu = new mainmenu();
        LoadingMenu load = new LoadingMenu();
        ClearScreen clr = new ClearScreen();
        register register = new register();
        TemporaryDatabase db = new TemporaryDatabase();
        login login = new login();
        asciiArt art = new asciiArt();
        intro intro = new intro();
        UserMenu userMenu = new UserMenu();
        MyAccount myAccount = new MyAccount();
        AccountDeleter delete = new AccountDeleter();
        AccountUpdater updater = new AccountUpdater();
        

        
        //start
        clr.clearScreen();

        art.display();
        load.loading();
        clr.clearScreen();

        //intro.display(); 
        //may error sa intro.display

        
       mainloop : while (true) {

            //while (true) { 
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
                                Registration registration = new Registration(s, register);
                                registration.startRegistration();
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
                                    
                                UserMainMenu : while (true) {
                                    userMenu.display();
                                    System.out.println("Enter choice: ");
                                    int userChoice = s.nextInt();


                                    switch(userChoice){
                                        case 1:
                                        myAccountmenu : while (true) {
                                            myAccount.display();
                                            System.out.println("Enter choice: ");
                                            int accountChoice = s.nextInt();
                                            switch(accountChoice){
                                                case 1:
                                                    //change email (medyo done)                                
                                                    updater.changeEmail();
                                                    break;    

                                                case 2:
                                                    //change password (medyo done)
                                                    updater.changePassword();
                                                    break;
  
                                                case 3:
                                                    
                                                    //change monthly income (medyo done)
                                                    updater.changeIncome();
                                                    break;

                                                case 4:
                                                
                                                    //deletion ng account (sure na ata pag di nag bug letse)
                                                    delete.deleteAccount();

                                                        


                                                case 5:
                                                /**
                                                 * 
                                                 * 
                                                 * 
                                                 * 
                                                 * RAIN - PRINTING OF DATA SHARING AGREEMENT
                                                 * 
                                                 * 
                                                 * 
                                                 * 
                                                 * 
                                                 */
                                                //*ipapagawa kay rain */
                                                    //read data sharing agreement
                                                    break;

                                                case 6:
                                                /**
                                                 * 
                                                 * 
                                                 * 
                                                 * 
                                                 * RAIN - PRINTING HOW TO USE THE SYSTEM
                                                 * 
                                                 * 
                                                 * 
                                                 *                                         
                                                 */
                                                //*ipapagawa kay rain */
                                                    //help
                                                    break;

                                                case 7:
                                                
                                                    continue UserMainMenu;
                                                default:
                                                    System.out.println("Invalid choice. Please select a valid option.");
                                                    break;
                                            }//switch case of my accountAccount
                                        } //my account end loop
                                        
                                        

                                        /** 
                                         * 
                                         * 
                                         *  BRYAN - EXPENSE TRACKER  
                                         * 
                                         * 
                                         * 
                                         * */                                       
                                        case 2:
                                            //expense tracker
                                            break;


                                        case 3:
                                            //insights summary or history of the user expenses keme
                                            break;
                                        case 4:
                                            //logout
                                            continue mainloop;
                                        default:
                                            System.out.println("Invalid choice. Please select a valid option.");
                                            break;
                                    }
                                
                                } //end of user main menu loop

                                    }//login status
                                    else {
                                        System.out.println("Invalid email or password. Please try again.");
                                    }

                            
                                break;
                            





                            case 3: 
                                System.out.println("\n\tThnak you for using our application!");
                                break mainloop;

                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    //}
        }//end ng main loop
    }
}