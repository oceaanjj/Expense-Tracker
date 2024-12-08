package Displays;

public class loading {
    public void loading() {
        try {
            System.out.println("\t\t\t  Loading:");
            for (int i = 0; i <= 100; i++) {
                int progressLength = i / 2;
                String progress = "╸".repeat(progressLength);
                System.out.print("\r" + i + "%  " + progress);
                Thread.sleep(50);
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
