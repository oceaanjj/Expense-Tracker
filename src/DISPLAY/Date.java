package display;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;   

public class Date {

    public void date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + dtf.format(now));
    }
    public boolean isFirstDayOfMonth(LocalDate date) {
        return date.getDayOfMonth() == 1;
    }
}

