package account;

import display.ClearScreen;
import display.myAccountHeader;
import java.util.Scanner;


    /* 
       this is the class that will be used to update the account details 
       ( called in our main ) it is connected to Editor ( the one that had access to txt files)
    */
public class AccountUpdater extends AccountEditor {
    public static final String GREEN_TEXT = "\u001B[32m"; 
    public static final String RESET = "\u001B[0m";
    public static final String ORANGE_TEXT = "\u001B[38;5;214m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    private final Verification verifier = new Verification();
    private final Scanner s = new Scanner(System.in);
    private final Confirmation confirm = new Confirmation();
    ClearScreen clr = new ClearScreen();
    myAccountHeader myAccountHeader = new myAccountHeader();

    
    public void changeEmail() {
        while(true){
            myAccountHeader.display();
            System.out.print(GREEN_TEXT + "\n\t\t\t\t\t\t\t\tEnter your new email : " + RESET);
            String newEmail = s.nextLine();
        
            if(!newEmail.contains("@") || !newEmail.contains(".")) {
                clr.clearScreen();
                myAccountHeader.display();
                System.out.print(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* REMINDER : Email should contain '@' and '.'" + RESET);
                continue;
            }
            else{
                    if (isEmailInUse(newEmail)) {
                        clr.clearScreen();
                        myAccountHeader.display();
                        System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* The new email is already in use. Please try another email." + RESET);
                        changed = false;
                        continue;
                    }
                    else{
                    
                        setNewEmail(newEmail);
                        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
                            clr.clearScreen();
                            myAccountHeader.display();
                            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t* Verification failed. Cancelling email change." + RESET);
                            return;
                        }

                        if (confirm.confirmAction("\t\t\t\t\t\t\t\tAre you sure you want to change your email? (y/n): ")) {
                            updateEmail();
                            return;
                        }
                        else {
                            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\tChanging account email cancelled." + RESET);
                            return;
                        }
                    }
            }   
        }
    }

    public void changePassword() {
        System.out.print(ORANGE_TEXT + "\n\t\t\t\t\t\t\t\t\tEnter your new password:" + RESET);
        String newPassword = s.nextLine();
        setNewPassword(newPassword);

        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
            clr.clearScreen();
            myAccountHeader.display();
            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\t* Verification failed. Cancelling password change." + RESET);
            return;
        }

        if (confirm.confirmAction("\t\t\t\t\t\t\t\t\tAre you sure you want to change your password? (y/n): ")) {
            updatePassword();
        }
        else {
            clr.clearScreen();
            myAccountHeader.display();
            System.out.println("\t\t\t\t\t\t\t\t\tChanging account password cancelled.");
        }
    }

    public void changeIncome() {
        System.out.print(GREEN_TEXT + "\t\t\t\t\t\t\t\t\tEnter your new monthly income : " + RESET);
        double newIncome = s.nextDouble();
        setMonthlyIncome(newIncome);

        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
            clr.clearScreen();
            myAccountHeader.display();
            System.out.println(ORANGE_TEXT + "* Verification failed. Cancelling income change." + RESET);
            return;
        }

        if (confirm.confirmAction("\t\t\t\t\t\t\t\t\tAre you sure you want to change your monthly income? (y/n): ")) {
            updateIncome();
        }
        else {
            clr.clearScreen();
            myAccountHeader.display();
            System.out.println(ORANGE_TEXT + "\t\t\t\t\t\t\t\t\t* Changing monthly income cancelled." + RESET);
        }
    }

}

    
