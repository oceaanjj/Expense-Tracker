package account;

import java.io.File;

public class AccountDeleter extends AccountEditor {
    private final Verification verifier = new Verification();
    private final Confirmation confirm = new Confirmation();

    public void deleteAccount() {
        while(true){
            if (verifier.verifyEmail(this) && verifier.verifyPassword(this)) {
                if (confirm.confirmAction("Are you sure you want to delete your account? (y/n): ")) {
                    deleteAllFiles();
                    return;
                } else {
                    System.out.println("Account deletion cancelled.");
                }
            } else {
                System.out.println("Verification failed. Account deletion aborted.");
            }
        }
    }

    //path to the files to be deleted (accounts, needs, savings, wants)
    private void deleteAllFiles() {
        String baseDir = System.getProperty("user.dir");
        deleteSingleFile(baseDir + "/src/database/accounts/" + getEmail() + ".txt");
        deleteSingleFile(baseDir + "/src/database/needs/" + getEmail() + ".txt");
        deleteSingleFile(baseDir + "/src/database/savings/" + getEmail() + ".txt");
        deleteSingleFile(baseDir + "/src/database/wants/" + getEmail() + ".txt");

        System.out.println("ACCOUNT SUCCESSFULLY DELETED.");
    }

    private void deleteSingleFile(String filePath) {
        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("Deleted file: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File not found: " + filePath);
        }
    }
}
