package ExpenseTracker;

public class loading {
    public void loading() {
        try {
            System.out.println("\t\t\t\t\t\tLoading:");
            for (int i = 0; i <= 100; i += 2) {
                String progress = "╸".repeat(i);
                System.out.print("\r" + i + "%  " + progress );
                Thread.sleep(100);
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
