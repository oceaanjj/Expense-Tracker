package user;
import java.util.Scanner;

public class userMain {

    public void startExpenseTracker(Savings savings, Needs needs) {
        Scanner s = new Scanner(System.in);
        System.out.println("START TRACKING YOUR EXPENSES");
        System.out.println("[1] NEEDS");
        System.out.println("[2] WANTS");
        System.out.println("[3] SAVINGS");
        System.out.println("[4] EXIT");
        System.out.println("Enter your choice : ");
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                //Needs needs = new Needs();
                needs.needs();
                break;
            case 2:
                Wants wants = new Wants();
                wants.wants();
                break;
            case 3:
                //Savings savings = new Savings(); kailangan iget yung instance sa main para hindi mag null yung email
                savings.addSavings();
                break;
            case 4:
                System.out.println("EXIT");
                break;
        }
    }
    
}
