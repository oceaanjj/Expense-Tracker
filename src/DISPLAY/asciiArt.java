package display;

public class asciiArt extends menu {

    public static final String RESET = "\u001B[0m";        // Reset color
    public static final String YELLOW_TEXT = "\u001B[33m";  // Yellow text

    @Override
    public void display() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                                  __,---.__" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                               ,-'         `-.__" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                             ₱/           `._\\ _\\" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                             /               ''._" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                            |   ,             (\")" + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t                            |__,'`-..--|__|--''" + RESET);
        System.out.println();
    }
}
