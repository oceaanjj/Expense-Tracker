package ExpenseTracker;

import java.util.Scanner;

public class User extends TemporaryDatabase{
    Scanner in = new Scanner(System.in);
    
    //menu
    public void userMenu() {
        System.out.println(" Menu ");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Calculate Total Expenses");
        System.out.println("5. Filter Expenses by Date Range");
        System.out.println("6. Exit");
    }

    public void addExpense() {
        System.out.println("Enter Category");
        System.out.println("[1] Foods ");
        System.out.println("[2] Transportation ");
        System.out.println("[3] Utility Bill");
        System.out.println("[4] Rent");
        System.out.println("[5] Exit");
        int choice = in.nextInt();
//then tsaka maglalagay ng addamount kinemberut tsaka babawasan yung income nila
        switch (choice) {
            case 1:

                break;
            case 2:
                break;
            default:
                break;
        }
    }







//later na tech focus muna aq sa expense kineme
    public void Savings(){
        
    }
}
