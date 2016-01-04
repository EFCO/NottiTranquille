package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.joda.time.Interval;

import java.util.List;

/**
 * Price provides the base class for all the price system.
 *
 * The price system is based on two Design Pattern, the Decorator Patter and the Builder Pattern.
 * Price is the Component to decorate, the concrete component is {@link BasePrice}, the decorator is
 * {@link Decorator} that has two concrete decorators {@link Discount} and {@link Fee}. Moreover {@link Discount} and
 * {@link Fee} are two abstract classes which materialize into {@link FixDiscount}, {@link PercentageDiscount},
 * {@link FixFee} and {@link PercentageFee}.
 *
 * <p>
 *     The whole Price System is instantiable by {@link Builder} that allows more readability and avoid telescoping constructor.
 * </p>
 *
 * @see Price
 * @see BasePrice
 * @see Decorator
 * @see Discount
 * @see FixDiscount
 * @see PercentageDiscount
 * @see Fee
 * @see FixFee
 * @see PercentageFee
 * @see Price.Builder
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public abstract class Price {

	/**
	 * The interval in which Price is valid
	 */
    protected Interval interval;

	/**
	 * The number of times the Price must be repeated
	 */
    protected int times;

	/**
	 * The number of the occurrences the Price must be repeated
	 */
    protected int occurrences;

	/**
	 * The list of the days in which the Price must be repeated
	 */
    protected List<Day> days;

    /**
     * The type of repetition
     */
    protected RepetitionType repetitionType;

    /**
     * The value of the Price
     */
    protected double price;

    /**
     * Default constructor
     */
    protected Price() {
    }

    /**
	 * Shows the current Price's value decorating with all decorator applied.
     *
	 * @return the Price's value
	 */
	public abstract double showPrice();

    /**
     * TODO
     *
     * @param <T>
     * @param <B>
     */
    protected static abstract class Builder<T extends Price, B extends Builder<T, B>> {

        /**
         * TODO
         */
        protected T object;

        /**
         * TODO
         */
        protected B thisObject;

        /**
         *
         * @return
         */
        protected abstract T createObject();

        /**
         *
         * @return
         */
        protected abstract B thisObject();

        /**
         *
         */
        public Builder() {
            object = createObject();
            thisObject = thisObject();
        }

        /**
         * Sets the interval in which the Price is valid.
         *
         * @param interval  the interval
         * @return  the builder itself
         */
        public B setInterval(Interval interval) {
            object.interval = interval;
            return thisObject;
        }

        /**
         * Sets the number of times that the Price is valid in a certain {@link Interval}.
         *
         * @param times the times
         * @return  the builder itself
         */
        public B setTimes(int times) {
            object.times = times;
            return thisObject;
        }

        /**
         * Sets the number of the occurrences that the price has to have until is yet valid.
         *
         * @param occurrences   the occurrences
         * @return  the builder itself
         */
        public B setOccurrences(int occurrences) {
            object.occurrences = occurrences;
            return thisObject;
        }

        /**
         * Sets the days in which the Price is repeated.
         *
         * @param days  the days
         * @return  the builder itself
         */
        public B setDays(List<Day> days) {
            object.days = days;
            return thisObject;
        }

        /**
         * Sets the type of repetition.
         *
         * @param repetitionType    the repetition type
         * @return  the builder itself
         */
        public B setRepetitionType(RepetitionType repetitionType) {
            object.repetitionType = repetitionType;
            return thisObject;
        }

        /**
         * Sets the Price's value.
         *
         * @param price the value
         * @return  the builder itself
         */
        public B setPrice(double price) {
            object.price = price;
            return thisObject;
        }

        /**
         *
         *
         * @return
         */
        public T build() {
            return object;
        }
    }
}