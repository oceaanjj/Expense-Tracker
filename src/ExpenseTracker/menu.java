package ExpenseTracker;

import java.util.*;

public class menu extends Date {

    public void mainmenu() {
        date();
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                               ║");
        System.out.println("║\t\tWelcome to Expense Tracker System               ║");
        System.out.println("║                                                               ║");
        System.out.println("║                                                               ║");
        System.out.println("║                                                               ║");
        System.out.println("║\t\t\t  [1] Register                          ║");
        System.out.println("║\t\t\t  [2] Login                             ║");
        System.out.println("║\t\t\t  [3] Exit                              ║");
        System.out.println("║                                                               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    public void loading() {
        try {
            System.out.println("\t\t\t\t\t\tLoading:");
            for (int i = 0; i <= 100; i += 2) {
                String progress = "╸".repeat(i);
                System.out.print("\r" + i + "%  " + progress );
                Thread.sleep(100);
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void termsAndConditions() {
        Scanner s = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                              ║");
        System.out.println("║\tTERMS AND CONDITIONS                                                                                   ║");
        System.out.println("║                                                                                                              ║");
        System.out.println("║\t\tPlease read the following terms and conditions                                                 ║");
        System.out.println("║\t\tcarefully before registering and using our system.                                             ║");
        System.out.println("║                                                                                                              ║");
        System.out.println("║\t\t\t1. ACCEPTANCE OF TERMS                                                                 ║");       
        System.out.println("║\t\t\tBy registering and using our system, you confirm that you have read,                   ║");
        System.out.println("║\t\t\tunderstood, and agree to be bound by the terms and conditions of this                  ║");
        System.out.println("║\t\t\tagreement. If you do not agree to the terms, you may not use the system.               ║");
        System.out.println("║                                                                                                              ║");
        System.out.println("║\t\t\t2. USER INFORMATION                                                                    ║");
        System.out.println("║\t\t\tTo provide a personalized experience, we collect the following information             ║");
        System.out.println("║\t\t\tduring registration and usage:                                                         ║");
        System.out.println("║                                                                                                              ║");
        System.out.println("║\t\t\tPersonal Details: Email address (Gmail only).                                          ║");
        System.out.println("║\t\t\tFinancial Data: Monthly income, expenses, and categories.                              ║");
        System.out.println("║\t\t\tWe ensure that your information is securely stored and used solely for improving       ║");
        System.out.println("║\t\t\tyour experience within the system.                                                     ║");
        System.out.println("║                                                                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("\tPress Enter to continue...");
        s.nextLine();

        
    }
}
