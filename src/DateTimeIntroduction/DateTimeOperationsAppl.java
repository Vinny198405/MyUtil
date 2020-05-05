package DateTimeIntroduction;

import java.time.*;
import java.time.format.*;

public class DateTimeOperationsAppl {
    public static void main(String[] args) {
        LocalDate current = LocalDate.now();
        String[] dayOfWeek = {"2020-03-10", "2020-03-11", "2020-04-09", "2020-04-10", "2020-04-15",
                "2020-04-16", "2020-04-29", "2020-05-29", "2020-09-19", "2020-09-20", "2020-09-28",
                "2020-10-04", "2020-10-10", "2020-11-11", "2020-11-18"};

        System.out.println("Next day: " + current.with(new WorkingDays(20, dayOfWeek)));

        displayTimezoneId("CaNada");

        displayCompleteAge("1799-06-06");
        displayCompleteAge("06/06/1799");
        displayCompleteAge("06.06.1799");
    }

    private static void displayCompleteAge(String strBirthDate) {
        LocalDate start = formatCheck(strBirthDate);
        LocalDate end = LocalDate.now();

        Period period = Period.between(start, end);
        System.out.printf("Years: %d; Months: %d; Days: %d; from date: %s\n", period.getYears(), period.getMonths(), period.getDays(), strBirthDate);
    }

    private static LocalDate formatCheck(String strBirthDate) {
        DateTimeFormatter format1 = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter[] formats = {format1, format2};
        int count = 0;

        LocalDate birthDate = null;
        while (birthDate == null) {
            try {
                birthDate = LocalDate.parse(strBirthDate, formats[count++]);
            } catch (DateTimeParseException e) {
                if (count >= formats.length) throw e;
            }
        }
        return birthDate;
    }

    private static void displayTimezoneId(String zonePattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mma VV");
        for (String zoneID : ZoneId.getAvailableZoneIds()) {
            if (zoneID.toLowerCase().contains(zonePattern.toLowerCase())) {
                ZonedDateTime ztm = ZonedDateTime.now(ZoneId.of(zoneID));
                System.out.println(ztm.format(format));
            }
        }
    }
}
