import ExpenseTracker.Database;
import ExpenseTracker.login;
import ExpenseTracker.menu;
import ExpenseTracker.register;
import java.util.*;

public class Main {
    public static void main(String[] args) { 
        String email, password, nickname;  
        double income;
        Scanner s = new Scanner(System.in);
        menu menu = new menu();
        register register = new register();
        Database db = new Database();
        login login = new login();

        /**menu.loading();
        menu.clearScreen();
        //try*/

        
        menu.mainmenu();
        System.out.print("\n   Enter choice : ");
        int choice = s.nextInt();


        switch (choice) {
            //Registration
            case 1:
                menu.clearScreen();
                System.out.println("Registeration");
                s.nextLine();
                System.out.print("Enter Email : ");
                email = s.nextLine();
                register.addEmail(email);

                System.out.print("Enter Password : ");
                password = s.nextLine();
                register.addPass(password);

                System.out.print("Enter Nickname : ");
                nickname = s.nextLine();
                register.addNickname(nickname);

                    menu.termsAndConditions();
                    System.out.print("Do you agree to the terms and conditions ? (y/n) : ");
                    char agree = s.next().charAt(0);
                    if(agree == 'n') {
                    
                        System.out.println("You must agree to the terms and conditions to proceed.");
                    }
                    else if(agree == 'y') {
                        System.out.print("Enter Monthly Income : ");
                        income = s.nextLong();
                        register.Income(income);    
                        System.out.println("Registration Successful");

                        db.write(email, password, nickname, income);
                
                        return;
                    }

                s.nextLine();


       

                break;

                //Login
            case 2:

                menu.clearScreen();
                System.out.println("Login");
                s.nextLine();
                System.out.print("Enter Email : ");
                email = s.nextLine();
                login.Email(email);

                System.out.print("Enter Password : ");
                password = s.nextLine();
                login.Password(password);
                
                login.userLogin(email, password);

                break;



            case 3:
                return;
            default:
                break;
        }

        
    }
    
}
