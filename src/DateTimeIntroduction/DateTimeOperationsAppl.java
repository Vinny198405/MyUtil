package DateTimeIntroduction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

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
        LocalDate birthDate = formatCheck(strBirthDate);
        LocalDate date = LocalDate.now();

        long years = ChronoUnit.YEARS.between(birthDate, date);
        date = date.minus(years, ChronoUnit.YEARS);

        long months = ChronoUnit.MONTHS.between(birthDate, date);
        date = date.minus(months, ChronoUnit.MONTHS);

        long days = ChronoUnit.DAYS.between(birthDate, date);

        System.out.printf("Years: %d; Months: %d; Days: %d; from date: %s\n", years, months, days, strBirthDate);
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
        for (String zoneID : ZoneId.getAvailableZoneIds()) {
            if (zoneID.toLowerCase().contains(zonePattern.toLowerCase())) {
                System.out.println(ZonedDateTime.now(ZoneId.of(zoneID)));
            }
        }
    }
}
