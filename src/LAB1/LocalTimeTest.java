package LAB1;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalTimeTest {
    public static void main(String[] args) {
        System.out.println(localTimeOfHourToMinute());
        System.out.println(localTimeOfHourToNanoSec());
        System.out.println(localTimeParse());
        System.out.println(localTimeWith());
        System.out.println(localTimePlus());
        System.out.println(localTimeMinus());
        System.out.println(localTimeMinusDuration());
        System.out.println(localDateIsBefore());
        System.out.println(localTimeTruncatedTo());
    }

    static LocalTime localTimeOfHourToMinute() {

        return LocalTime.of(23, 7); //23:07
    }

    static LocalTime localTimeOfHourToNanoSec() {

        return LocalTime.of(23, 7, 3, 100_000_000); //23:07:03.100
    }

    static LocalTime localTimeParse() {

        return LocalTime.parse("23:07:03.100"); //23:07:03.100
    }

    static LocalTime localTimeWith() {
        LocalTime lt = DateAndTimes.LT_23073050;

        return lt.withHour(21); //21:07:30.500
    }

    static LocalTime localTimePlus() {
        LocalTime lt = DateAndTimes.LT_23073050;

        return lt.plusMinutes(30); //23:37:30.500
    }

    static LocalTime localTimeMinus() {
        LocalTime lt = DateAndTimes.LT_23073050;

        return lt.minusHours(3); //20:07:30.500
    }

    static LocalTime localTimeMinusDuration() {
        LocalTime lt = DateAndTimes.LT_23073050;

        Duration duration = Duration.ofHours(3).plusMinutes(30).plusSeconds(20).plusNanos(200_000_000);
        return lt.minus(duration); //19:37:10.300
    }

    static boolean localDateIsBefore() {
        LocalTime lt = DateAndTimes.LT_23073050;
        LocalTime lt2 = DateAndTimes.LT_12100000;

        return lt2.isBefore(lt); //true
    }

    static LocalTime localTimeTruncatedTo() {
        LocalTime lt = DateAndTimes.LT_23073050;

        return lt.truncatedTo(ChronoUnit.MINUTES); //23:07
    }

    static class DateAndTimes {
        public static final LocalTime LT_23073050 = LocalTime.of(23, 7, 30, 500_000_000);
        public static final LocalTime LT_12100000 = LocalTime.of(12, 10);
    }
}
