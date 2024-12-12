package user;

import java.util.Scanner;

public class User {
    Scanner s = new Scanner(System.in);
    userMenuDisplay menu = new userMenuDisplay();
    Needs needs = new Needs();

    public void startExpenseTracker() {

        while(true){
            menu.display();
            System.out.println("Enter choice: ");
            int choice = s.nextInt();
            
            switch (choice) {
                case 1:
                    needs.addNeeds();
                    break;
                case 2:
                    addSavings();
                    break;
                case 3:
                    addWants();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }
    

   







//later na tech focus muna aq sa expense kineme
    public void addSavings(){
        
    }


    public void addWants(){
        
    }
}
