package user;
import java.util.Scanner;
public class Needs {
    public void needs(){
        Scanner s = new Scanner(System.in);
        System.out.println("NEEDS");
        System.out.println("[1] FOOD");
        System.out.println("[2] TRANSPORTATION");
        System.out.println("[3] BILLS");
        System.out.println("[4] EXIT");
        System.out.println("Enter your choice : ");
        int choice = s.nextInt();

        switch(choice){
            case 1:
                System.out.println("FOOD");
                break;
            case 2:
                System.out.println("TRANSPORTATION");
                break;
            case 3:
                System.out.println("BILLS");
                break;
            case 4:
                System.out.println("EXIT");
                break;
        }

    }
}
