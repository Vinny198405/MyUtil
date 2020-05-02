package DateTimeIntroduction;

import java.time.DayOfWeek;
import java.time.temporal.*;

public class NextFriday13 implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal res = getCurrent13(temporal);

        while (res.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
            res = res.plus(1, ChronoUnit.MONTHS);
        }
        return res;
    }

    private Temporal getCurrent13(Temporal temporal) {
        int dayOfMonth = temporal.get(ChronoField.DAY_OF_MONTH);
        Temporal res = temporal;
        if (dayOfMonth >= 13) {
            res = res.plus(1, ChronoUnit.MONTHS);
        }
        res = res.with(ChronoField.DAY_OF_MONTH, 13);
        return res;
    }
}
