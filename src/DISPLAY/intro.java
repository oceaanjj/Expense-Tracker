package display;

import java.io.IOException;
import java.util.Scanner;

public class intro extends menu {
    @Override
    public void display() {
        
        System.out.println("PROGRAMMED BY : ");
        System.out.println("Esquerra, Jovilyn F.");
        System.out.println("Gerolia, Bryan James");
        System.out.println("Robles, Rain Louie");

        System.out.println(" _____                                  _____               _             ");
        System.out.println("| ____|_  ___ __   ___ _ __  ___  ___  |_   _| __ __ _  ___| | _____ _ __ ");
        System.out.println("|  _| \\ \\/ / '_ \\ / _ \\ '_ \\/ __|/ _ \\   | || '__/ _ |/ __| |/ / _ \\ '__|");
        System.out.println("| |___ >  <| |_) |  __/ | | \\__ \\  __/   | || | | (_| | (__|   <  __/ |   ");
        System.out.println("|_____/_/\\_\\ .__/ \\___|_| |_|___/\\___|   |_||_|  \\__,_|\\___|_|\\_\\___|_|   ");
        System.out.println("           |_|                                                             ");


     
        // Blinking effect loop
        Scanner s = new Scanner(System.in);
        int count = 0;
        boolean entered = false;

        // Blinking effect loop
        while (!entered) {
            if (count % 2 == 0) {
                System.out.print("\rPress Enter to continue...");
            } else {
                System.out.print("\r                            ");
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

     

