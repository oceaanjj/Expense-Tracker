package ExpenseTracker;

import java.util.Scanner;

public class AccountUpdater extends UpdateAccount {

    private Scanner s = new Scanner(System.in);
    private UpdateAccount update = new UpdateAccount();
    


    public void changeEmail() {
        String newEmail;
        
        while (true) {
            System.out.print("Enter new email: ");
            newEmail = s.nextLine();
            
            if (!newEmail.contains("@") || !newEmail.contains(".")) {
                System.out.println("Invalid email address.");
                continue; 
            } /**else if (newEmail.equals(update.getEmail())) {
                System.out.println("New email is the same as the current email.");
                continue; 
            }**/
            

            update.setNewEmail(newEmail);
            break; 
        }
    
        if (!verifyEmail()) {
            return; 
        }
    

        if (!verifyPassword()) {
            return; 
        }
    
        System.out.print("Are you sure you want to change your email? (y/n): ");
        String confirmation = s.nextLine().toLowerCase().trim();
    
        if (confirmation.charAt(0) == 'y') {
            update.ChangeEmail();
        } else {
            System.out.println("Changing account email cancelled.");
        }
    }
    
    /**public void changeEmail() { 
        System.out.println("Enter new email : ");
        String newEmail = s.nextLine();
        
        update.setNewEmail(newEmail);
        s.nextLine();

        if (!verifyEmail()) {
            return; 
        }

        if (!verifyPassword()) {
            return; 
        }

        System.out.print("Are you sure you want to change your email? (y/n): ");
        String confirmation = s.nextLine().toLowerCase().trim();

        if (confirmation.charAt(0) == 'y') {
            update.ChangeEmail();
        } else {
            System.out.println("Changing account email cancelled.");
        }
    }**/

    public void changePassword() {
        System.out.print("Enter new password: ");
        String newPassword = s.nextLine();
        update.setNewPassword(newPassword);
        s.nextLine();

        if (!verifyEmail()) {
            return;  
        }

        if (!verifyPassword()) {
            return;  
        }

        System.out.print("Are you sure you want to change your password? (y/n): ");
        String confirmation = s.nextLine().toLowerCase().trim();

        if (confirmation.charAt(0) == 'y') {
            update.ChangePassword();
        } else {
            System.out.println("Changing account password cancelled.");
        }
    }

    public void ChangeIncome() {
        System.out.print("Enter new income : ");
        double newIncome = s.nextDouble();
        update.setMonthlyIncome(newIncome);

        s.nextLine();

        if (!verifyEmail()) {
            return;  
        }

        if (!verifyPassword()) {
            return;  
        }

        System.out.print("Are you sure you want to change your income? (y/n): ");
        String confirmation = s.nextLine().toLowerCase().trim();

        if (confirmation.charAt(0) == 'y') {
            update.ChangeIncome();
        } else {
            System.out.println("Changing account income cancelled.");
        }
    }

    private boolean verifyEmail() {
        String registeredEmail;
        while (true) {
            System.out.print("Enter your registered email: ");
            registeredEmail = s.nextLine();
            update.setEmail(registeredEmail);

            if (!update.isCorrectEmail()) {
                System.out.println("Email does not match the registered email.");
                continue;
            } else {
                return true;  
            }
        }
    }

    private boolean verifyPassword() {
        String registeredPassword;
        while (true) {
            System.out.print("Enter your account password: ");
            registeredPassword = s.nextLine();
            update.setPassword(registeredPassword);

            if (!update.isCorrectPassword()) {
                System.out.println("Incorrect password.");
                continue;
            } else {
                return true;  
            }
        }
    }
}
