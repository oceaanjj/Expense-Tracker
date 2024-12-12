package display;

public class UserMenu extends menu {
    @Override
    public void display() {
        System.out.println("[1] MY ACCOUNT");
        System.out.println("[2] EXPENSE TRACKER");
        System.out.println("[3] DASHBOARD");
        System.out.println("[4] LOGOUT");
    }
}
