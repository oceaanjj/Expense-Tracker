import account.AccountDeleter;
import account.AccountUpdater;
import account.Login;
import account.Registration;
import display.clearScreen;
import display.LoadingMenu;
import display.MyAccount;
import display.UserMenu;
import display.asciiArt;
import display.intro;
import display.mainmenu;
import java.util.*;
import user.Needs;
import user.Savings;
import user.Wants;
import user.userMain;

public class Main {
    /***
     * KULANG :
     * TRY AND CATCH SA IBANG PARTS NG CODE NAG EERROR DAPAT KAPAG INVALID INPUT TAS
     * BABALIK, MAGAASK AGAIN
     * 
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;
        

        // TermsAndConditions terms = new TermsAndConditions();
        mainmenu mainmenu = new mainmenu();
        LoadingMenu load = new LoadingMenu();
        clearScreen clr = new clearScreen();
        Registration register = new Registration();
        Login login = new Login();
        asciiArt art = new asciiArt();
        intro intro = new intro();
        UserMenu userMenu = new UserMenu();
        MyAccount myAccount = new MyAccount();
        AccountDeleter delete = new AccountDeleter();
        AccountUpdater updater = new AccountUpdater();
        userMain user = new userMain();
        Savings savings = new Savings();
        Needs needs = new Needs();
        Wants wants = new Wants();
<<<<<<< HEAD
        Dashboard dashboard = new Dashboard();

        
=======
>>>>>>> origin/360

        // start
        clr.clearScreen();
        art.display();
        //load.loading();
        clr.clearScreen();

        //displaying intro title
        intro.display(); 

        
       mainloop : while (true) {
            clr.clearScreen();
            //while (true) { 
                mainmenu.display();

                System.out.print("\n\t\t\t\t\t\t\t   Enter choice : ");
                
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
                // registration
                case 1:
                    clr.clearScreen();
                    register.startRegistration();
                    break;

<<<<<<< HEAD
                            case 2: 
                            //login
                                //clr.clearScreen();
                                
                                //System.out.println("Login");
                           
                                                                                           
                                if (login.userLogin()) {
                                        savings.setEmail(login.getEmail());
                                        needs.setEmail(login.getEmail());
                                        wants.setEmail(login.getEmail());
                                        dashboard.setEmail(login.getEmail());

                                        login.displayUserName(login.getEmail());
                                        
                                        
                                                    
                                                                                 
                                            UserMainMenu : while (true) {
                                                clr.clearScreen();
                                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                login.displayUserName(login.getEmail());
                                                
                                                userMenu.display();
                                                System.out.print("\n\t\t\t\t\t\t\t     Enter choice : ");
                                                int userChoice = s.nextInt();

                                                switch(userChoice){
                                                    case 1:
                                                        /*myAccountmenu :*/ while (true) {
                                                            clr.clearScreen();
                                                            myAccount.display();
                                                            System.out.print("\n\t\t\t\t\t\t\t   Enter choice : ");
                                                            int accountChoice = s.nextInt();
                                                            switch(accountChoice){

                                                                case 1:
                                                                clr.clearScreen();
                                                                    //change email (medyo done)                                
                                                                    updater.changeEmail();
                                                                    break;    

                                                                case 2:
                                                                clr.clearScreen();
                                                                    //change password (medyo done)
                                                                    updater.changePassword();
                                                                    break;
                
                                                                case 3:
                                                                clr.clearScreen();
                                                                    //change monthly income (medyo done)
                                                                    updater.changeIncome();
                                                                    break;
=======
                case 2:
                    // login
                    clr.clearScreen();

                    System.out.println("Login");

                    if (login.userLogin() == true) {
                        savings.setEmail(login.getEmail());
                        needs.setEmail(login.getEmail());
                        wants.setEmail(login.getEmail());
                        user.setEmail(login.getEmail());
                        // System.out.println(savings.getEmail());

                        // boolean loginStatus = login.userLogin(login.getEmail(), login.getPassword());
>>>>>>> origin/360

                        UserMainMenu: while (true) {
                            userMenu.display();
                            System.out.println("Enter choice: ");
                            int userChoice = s.nextInt();

                            switch (userChoice) {
                                case 1:
                                    /* myAccountmenu : */ while (true) {
                                        myAccount.display();
                                        System.out.println("Enter choice: ");
                                        int accountChoice = s.nextInt();
                                        switch (accountChoice) {

                                            case 1:
                                                // change email (medyo done)
                                                updater.changeEmail();
                                                break;

                                            case 2:
                                                // change password (medyo done)
                                                updater.changePassword();
                                                break;

<<<<<<< HEAD
                                                                case 7:
                                                                
                                                                    continue UserMainMenu;
                                                                default:
                                                                    System.out.println("\n\t\t\t\t\t\t\t* Invalid choice. Please select a valid option.");
                                                                    break;
                                                            }//switch case of my accountAccount
                                                        } //my account end loop
                                                        
                                                        
=======
                                            case 3:
>>>>>>> origin/360

                                                // change monthly income (medyo done)
                                                updater.changeIncome();
                                                break;

                                            case 4:

                                                // deletion ng account (sure na ata pag di nag bug letse)
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
                                                // *ipapagawa kay rain */
                                                // read data sharing agreement
                                                break;

<<<<<<< HEAD
                                                    default:
                                                        System.out.println("\n\t\t\t\t\t\t\t* Invalid choice. Please select a valid option.");
                                                        break;
                                                    }
                                                
                                                } //end of user main menu loop

                                            }//login status
                                    else
                                    {
                                        break;
                                        
                                    }
                            

                                            
                            
                            
=======
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
                                                // *ipapagawa kay rain */
                                                // help
                                                break;

                                            case 7:

                                                continue UserMainMenu;
                                            default:
                                                System.out.println("Invalid choice. Please select a valid option.");
                                                break;
                                        }// switch case of my accountAccount
                                    } // my account end loop
>>>>>>> origin/360

                                case 2:
                                    // System.out.println(savings.getEmail());
                                    // savings.addSavings();
                                    user.startExpenseTracker(savings, needs, wants);
                                    break;

                                case 3:
                                    // dashboard
                                    //dashboard.displayDashboard();
                                    break;

                                case 4:
                                    // logout
                                    continue mainloop;

                                default:
                                    System.out.println("Invalid choice. Please select a valid option.");
                                    break;
                            }

                        } // end of user main menu loop

                    } // login status
                    else {
                        System.out.println("Invalid email or password. Please try again.");
                    }

                    break;

                case 3:
                    System.out.println("\n\tThank you for using our system !");
                    break mainloop;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
            // }
        } // end ng main loop
    }
}