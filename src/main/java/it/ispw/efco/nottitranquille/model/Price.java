package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import javax.persistence.*;
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
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="priceType", discriminatorType = DiscriminatorType.STRING, length = 20)
public abstract class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The interval in which Price is valid
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentInterval")
    @Columns(columns = { @Column(name = "startDate"), @Column(name = "endDate") })
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
    @ElementCollection(targetClass = Day.class)
    @Enumerated
    protected List<Day> days;

    /**
     * The type of repetition
     */
    protected RepetitionType repetitionType;

    /**
     * The value of the Price
     */
    protected double value;

    /**
     * Default constructor
     */
    protected Price() {
    }

    /**
     * Builder constructor
     */
    protected Price(Builder builder) {
        this.interval = builder.interval;
        this.times = builder.times;
        this.occurrences = builder.occurrences;
        this.repetitionType = builder.repetitionType;
        this.days = builder.days;
        this.value = builder.value;

        if (builder.value < 0) {
            throw new IllegalStateException("Value must be set greater than zero!");
        }

        if (builder.repetitionType != null) {
            if (builder.repetitionType == RepetitionType.EVERY_DAY) {
                if (builder.days != null) {
                    throw new IllegalStateException("If repetition type is EVERY_DAYS it is not legal set days!");
                }
            } else if (builder.repetitionType == RepetitionType.EVERY_WORKDAY || builder.repetitionType == RepetitionType.EVERY_NOT_WORKDAY || builder.repetitionType == RepetitionType.EVERY_WEEKEND) {
                if (builder.days != null) {
                    throw new IllegalStateException("If repetition type is EVERY_WORKDAY or EVERY_NOT_WORKDAY it is not legal set days!");
                }

                if (builder.times != -1) {
                    throw new IllegalStateException("If repetition type is EVERY_WORKDAY or EVERY_NOT_WORKDAY it is not legal set repetition times!");
                }

            } else if (builder.repetitionType == RepetitionType.EVERY_WEEK || builder.repetitionType == RepetitionType.EVERY_MONTH || builder.repetitionType == RepetitionType.EVERY_YEAR) {
                if (builder.days == null) {
                    throw new IllegalStateException("If repetition is EVERY_WEEK, EVERY_MONTH or EVERY_YEAR, days is mandatory!");
                }

                // Sets default times to 1
                if (builder.times == -1) {
                    this.times = 1;
                }
            }
        }

        if (builder.interval != null) {
            if (builder.occurrences != -1) {
                throw new IllegalStateException("If there is an interval occurrences is useless!");
            }
        } else {
            // Sets default interval from now to forever
            this.interval = new Interval(DateTime.now(), new DateTime(9999, 1, 1, 0, 0, 0, DateTimeZone.UTC ));
        }
    }

    /**
     * Gets Price's id of persistent system.
     *
     * @return the id into persistent system
     */
    public Long getId() {
        return this.id;
    }

    /**
	 * Shows the current Price's value decorating with all decorator applied.
     *
	 * @return the Price's value
	 */
	public abstract double showPrice();

    /**
     * Updates Price's state with the state of the Price provided.
     *
     * @param priceToUpdate the new price
     */
    public void update(Price priceToUpdate) {
        this.interval = priceToUpdate.interval;
        this.times = priceToUpdate.times;
        this.occurrences = priceToUpdate.occurrences;
        this.repetitionType = priceToUpdate.repetitionType;
        this.days = priceToUpdate.days;
        this.value = priceToUpdate.value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "interval=" + interval +
                ", times=" + times +
                ", occurrences=" + occurrences +
                ", days=" + days +
                ", repetitionType=" + repetitionType +
                ", value=" + value +
                '}';
    }

    /**
     * Builder for {@link Price} object.
     *
     * @param <T>   price
     * @param <B>   builder
     */
    protected static abstract class Builder<T extends Price, B extends Builder<T, B>> {

        /**
         * This object
         */
        protected B thisObject;

        /**
         * The interval in which Price is valid
         */
        protected Interval interval;

        /**
         * The number of times the Price must be repeated
         */
        protected int times = -1;

        /**
         * The number of the occurrences the Price must be repeated
         */
        protected int occurrences = -1;

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
        protected double value = -1;

        /**
         * Abstract method that has to call the concrete object constructor passing builder instance.
         * <br>
         * Calls builder constructor.
         * <p>
         * Example:
         * </p>
         *
         * <pre>
         * &#064;Override
         * protected BasePrice createObject() {
         *      return new BasePrice(this);
         * }
         * </pre>
         *
         * @return the new object
         */
        protected abstract T createObject();

        /**
         * Abstract method that has to refer to concrete object.
         * <p>
         * Example:
         * </p>
         *
         * <pre>
         * &#064;Override
         * protected Builder thisObject() {
         *      return this;
         * }
         * </pre>
         *
         * @return this object
         */
        protected abstract B thisObject();

        /**
         * Default constructor.
         */
        public Builder() {
            thisObject = thisObject();
        }

        /**
         * Sets the interval in which the Price is valid.
         *
         * @param interval  the interval
         * @return  the builder itself
         */
        public B setInterval(Interval interval) {
            this.interval = interval;
            return thisObject;
        }

        /**
         * Sets the number of times that the Price is valid in a certain {@link Interval}.
         *
         * @param times the times
         * @return  the builder itself
         */
        public B setTimes(int times) {
            this.times = times;
            return thisObject;
        }

        /**
         * Sets the number of the occurrences that the price has to have until is yet valid.
         *
         * @param occurrences   the occurrences
         * @return  the builder itself
         */
        public B setOccurrences(int occurrences) {
            this.occurrences = occurrences;
            return thisObject;
        }

        /**
         * Sets the days in which the Price is repeated.
         *
         * @param days  the days
         * @return  the builder itself
         */
        public B setDays(List<Day> days) {
            this.days = days;
            return thisObject;
        }

        /**
         * Sets the type of repetition.
         *
         * @param repetitionType    the repetition type
         * @return  the builder itself
         */
        public B setRepetitionType(RepetitionType repetitionType) {
            this.repetitionType = repetitionType;
            return thisObject;
        }

        /**
         * Sets the Price's value.
         *
         * @param price the value
         * @return  the builder itself
         */
        public B setPrice(double price) {
            this.value = price;
            return thisObject;
        }

        /**
         * Builds object calling object constructor.
         *
         * @return  the new object
         * @throws  IllegalStateException if TODO
         */
        public T build() {
            return createObject();
        }
    }
}