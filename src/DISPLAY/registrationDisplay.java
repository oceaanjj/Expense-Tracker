package display;

public class registrationDisplay extends menu {
    public static final String RESET = "\u001B[0m";        
    public static final String YELLOW_TEXT = "\u001B[33m";  

    public void display() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(YELLOW_TEXT + "\t\t\t\t                        ____               _       _                _    _               " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t                       |  _ \\  ___   __ _ (_) ___ | |_  _ __  __ _ | |_ (_)  ___   _ __  " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t                       | |_) |/ _ \\ / _` || |/ __|| __|| '__|/ _` || __|| | / _ \\ | '_ \\ " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t                       |  _ <|  __/| (_| || |\\__ \\| |_ | |  | (_| || |_ | || (_) || | | | " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t                       |_| \\_\\\\___| \\__, ||_||___/ \\__||_|   \\__,_| \\__||_| \\___/ |_| |_|" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t                                    |___/                                                 " + RESET);
        System.out.println("\n\n");
    
    
        }
}
