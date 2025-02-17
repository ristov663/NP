package LAB1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        System.out.println(localDateTimeOf());
        System.out.println(localDateTimeParse());
        System.out.println(localTimeWith());
        System.out.println(localDatePlusMinus());
        System.out.println(localDateTimeFormat());
        System.out.println(toLocalDateAndTime());
        System.out.println(toLocalDateTime());
    }

    static LocalDateTime localDateTimeOf() {

        return LocalDateTime.of(2015, 6, 20, 23, 7, 30); //2015-06-20T23:07:30
    }

    static LocalDateTime localDateTimeParse() {

        return LocalDateTime.parse("2015-06-20T23:07:30"); //2015-06-20T23:07:30
    }

    static LocalDateTime localTimeWith() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;

        return ldt.with(TemporalAdjusters.firstDayOfNextMonth()).truncatedTo(ChronoUnit.HOURS); //2015-07-01T23:00
    }

    static LocalDateTime localDatePlusMinus() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;

        return ldt.plusMonths(10).minusHours(5); //2016-04-18T18:07:30.500
    }

    static String localDateTimeFormat() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        return ldt.format(formatter); //2015_06_18_23_07_30
    }

    static String toLocalDateAndTime() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;

        LocalDate localDate = ldt.toLocalDate();
        LocalTime localTime = ldt.toLocalTime();
        return localDate.toString() + localTime.toString(); //2015-06-1823:07:30.500
    }

    static String toLocalDateTime() {
        LocalDate ld = DateAndTimes.LD_20150618;
        LocalTime lt = DateAndTimes.LT_23073050;

        LocalDateTime localDateTime1 = ld.atTime(lt);
        LocalDateTime localDateTime2 = lt.atDate(ld);
        return localDateTime1.toString() + " " + localDateTime2.toString(); //2015-06-18T23:07:30.500 2015-06-18T23:07:30.500

    }

    static class DateAndTimes {
        public static final LocalDate LD_20150618 = LocalDate.of(2015, 6, 18);
        public static final LocalTime LT_23073050 = LocalTime.of(23, 7, 30, 500000000);
        public static final LocalDateTime LDT_20150618_23073050 = LocalDateTime.of(2015, 6, 18, 23, 7, 30, 500000000);
    }
}
