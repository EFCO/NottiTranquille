package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.dao.PriceDao;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Location {

    /**
     * Default constructor
     */
    public Location() {
    }

    /**
     *
     */
    private Prices prices;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private Integer numberOfRooms;

    /**
     *
     */
    private Integer numberOfBathrooms;

    /**
     *
     */
    private Integer maxGuestsNumber;

    /**
     *
     */
    private Integer numberOfBeds;

    /**
     *
     */
    private List<String> photos;

    /**
     *
     */
    private Integer numberOfBedrooms;

    /**
     * @param date
     * @return
     */
    public double getPrice(DateTime date) {
        return getPrice(new Interval(date, date));

    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public double getPrice(DateTime startDate, DateTime endDate) {
        System.out.print(startDate.toDate());
        System.out.print(endDate.toDate());
        return getPrice(new Interval(startDate, endDate));
    }
    // TODO non overlappare price base tra loro. un giorno non può avere più prezzi base
    // TODO per applicare sconti o tasse deve essere presente per forza un prezzo base
    // TODO se non è presente un prezzo base non è affittabile la locazione

    // TODO create function that checks if price is eligible for certain date

    /**
     * @param interval
     * @return
     */
    public double getPrice(Interval interval) {

        double price = 0;

        // TODO che cambiava con il total qui non devo considerare tasse e sconti?
        // TODO ma i prezzi non devono essere collegate alle locazioni? Si tramite prices
        List<Price> basePrices = PriceDao.retrieveBasePrices(interval, 0, Integer.MAX_VALUE);
        List<Fee> fees = PriceDao.retrieveFees(interval, 0, Integer.MAX_VALUE);
        List<Discount> discounts = PriceDao.retrieveDiscounts(interval, 0, Integer.MAX_VALUE);

        DateTime currentDate = interval.getStart();

        // It calculates the price of the day until there are days in the interval provided
        do {
            if (interval.contains(currentDate)) {
                Price dayPrice = new BasePrice.Builder()
                        .setPrice(0)
                        .build();

                // Applies the base price
                for (Price basePrice : basePrices) {
                    if (basePrice.isEligible(currentDate)) {
                        dayPrice = basePrice;
                        System.out.format("Base price is: %f\n", basePrice.getValue());
                        break;
                    }
                    //throw new Exception("There is not even a base price. At lease one base price is needed!");
                }

                // Applies all fees eligible
                for (Fee fee : fees) {
                    if (fee.isEligible(currentDate)) {
                        fee.applyTo(dayPrice);
                        System.out.format("Fee is: %f\n", fee.getValue());
                        dayPrice = fee;
                    }
                }

                // Applies all discounts eligible
                for (Discount discount : discounts) {
                    if (discount.isEligible(currentDate)) {
                        discount.applyTo(dayPrice);
                        System.out.format("Discount is: %f\n", discount.getValue());
                        dayPrice = discount;
                    }
                }

                // Day by day it stores the price of location
                System.out.format("Price for %s is: %f\n", currentDate.toString(), dayPrice.showPrice());
                price += dayPrice.showPrice();
                System.out.format("Total price is: %f\n", price);
                currentDate = currentDate.plusDays(1);
            } else {
                break;
            }
        } while (currentDate != interval.getEnd());

        return price;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     * @return
     */
    public double getTotalPrice(Date startDate, Date endDate, Set<Service> services) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param interval
     * @param services
     * @return
     */
    public double getTotalPrice(Interval interval, Set<Service> services) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     */
    public void reserve(Date startDate, Date endDate, Set<Service> services) {
        // TODO implement here
    }

    /**
     * @param interval
     * @param services
     */
    public void reserve(Interval interval, Set<Service> services) {
        // TODO implement here
    }

    /**
     *
     */
    public void getAvailability() {
        // TODO implement here
    }

    /**
     *
     * @return
     */
    public Prices getPrices() {
        return this.prices;
    }

}