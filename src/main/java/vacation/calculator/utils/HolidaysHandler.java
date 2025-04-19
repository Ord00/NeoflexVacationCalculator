package vacation.calculator.utils;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public final class HolidaysHandler {

    private static final Set<MonthDay> COMMON_HOLIDAYS = initCommonHolidays();
    private static final Set<LocalDate> SPECIAL_HOLIDAYS = new HashSet<>();

    private HolidaysHandler() {}

    public static Set<MonthDay> getCommonHolidays() {
        return new HashSet<>(COMMON_HOLIDAYS);
    }

    public static Set<LocalDate> getSpecialHolidays() {
        return new HashSet<>(SPECIAL_HOLIDAYS);
    }

    private static Set<MonthDay> initCommonHolidays() {

        Set<MonthDay> holidays = new HashSet<>();

        addDateRange(holidays, 1, 1, 8);
        addSingleDate(holidays, 2, 23);
        addSingleDate(holidays, 3, 8);
        addSingleDate(holidays, 5, 1);
        addSingleDate(holidays, 5, 9);
        addSingleDate(holidays, 6, 12);
        addSingleDate(holidays, 11, 4);

        return Collections.unmodifiableSet(holidays);

    }

    private static void addDateRange(Set<MonthDay> set,
                                     int month,
                                     int startDay,
                                     int endDay) {

        IntStream.rangeClosed(startDay, endDay)
                .forEach(day -> set.add(MonthDay.of(month, day)));

    }

    private static void addSingleDate(Set<MonthDay> set,
                                      int month,
                                      int day) {

        set.add(MonthDay.of(month, day));

    }

    public static void addSpecialHoliday(LocalDate date) {

        SPECIAL_HOLIDAYS.add(date);

    }

    public static void addSpecialHolidaysRange(int year,
                                               int month,
                                               int startDay,
                                               int endDay) {

        IntStream.rangeClosed(startDay, endDay)
                .forEach(day -> SPECIAL_HOLIDAYS.add(LocalDate.of(year, month, day)));

    }

    public static boolean isHoliday(LocalDate date) {

        return COMMON_HOLIDAYS.contains(MonthDay.from(date)) ||
                SPECIAL_HOLIDAYS.contains(date);

    }

}
