package DISPLAY;

public class intro extends menu {
    public void display() {
        
            System.out.println(" _____                                  _____               _             ");
            System.out.println("| ____|_  ___ __   ___ _ __  ___  ___  |_   _| __ __ _  ___| | _____ _ __ ");
            System.out.println("|  _| \\ \\/ / '_ \\ / _ \\ '_ \\/ __|/ _ \\   | || '__/ _` |/ __| |/ / _ \\ '__|");
            System.out.println("| |___ >  <| |_) |  __/ | | \\__ \\  __/   | || | | (_| | (__|   <  __/ |   ");
            System.out.println("|_____/_/\\_\\ .__/ \\___|_| |_|___/\\___|   |_||_|  \\__,_|\\___|_|\\_\\___|_|   ");
            System.out.println("           |_|                                                             ");

            int count = 0;
            boolean entered = false;
            while (!entered) {
            //blinking text effect pero hindi maka enter
            if (count % 2 == 0) {
                System.out.print("\rclick enter to continue...");
            } else {
                System.out.print("\r                           ");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;  
            }
        }
    }
     

