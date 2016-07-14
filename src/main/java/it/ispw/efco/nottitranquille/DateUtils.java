package it.ispw.efco.nottitranquille;

import org.joda.time.LocalDate;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     *
     *
     * @param n
     * @param dayOfWeek
     * @param month
     * @param year
     * @return
     */
    public static LocalDate getNthOfMonth(int n, int dayOfWeek, int month, int year) {
        if (n == -1) {
            return getNthOfMonth(0, dayOfWeek, month + 1, year);
        }
        final LocalDate compareDate = new LocalDate(year, month, 1);
        if (compareDate.getDayOfWeek() > dayOfWeek) {
            return compareDate.withDayOfWeek(dayOfWeek).plusDays(7 * n);
        } else {
            return compareDate.withDayOfWeek(dayOfWeek).plusDays(7 * (n - 1));
        }
    }
}
