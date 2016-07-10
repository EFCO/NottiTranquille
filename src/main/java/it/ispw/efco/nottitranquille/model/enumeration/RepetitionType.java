package it.ispw.efco.nottitranquille.model.enumeration;

import it.ispw.efco.nottitranquille.model.Price;

/**
 * Enumeration for type of {@link Price} repetition.
 *
 * <p>
 * Type that can be used:
 *  <li>{@link #EVERY_DAY}</li>
 *  <li>{@link #EVERY_WEEK}</li>
 *  <li>{@link #EVERY_MONTH}</li>
 *  <li>{@link #EVERY_YEAR}</li>
 *  <li>{@link #EVERY_WEEKEND}</li>
 *  <li>{@link #EVERY_WORKDAY}</li>
 *  <li>{@link #EVERY_NOT_WORKDAY}</li>
 * </p>
 *
 * @see Price
 * @see Price.Builder
 * @see Price.Builder#setRepetitionType(RepetitionType)
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public enum RepetitionType {

    /**
     * Repeat every day
     */
    EVERY_DAY,

    /**
     * Repeat every week
     */
    EVERY_WEEK,

    /**
     * Repeat every month
     */
    EVERY_MONTH,

    /**
     * Repeat every year
     */
    EVERY_YEAR,

    /**
     * Repeat every weekend
     */
    EVERY_WEEKEND,

    /**
     * Repeat every from monday to friday
     */
    EVERY_WORKDAY,

    /**
     * Repeat every saturday and sunday
     */
    EVERY_NO_WORKDAY
}