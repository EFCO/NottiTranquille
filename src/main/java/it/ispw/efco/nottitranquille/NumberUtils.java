package it.ispw.efco.nottitranquille;

public class NumberUtils {
    public static boolean isLowerThen(final int number, final int uppermost) {
        return number < uppermost;
    }

    public static boolean isUpperThen(final int number, final int lowest) {
        return number > lowest;
    }

    public static boolean isFromTo(final int number, final int lowest, final int uppermost) {
        return number > lowest && number < uppermost;
    }

    public static boolean isPositive(final int number) {
        return number >= 0;
    }

    public static boolean isNegative(final int number) {
        return number < 0;
    }
}
