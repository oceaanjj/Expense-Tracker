package ExpenseTracker;

import java.util.Scanner;

public class AccountUpdater extends TextFileModifier {
    private final Verification verifier = new Verification();
    private final Scanner s = new Scanner(System.in);
    private final Confirmation confirm = new Confirmation();

    public void changeEmail() {
        System.out.println("Enter your new email:");
        String newEmail = s.nextLine();
        setNewEmail(newEmail);

        if (!verifier.verifyEmail(this) || !verifier.verifyPassword(this)) {
            System.out.println("Verification failed. Cancelling email change.");
            return;
        }

        if (confirm.confirmAction("Are you sure you want to change your email? (y/n): ")) {
            ChangeEmail();
        }
        else {
            System.out.println("Changing account email cancelled.");
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
            ChangePassword();
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
            ChangeIncome();
        }
        else {
            System.out.println("Changing monthly income cancelled.");
        }
    }

}

    
