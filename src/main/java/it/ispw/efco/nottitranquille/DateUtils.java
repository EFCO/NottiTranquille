package it.ispw.efco.nottitranquille;

import org.joda.time.LocalDate;

/**
 * {@inheritDoc}
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * Gets the {@link LocalDate} of a nth day of week in a specific month and year.
     *
     * @param n the nth
     * @param dayOfWeek the day of week
     * @param month the month
     * @param year the year
     * @return the date of the nth day of week
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
