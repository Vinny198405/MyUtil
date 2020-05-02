package DateTimeIntroduction;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class DateTimeTestAppl {
    public static void main(String[] args) {
        LocalDate current = LocalDate.now();
// System.out.println(current);
// LocalDate birthDateASP = LocalDate.parse("1799-06-06");
// LocalDate barMitzvaASP = birthDateASP.plus(13, ChronoUnit.YEARS);
// LocalDate britASP = birthDateASP.plus(7, ChronoUnit.DAYS);
// DateTimeFormatter format1 = DateTimeFormatter.ISO_DATE;
// DateTimeFormatter format2 = DateTimeFormatter.ofPattern("eeee d/MMM/y", Locale.forLanguageTag("iw-IL"));
// System.out.println(birthDateASP+" Birthdate of Pushkin");
// System.out.println(barMitzvaASP.format(format1)+" Bar Mizva of Pushkin (ISO)");
// System.out.println(britASP.format(format2)+" Brit Mila of Pushkin (ofPattern)");
// ChronoUnit unit = ChronoUnit.MONTHS;
// System.out.printf("between %s and %s there are %d %s \n",
// birthDateASP,barMitzvaASP,
// unit.between(birthDateASP,barMitzvaASP),unit);
//        System.out.println(current.with(TemporalAdjusters.firstDayOfNextMonth())); //first day of next month
//        System.out.println(current.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY))); //first wednesday in current month
        System.out.println(current.with(new NextFriday13()));
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime zdt1 = ZonedDateTime.now(ZoneId.of("GMT+5"));
        System.out.println(zdt);
        System.out.println(zdt1);
//   for(String zoneID: ZoneId.getAvailableZoneIds()){
//       System.out.println(zoneID);
//   }

    }
}
