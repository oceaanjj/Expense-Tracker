package display;

import java.io.IOException;
import java.util.Scanner;

public class intro extends menu {
    public static final String RESET = "\u001B[0m";        // Reset color
    public static final String YELLOW_TEXT = "\u001B[33m";  // Yellow text // Light blue (Baby blue)


    @Override
    public void display() {
        
        System.out.println("PROGRAMMED BY : ");
        System.out.println("Esquerra, Jovilyn F.");
        System.out.println("Gerolia, Bryan James");
        System.out.println("Robles, Rain Louie");
        
        System.out.println(YELLOW_TEXT + "      __________   ___ .______    _______ .__   __.      _______. _______       " + RESET);
        System.out.println(YELLOW_TEXT + "     |   ____\\  \\ /  / |   _  \\  |   ____||  \\ |  |     /       ||   ____|      " + RESET);
        System.out.println(YELLOW_TEXT + "     |  |__   \\  V  /  |  |_)  | |  |__   |   \\|  |    |   (----`|  |__         " + RESET);
        System.out.println(YELLOW_TEXT + "     |   __|   >   <   |   ___/  |   __|  |  . `  |     \\   \\    |   __|        " + RESET);
        System.out.println(YELLOW_TEXT + "     |  |____ /  .  \\  |  |      |  |____ |  |\\   | .----)   |   |  |____       " + RESET);
        System.out.println(YELLOW_TEXT + "     |_______/__/ \\__\\ | _|      |_______||__| \\__| |_______/    |_______|      " + RESET);
        System.out.println(YELLOW_TEXT + ".___________..______          ___       ______  __  ___  _______ .______      " + RESET);
        System.out.println(YELLOW_TEXT + "|           ||   _  \\        /   \\     /      ||  |/  / |   ____||   _  \\     " + RESET);
        System.out.println(YELLOW_TEXT + "`---|  |----`|  |_)  |      /  ^  \\   |  ,----'|  '  /  |  |__   |  |_)  |    " + RESET);
        System.out.println(YELLOW_TEXT + "    |  |     |      /      /  /_\\  \\  |  |     |    <   |   __|  |      /     " + RESET);
        System.out.println(YELLOW_TEXT + "    |  |     |  |\\  \\----./  _____  \\ |  `----.|  .  \\  |  |____ |  |\\  \\----." + RESET);
        System.out.println(YELLOW_TEXT + "    |__|     | _| `._____/__/     \\__\\ \\______||__|\\__\\ |_______|| _| `._____|" + RESET);
        System.out.println();
    


     
        // Blinking effect loop
        Scanner s = new Scanner(System.in);
        int count = 0;
        boolean entered = false;

        // Blinking effect loop
        while (!entered) {
            if (count % 2 == 0) {
                System.out.print("\r                          Press Enter to continue...");
            } else {
                System.out.print("\r                                                    ");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    
            try {
                if (System.in.available() > 0) {
                    String input = s.nextLine();
                    if (input.isEmpty()) {
                        entered = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            count++;
        }

    }
}

     

