package display;

public class myAccountHeader extends menu {
    final String YELLOW = "\u001B[33m";
    final String RESET = "\u001B[0m";

    @Override
    public void display() {
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t___  ___         ___                            _   " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t|  \\/  |        / _ \\                          | |  " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t| .  . |_   _  / /_\\ \\ ___ ___ ___  _   _ _ __ | |_ " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t| |\\/| | | | | |  _  |/ __/ __/ _ \\| | | | '_ \\| __|" + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t| |  | | |_| | | | | | (_| (_| (_) | |_| | | | | |_ " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t\\_|  |_/\\__, | \\_| |_/\\___\\___\\___/ \\__,_|_| |_|\\__|" + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t         __/ |                                      " + RESET);
        System.out.println(YELLOW + "\t\t\t\t\t\t\t\t        |___/                                       " + RESET);
    }
    
}
