package LAB1;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {
    public static void main(String[] args) {
        System.out.println(create());
        System.out.println(parse());
        System.out.println(with().getYear());
        System.out.println(withAdjuster());
        System.out.println(plus());
        System.out.println(minus());
        System.out.println(plusPeriod());
        System.out.println(isAfter());
        System.out.println(until());
    }

    static LocalDate create() {
        return LocalDate.of(2015, 6, 18); //2015-06-18
    }

    static LocalDate parse() {
        return LocalDate.parse("2015-06-18"); //2015-06-18
    }

    static LocalDate with() {
        LocalDate ld = DateAndTimes.LD_20150618;
        return ld.withYear(2015); //2015
    }

    static LocalDate withAdjuster() {
        LocalDate ld = DateAndTimes.LD_20150618;
        return ld.with(TemporalAdjusters.firstDayOfNextYear()); //2016-01-01
    }

    static LocalDate plus() {
        LocalDate ld = DateAndTimes.LD_20150618;
        return ld.plusMonths(10); //2016-04-18
    }

    static LocalDate minus() {
        LocalDate ld = DateAndTimes.LD_20150618;
        return ld.minusDays(10); //2015-06-08
    }

    static LocalDate plusPeriod() {
        LocalDate ld = DateAndTimes.LD_20150618;
        Period period = Period.of(1, 2, 3);
        return ld.plus(period); //2016-08-21
    }

    static boolean isAfter() {
        LocalDate ld = DateAndTimes.LD_20150618;
        LocalDate ld2 = DateAndTimes.LD_20150807;

        return ld2.isAfter(ld); //true
    }

    static Period until() {
        LocalDate ld = DateAndTimes.LD_20150618;
        LocalDate ld2 = DateAndTimes.LD_20150807;

        return ld.until(ld2); //P1M20D
    }
}

class DateAndTimes {
    public static final LocalDate LD_20150618 = LocalDate.of(2015, 6, 18);
    public static final LocalDate LD_20150807 = LocalDate.of(2015, 8, 7);
}
