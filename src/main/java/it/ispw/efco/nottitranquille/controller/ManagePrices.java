package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.*;

/**
 * ManagesPrices Controller.
 *
 * This Use Case shows the process by which a Manager can manage the {@link Price} of his {@link Location} and payable
 * {@link Service}s.
 * <p>
 * For {@link Location}, he can set a {@link BasePrice} for a night or a more complex structure with {@link Discount}s and
 * {@link Fee}s addable in fix or percentage mode (added either as fixed or percentage modifier). He can also plan the
 * price trend during the week and during festivity.
 * </p>
 * <p>
 * For {@link Service}, he can only add a default price.
 * </p>
 *
 * @see Price
 * @see BasePrice
 * @see Discount
 * @see FixDiscount
 * @see PercentageDiscount
 * @see Fee
 * @see FixFee
 * @see PercentageFee
 * @see Service
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ManagePrices {

	/**
     * Adds a {@link BasePrice} with an {@link Interval}, in which the price is valid, to a {@link Location}.
     *
	 * @param location  the location
	 * @param interval  the interval in which the price must be considered
	 * @param basePrice the price
	 */
	public static void addBasePrice(Location location, Interval interval, double basePrice) {
        BasePrice price = new BasePrice.Builder()
                .setPrice(basePrice)
                .setInterval(interval)
                .build();

		location.getPrices().addPrice(price);
	}

	/**
     * Adds a {@link PercentageFee} with an {@link Interval}, in which the fee is valid, to a {@link Location}.
     *
     * @param location  the location
	 * @param interval  the interval in which the fee must be considered
	 * @param fee   the fee to apply
	 */
	public static void addPercentageFee(Location location, Interval interval, double fee) {
        PercentageFee price = new PercentageFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link PercentageFee} that is valid from a certain date to another one to a {@link Location}.
     *
     * @param location  the location
     * @param starDate  the date from the fee must be considered
     * @param endDate   the date until the fee must be considered
     * @param fee   the fee to apply
     */
	public static void addPercentageFee(Location location, DateTime starDate, DateTime endDate, double fee) {
        Interval interval = new Interval(starDate, endDate);

        PercentageFee price = new PercentageFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link FixFee} with an {@link Interval}, in which the fee is valid, to a {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the fee must be considered
     * @param fee   the fee to apply
     */
    public static void addFixFee(Location location, Interval interval, double fee) {
        FixFee price = new FixFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link FixFee} that is valid from a certain date to another one to a {@link Location}.
     *
     * @param location  the location
     * @param starDate  the date from the fee must be considered
     * @param endDate   the date until the fee must be considered
     * @param fee   the fee to apply
     */
    public static void addFixFee(Location location, DateTime starDate, DateTime endDate, double fee) {
        Interval interval = new Interval(starDate, endDate);

        FixFee price = new FixFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link PercentageDiscount} with an {@link Interval}, in which the fee is valid, to a {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the fee must be considered
     * @param discount  the discount to apply
     */
    public static void addPercentageDiscount(Location location, Interval interval, double discount) {
        PercentageDiscount price = new PercentageDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link PercentageDiscount} that is valid from a certain date to another one to a {@link Location}.
     *
     * @param location  the location
     * @param starDate  the date from the fee must be considered
     * @param endDate   the date until the fee must be considered
     * @param discount  the discount to apply
     */
    public static void addPercentageDiscount(Location location, DateTime starDate, DateTime endDate, double discount) {
        Interval interval = new Interval(starDate, endDate);

        PercentageDiscount price = new PercentageDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link FixDiscount} with an {@link Interval}, in which the fee is valid, to a {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the fee must be considered
     * @param discount  the discount to apply
     */
    public static void addFixDiscount(Location location, Interval interval, double discount) {
        FixDiscount price = new FixDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link FixDiscount} that is valid from a certain date to another one to a {@link Location}.
     *
     * @param location  the location
     * @param starDate  the date from the fee must be considered
     * @param endDate   the date until the fee must be considered
     * @param discount  the discount
     */
    public static void addFixDiscount(Location location, DateTime starDate, DateTime endDate, double discount) {
        Interval interval = new Interval(starDate, endDate);

        FixDiscount price = new FixDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link BasePrice} (always valid) to a {@link Service}.
     *
	 * @param service   the service
	 * @param servicePrice  the price
	 */
	public static void addServicePrice(Service service, double servicePrice) {
		BasePrice price = new BasePrice.Builder()
                .setPrice(servicePrice)
                .build();

        service.setPrice(price);
	}

    /**
     * Adds a {@link BasePrice} that repeats itself in an {@link Interval}, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the price must be considered
	 * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param basePrice the price
     */
	public static void addBasePriceWithRepetition(Location location, Interval interval, RepetitionType repetitionType, int times, List<Day> days, double basePrice) {
        BasePrice price = new BasePrice.Builder()
                .setPrice(basePrice)
                .setInterval(interval)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link BasePrice} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param occurrences   the number of repetition
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param basePrice the price
     */
	public static void addBasePriceWithRepetition(Location location, int occurrences, RepetitionType repetitionType, int times, List<Day> days, double basePrice) {
        BasePrice price = new BasePrice.Builder()
                .setPrice(basePrice)
                .setOccurrences(occurrences)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link FixFee} that repeats itself in an {@link Interval}, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the price must be considered
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param fee   the fee
     */
	public static void addFixFeeWithRepetition(Location location, Interval interval, RepetitionType repetitionType, int times, List<Day> days, double fee) {
        FixFee price = new FixFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link FixFee} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param occurrences   the number of repetition
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param fee   the fee
     */
	public static void addFixFeeWithRepetition(Location location, int occurrences, RepetitionType repetitionType, int times, List<Day> days, double fee) {
        FixFee price = new FixFee.Builder()
                .setFee(fee)
                .setOccurrences(occurrences)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link PercentageFee} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the price must be considered
     * @param repetitionType    the type of Repetition
     * @param times the number of times
     * @param days  the days
     * @param fee the fee
     */
    public static void addPercentageFeeWithRepetition(Location location, Interval interval, RepetitionType repetitionType, int times, List<Day> days, double fee) {
        PercentageFee price = new PercentageFee.Builder()
                .setFee(fee)
                .setInterval(interval)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link PercentageFee} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param occurrences   the number of repetition
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param fee   the fee
     */
    public static void addPercentageFeeWithRepetition(Location location, int occurrences, RepetitionType repetitionType, int times, List<Day> days, double fee) {
        PercentageFee price = new PercentageFee.Builder()
                .setFee(fee)
                .setOccurrences(occurrences)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link FixDiscount} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the price must be considered
     * @param repetitionType    the type of Repetition
     * @param times the number of times
     * @param days  the days
     * @param discount the discount
     */
    public static void addFixDiscountWithRepetition(Location location, Interval interval, RepetitionType repetitionType, int times, List<Day> days, double discount) {
        FixDiscount price = new FixDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link FixDiscount} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param occurrences   the number of repetition
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param discount   the discount
     */
    public static void addFixDiscountWithRepetition(Location location, int occurrences, RepetitionType repetitionType, int times, List<Day> days, double discount) {
        FixDiscount price = new FixDiscount.Builder()
                .setDiscount(discount)
                .setOccurrences(occurrences)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
    }

    /**
     * Adds a {@link PercentageDiscount} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param interval  the interval in which the price must be considered
     * @param repetitionType    the type of Repetition
     * @param times the number of times
     * @param days  the days
     * @param discount the discount
     */
	public static void addPercentageDiscountWithRepetition(Location location, Interval interval, RepetitionType repetitionType, int times, List<Day> days, double discount) {
        PercentageDiscount price = new PercentageDiscount.Builder()
                .setDiscount(discount)
                .setInterval(interval)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

    /**
     * Adds a {@link PercentageDiscount} that repeats itself such occurrences, in such {@link Day}s, such times to a
     * {@link Location}.
     *
     * @param location  the location
     * @param occurrences   the number of repetition
     * @param repetitionType    the type of repetition
     * @param times the number of times
     * @param days  the days
     * @param discount   the discount
     */
	public static void addPercentageDiscountWithRepetition(Location location, int occurrences, RepetitionType repetitionType, int times, List<Day> days, double discount) {
        PercentageDiscount price = new PercentageDiscount.Builder()
                .setDiscount(discount)
                .setOccurrences(occurrences)
                .setRepetitionType(repetitionType)
                .setTimes(times)
                .setDays(days)
                .build();

        location.getPrices().addPrice(price);
	}

	/**
	 * @param location 
	 * @param price
	 */
	public static void removePrice(Location location, Price price) {
		location.getPrices().removePrice(price);
	}

	/**
	 * @param location 
	 * @param price 
	 * @param basePrice
	 */
	public static void updateBasePrice(Location location, Price price, double basePrice) {
        // TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param interval
	 */
	public static void updateInterval(Location location, Price price, Interval interval) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param fee
	 */
	public static void updateFee(Location location, Price price, double fee) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param discount
	 */
	public static void updateDiscount(Location location, Price price, double discount) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param servicePrice
	 */
	public static void updateServicePrice(Location location, Price price, double servicePrice) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param repetitionType
	 */
	public static void updateRepetitionType(Location location, Price price, RepetitionType repetitionType) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param times
	 */
	public static void updateTimes(Location location, Price price, int times) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param days
	 */
	public static void updateDays(Location location, Price price, List<Day> days) {
		// TODO implement here
	}

	/**
	 * @param location 
	 * @param price 
	 * @param occurencies
	 */
	public static void updateOccurencies(Location location, Price price, int occurencies) {
		// TODO implement here
	}

}