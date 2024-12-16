package display;

import java.io.IOException;
import java.util.Scanner;

public class intro extends menu {
    public static final String RESET = "\u001B[0m";        // Reset color
    public static final String YELLOW_TEXT = "\u001B[33m";  // Yellow text // Light blue (Baby blue)


    @Override
    public void display() {
        System.out.println("\n\n\n\n");
        System.out.println("\t\t\t\t\t\t                                  PROGRAMMED BY\n");
        System.out.println("\t\t\t\t\t\t                              Esquerra, Jovilyn F.");
        System.out.println("\t\t\t\t\t\t                              Gerolia, Bryan James");
        System.out.println("\t\t\t\t\t\t                               Robles, Rain Louie");
        System.out.println();
        System.out.println("\t\t\t\t\t\t                                  SUBMITTED TO\n");
        System.out.println("\t\t\t\t\t\t                            Prof. Aldwin Jan Quiben");
        System.out.println("\n\n\n\n");
        

        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t      __________   ___ .______    _______ .__   __.      _______. _______       " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t     |   ____\\  \\ /  / |   _  \\  |   ____||  \\ |  |     /       ||   ____|      " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t     |  |__   \\  V  /  |  |_)  | |  |__   |   \\|  |    |   (----`|  |__         " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t     |   __|   >   <   |   ___/  |   __|  |  . `  |     \\   \\    |   __|        " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t     |  |____ /  .  \\  |  |      |  |____ |  |\\   | .----)   |   |  |____       " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t     |_______/__/ \\__\\ | _|      |_______||__| \\__| |_______/    |_______|      " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t.___________..______          ___       ______  __  ___  _______ .______      " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t|           ||   _  \\        /   \\     /      ||  |/  / |   ____||   _  \\     " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t`---|  |----`|  |_)  |      /  ^  \\   |  ,----'|  '  /  |  |__   |  |_)  |    " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t    |  |     |      /      /  /_\\  \\  |  |     |    <   |   __|  |      /     " + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t    |  |     |  |\\  \\----./  _____  \\ |  `----.|  .  \\  |  |____ |  |\\  \\----." + RESET);
        System.out.println(YELLOW_TEXT + "\t\t\t\t\t\t    |__|     | _| `._____/__/     \\__\\ \\______||__|\\__\\ |_______|| _| `._____|" + RESET);
        System.out.println();
    


     
        Scanner s = new Scanner(System.in);
        int count = 0;
        boolean entered = false;


        while (!entered) {
            if (count % 2 == 0) {
                System.out.print("\r                                                                          press enter to continue...");
            } else {
                System.out.print("\r                                                                                                    ");
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

     

