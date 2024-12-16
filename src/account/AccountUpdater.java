package account;

import java.util.Scanner;


    /* 
       this is the class that will be used to update the account details 
       ( called in our main ) it is connected to Editor ( the one that had access to txt files)
    */
public class AccountUpdater extends AccountEditor {
    private final Verification verifier = new Verification();
    private final Scanner s = new Scanner(System.in);
    private final Confirmation confirm = new Confirmation();

    
    public void changeEmail() {
        while(true){
            System.out.println("Enter your new email:");
            String newEmail = s.nextLine();
        
            if(!newEmail.contains("@") || !newEmail.contains(".")) {
                System.out.println(" * REMINDER : Email should contain '@' and '.'");
                continue;
            }
            else{
                    setNewEmail(newEmail);
                    if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
                        System.out.println("Verification failed. Cancelling email change.");
                        return;
                    }

                    if (confirm.confirmAction("Are you sure you want to change your email? (y/n): ")) {
                        updateEmail();
                        return;
                    }
                    else {
                        System.out.println("Changing account email cancelled.");
                        return;
                    }
            }   
        }
    }

    public void changePassword() {
        System.out.println("Enter your new password:");
        String newPassword = s.nextLine();
        setNewPassword(newPassword);

        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
            System.out.println("Verification failed. Cancelling password change.");
            return;
        }

        if (confirm.confirmAction("Are you sure you want to change your password? (y/n): ")) {
            updatePassword();
        }
        else {
            System.out.println("Changing account password cancelled.");
        }
    }

    public void changeIncome() {
        System.out.println("Enter your new monthly income:");
        double newIncome = s.nextDouble();
        setMonthlyIncome(newIncome);

        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
            System.out.println("Verification failed. Cancelling income change.");
            return;
        }

        if (confirm.confirmAction("Are you sure you want to change your monthly income? (y/n): ")) {
            updateIncome();
        }
        else {
            System.out.println("Changing monthly income cancelled.");
        }
    }

}

    
