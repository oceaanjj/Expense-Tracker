package ExpenseTracker;

public class register extends Database {
    private String electricity;
    private String water;
    private String rent = "N/A";
    private String Internet = "N/A";

    void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    void setWater(String water) {
        this.water = water;
    }

    void setRent(String rent) {
        this.rent = rent;
    }

    void setInternet(String internet) {
        Internet = internet;
    }

    String getElectricity() {
        return electricity;
    }

    String getWater() {
        return water;
    }

    String getRent() {
        return rent;
    }

    String getInternet() {
        return Internet;
    }

    

}
