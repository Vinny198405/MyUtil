package PrintCalendarApplication;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarAppl {
    private static  Locale locale = Locale.UK;
    private static int columnWidth = 4;

    public static void main(String[] args) {
        int[] yearMonth;
        try {
            yearMonth = getYearMonth(args);
            printCalendar(yearMonth[0], yearMonth[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCalendar(int year, int month) {
        printTitle(year, month);
        printDaysOfWeek();
        printDates(year, month);
    }

    private static void printDates(int year, int month) {
        int firstDayOfWeek = getFirstDayOfWeek(year, month);

        printOffset((firstDayOfWeek - 1) * columnWidth);
        int lastDay = getLastDay(year, month);
        for(int day = 1; day <= lastDay; day++) {
            //%4d
            System.out.printf("%" + columnWidth + "d", day);
            firstDayOfWeek++;
            if(firstDayOfWeek == 8) {
                firstDayOfWeek = 1;
                System.out.println();
            }
        }
    }

    private static int getLastDay(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    private static void printOffset(int offset) {
        //JDK  11+
        String spaces = " ".repeat(offset);
        //JDK before 11
//		StringBuilder spacesBuilder = new StringBuilder();
//		for (int i = 0; i < offset; i++) {
//			spacesBuilder.append(' ');
//		}
//		String spaces = spacesBuilder.toString();
        System.out.print(spaces);
    }

    private static int getFirstDayOfWeek(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        return date.getDayOfWeek().getValue();
    }

    private static void printDaysOfWeek() {
        printOffset(columnWidth / 2);
        for(DayOfWeek dayOfWeek: DayOfWeek.values()) {
            System.out.print
                    (dayOfWeek.getDisplayName(TextStyle.SHORT, locale) + " ");
        }
        System.out.println();
    }

    private static void printTitle(int year, int month) {
        Month monthConst = Month.of(month);
        System.out.printf("\t%s, %d\n",
                monthConst.getDisplayName(TextStyle.FULL, locale), year);
    }

    /**
     *
     * @param args : if args.length < 2, year - current year ,
     *  month - current month
     * @return [0] - number of year,
     *  [1] - number of month
     */
    private static int[] getYearMonth(String[] args) {

        if (args.length < 2) {
            return getCurrentYearMonth();
        }

        int year  = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        return new int[] {year, month};
    }

    private static int[] getCurrentYearMonth() {
        LocalDate current = LocalDate.now();
        return new int[] {current.getYear(), current.getMonthValue()};
    }
}
