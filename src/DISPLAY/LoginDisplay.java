package display;

public class LoginDisplay extends menu {

    public static final String RESET = "\u001B[0m";    // Reset to default color
    public static final String YELLOW = "\u001B[33m";

    @Override
    public void display() {
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t __        ______     _______        __  .__   __. " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|  |      /  __  \\   /  _____|      |  | |  \\ |  | " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|  |     |  |  |  | |  |  __        |  | |   \\|  | " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|  |     |  |  |  | |  | |_ |       |  | |  . `  | " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|  `----.|  `--'  | |  |__| |       |  | |  |\\   | " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|_______| \\______/   \\______|       |__| |__| \\__| " + RESET);
        System.out.println(YELLOW + "                                                    " + RESET);     
        System.out.println();        
 
    }
    
}
