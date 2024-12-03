import ExpenseTracker.Database;
import ExpenseTracker.mail;
import ExpenseTracker.menu;
import ExpenseTracker.register;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        mail mail = new mail();
        String email, password, nickname;
        double income;
        Scanner s = new Scanner(System.in);
        menu menu = new menu();
        register register = new register();
        Database db = new Database();

        /*menu.loading();
        menu.clearScreen();*/
        
        //try
        menu.termsAndConditions();
        
        menu.mainmenu();
        System.out.print("\n   Enter choice : ");
        int choice = s.nextInt();


        switch (choice) {
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

                System.out.print("Enter Monthly Income : ");
                income = s.nextDouble();
                register.Income(income);
       
                s.nextLine();

                menu.termsAndConditions();

                s.nextLine();

                break;
            case 2:
                
                break;
            case 3:
                return;
            default:
                break;
        }

        
    }
    
}
