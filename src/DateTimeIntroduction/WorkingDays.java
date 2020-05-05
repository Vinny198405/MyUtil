package DateTimeIntroduction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Collections;
import java.util.HashSet;

public class WorkingDays implements TemporalAdjuster {
    private int workDays;
    private String[] dayOfWeek;
    private HashSet<String> dayOff;

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(temporal.toString());
        String newData = date.format(formatter);
        int current = 0;
        while (current != workDays) {
            if (checkDays(newData) || temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SATURDAY.getValue() || temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.FRIDAY.getValue()) {
                temporal = temporal.plus(1, ChronoUnit.DAYS);
            } else {
                current++;
                temporal = temporal.plus(1, ChronoUnit.DAYS);
            }
        }
        return temporal;
    }

    private boolean checkDays(String day) {
        return dayOff.contains(day);
    }

    private void setDayOff() {
        dayOff = new HashSet<>();
        Collections.addAll(dayOff, dayOfWeek);
    }

    WorkingDays(int workDays, String[] dayOfWeek) {
        this.workDays = workDays;
        this.dayOfWeek = dayOfWeek;
        setDayOff();
    }
}
