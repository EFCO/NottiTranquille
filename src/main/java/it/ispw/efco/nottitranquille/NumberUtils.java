package it.ispw.efco.nottitranquille;

public class NumberUtils {
    /**
     * Checks if the given number is between a lower and a upper bound
     *
     * @param number the number to check
     * @param lowest the lower bound
     * @param uppermost the upper bound
     * @return true if is between, false otherwise
     */
    public static boolean isBetween(final int number, final int lowest, final int uppermost) {
        return number > lowest && number < uppermost;
    }
}
