package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import it.ispw.efco.nottitranquille.view.LocationBean;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

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
    @ElementCollection(targetClass = String.class)
    private List<String> photos;

    /**
     *
     */
    private Integer numberOfBedrooms;

    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private LocationType type;

    /**
     *
     */
    @ManyToMany
    private List<Service> services;

    /**
     *
     */
    @ManyToOne
    private Structure structure;

    @ElementCollection(targetClass = Interval.class, fetch = FetchType.EAGER)
    @Column(length=100000) //for the Data too long error
    private List<Interval> booking = new ArrayList<Interval>();

    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Price> prices = new ArrayList<Price>();

    /**
     *
     */
    @Transient
    private List<BasePrice> basePrices = new ArrayList<BasePrice>();

    /**
     *
     */
    @Transient
    private List<Fee> fees = new ArrayList<Fee>();

    /**
     *
     */
    @Transient
    private List<Discount> discounts = new ArrayList<Discount>();

    /**
     *
     */
    @Transient
    private List<FixFee> fixFees = new ArrayList<FixFee>();

    /**
     *
     */
    @Transient
    private List<PercentageFee> percentageFees = new ArrayList<PercentageFee>();

    /**
     *
     */
    @Transient
    private List<FixDiscount> fixDiscounts = new ArrayList<FixDiscount>();

    /**
     *
     */
    @Transient
    private List<PercentageDiscount> percentageDiscounts = new ArrayList<PercentageDiscount>();

    /**
     * Default constructor
     */
    public Location() {
    }

    public Location(List<Interval> booking, Structure structure, Integer maxGuestsNumber, LocationType type) {
        this.booking = booking;
        this.structure = structure;
        this.maxGuestsNumber = maxGuestsNumber;
        this.type = type;
    }

    public Location(LocationBean locationBean) {
        this.description = locationBean.getDescription();
        this.numberOfRooms = Integer.valueOf(locationBean.getNumberOfRooms());
        this.numberOfBathrooms = Integer.valueOf(locationBean.getNumberOfBathrooms());
        this.maxGuestsNumber = Integer.valueOf(locationBean.getMaxGuestsNumber());
        this.numberOfBeds = Integer.valueOf(locationBean.getNumberOfBeds());
        this.numberOfBedrooms = Integer.valueOf(locationBean.getNumberOfBedrooms());
        this.photos = new ArrayList<String>();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
//        DateTime start = DateTime.parse("01-01-2016", dateTimeFormatter);
//        DateTime end = DateTime.parse("30-12-2016", dateTimeFormatter);
        this.booking = locationBean.getIntervalList();
        this.type = LocationType.Hotel;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Integer getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    /**
     * Gets a {@link Location}'s {@link Price} by id.
     *
     * @param id the id of the Price
     * @return the price
     */
    public Price getPriceById(long id) {
        for (Price price : prices) {
            if (price.getId() == id) {
                return price;
            }
        }
        return null;
    }

    /**
     * Checks if an {@link Interval} is available in the booking intervals.
     *
     * @param interval the interval to check
     * @return true if the interval intersect some booking's interval
     */
    public boolean isAvailable(Interval interval) {
        for (Interval inter : this.booking) {
            if (interval.isEqual(inter) || (inter.getStart().isBefore(interval.getStart()) && inter.getEnd().isAfter(interval.getEnd()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates final price of the {@link Location} in a certain {@link Interval}.
     *
     * @param interval the interval of time of the holiday
     * @return the final price
     * @throws Exception if there is no a {@link BasePrice} for a day in the holiday's interval
     */
    // TODO se non è presente un prezzo base non è affittabile la locazione e per applicare sconti o tasse deve essere presente per forza un prezzo base
    public double calculatePrice(Interval interval) throws Exception {

        double price = 0;
        DateTime currentDate = interval.getStart();

        // It calculates the price of the day until there are days in the interval provided
        do {
            if (interval.contains(currentDate)) {
                Price dayPrice = new BasePrice.Builder()
                        .setPrice(0)
                        .build();

                if (basePrices.size() != 0) {
                    // Applies the base price
                    for (Price basePrice : basePrices) {
                        if (basePrice.isEligible(currentDate)) {
                            dayPrice = basePrice;
                            System.out.format("Base price is: %f\n", basePrice.getValue());
                            break;
                        }
                        throw new Exception("There is not even a base price. At lease one base price is needed!");
                    }
                } else {
                    throw new Exception("There is not even a base price. At lease one base price is needed!");
                }

                // Applies all fix discounts eligible
                for (FixDiscount discount : fixDiscounts) {
                    if (discount.isEligible(currentDate)) {
                        discount.applyTo(dayPrice);
                        System.out.format("Discount is: %f\n", discount.getValue());
                        dayPrice = discount;
                    }
                }

                // Applies all percentage discounts eligible
                for (PercentageDiscount discount : percentageDiscounts) {
                    if (discount.isEligible(currentDate)) {
                        discount.applyTo(dayPrice);
                        System.out.format("Discount is: %f\n", discount.getValue());
                        dayPrice = discount;
                    }
                }

                // Applies all fix fees eligible
                for (FixFee fee : fixFees) {
                    if (fee.isEligible(currentDate)) {
                        fee.applyTo(dayPrice);
                        System.out.format("Fee is: %f\n", fee.getValue());
                        dayPrice = fee;
                    }
                }

                // Applies all percentage fees eligible
                for (PercentageFee fee : percentageFees) {
                    if (fee.isEligible(currentDate)) {
                        fee.applyTo(dayPrice);
                        System.out.format("Fee is: %f\n", fee.getValue());
                        dayPrice = fee;
                    }
                }

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
     * Calculates final price of the {@link Location} from a certain date to another one.
     *
     * @param startDate the start date of the holiday
     * @param endDate the end date of the holiday
     * @return the final price
     */
    public double calculatePrice(DateTime startDate, DateTime endDate) throws Exception {
        return calculatePrice(new Interval(startDate, endDate));
    }

    /**
     * Calculates final price of the {@link Location} in a certain date.
     *
     * @param date the date to calculate
     * @return the final price
     */
    public double calculatePrice(DateTime date) throws Exception {
        return calculatePrice(new Interval(date, date));
    }

    /**
     * Adds a {@link Price} into {@link Location}.
     *
     * @param price the price to add
     */
    // TODO non overlappare price base tra loro. un giorno non può avere più prezzi base
    public void addPrice(Price price) {
        // Adds prices to general list
        prices.add(price);
        addPriceIntoLists(price);
    }

    /**
     * Fill the transient list of BasePrice, Fee, Discount, FixFee, FixDiscount, PercentageFee and PercentageDiscount
     * with a new {@link Price}.
     *
     * @param price the price to add
     */
    private void addPriceIntoLists(Price price) {
        // Adds price to specific lists
        if (price instanceof BasePrice) {
            basePrices.add((BasePrice) price);
        } else if (price instanceof Fee) {
            fees.add((Fee) price);
            if (price instanceof FixFee) {
                fixFees.add((FixFee) price);
            } else if (price instanceof PercentageFee) {
                percentageFees.add((PercentageFee) price);
            }
        } else if (price instanceof Discount) {
            discounts.add((Discount) price);
            if (price instanceof FixDiscount) {
                fixDiscounts.add((FixDiscount) price);
            } else if (price instanceof PercentageDiscount) {
                percentageDiscounts.add((PercentageDiscount) price);
            }
        }
    }

    /**
     * Remove a {@link Price} from the {@link Location}.
     *
     * @param price the price to remove
     */
    public void removePrice(Price price) {
        // Removes price from general list
        prices.remove(price);

        // Removes price from specific lists
        if (price instanceof BasePrice) {
            basePrices.remove(price);
        } else if (price instanceof Fee) {
            fees.remove(price);
            if (price instanceof FixFee) {
                fixFees.remove(price);
            } else if (price instanceof PercentageFee) {
                percentageFees.remove(price);
            }
        } else if (price instanceof Discount) {
            discounts.remove(price);
            if (price instanceof FixDiscount) {
                fixDiscounts.remove(price);
            } else if (price instanceof PercentageDiscount) {
                percentageDiscounts.remove(price);
            }
        }
    }

    /**
     * Remove a {@link Price} from the {@link Location}.
     *
     * @param priceId the id of the price to remove
     */
    public void removePrice(long priceId) {
        removePrice(getPriceById(priceId));
    }

    /**
     * Updates Location's state with the state of the Location provided.
     *
     * @param locationToUpdate the new location
     */
    public void update(Location locationToUpdate) {
        this.description = locationToUpdate.description;
        this.numberOfRooms = locationToUpdate.numberOfRooms;
        this.numberOfBathrooms = locationToUpdate.numberOfBathrooms;
        this.maxGuestsNumber = locationToUpdate.maxGuestsNumber;
        this.numberOfBeds = locationToUpdate.numberOfBeds;
        this.photos = locationToUpdate.photos;
        this.numberOfBedrooms = locationToUpdate.numberOfBedrooms;
        this.type = locationToUpdate.type;
        this.services = locationToUpdate.services;
        this.structure = locationToUpdate.structure;
        this.booking = locationToUpdate.booking;
        this.prices = locationToUpdate.prices;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     * @return
     */
    public double calculateTotalPrice(DateTime startDate, DateTime endDate, List<Service> services) {
        return calculateTotalPrice(new Interval(startDate, endDate), services);
    }

    /**
     * @param interval
     * @param services
     * @return
     */
    public double calculateTotalPrice(Interval interval, List<Service> services) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     */
    public void reserve(DateTime startDate, DateTime endDate, List<Service> services) {
        reserve(new Interval(startDate, endDate), services);
    }

    /**
     * @param interval
     * @param services
     */
    public void reserve(Interval interval, List<Service> services) {
        // TODO implement here
    }

    /**
     *
     */
    public void getAvailability() {
        // TODO implement here
    }

    /**
     * After loading from DB fill the transient list of BasePrice, Fee, Discount, FixFee, FixDiscount, PercentageFee and PercentageDiscount.
     */
    @PostLoad
    private void discriminatePrices() {
        for (Price price : this.prices) {
            addPriceIntoLists(price);
        }
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public LocationType getType() {
        return type;
    }

    public List<Service> getServices() {
        return services;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<Interval> getBooking() {
        return booking;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", numberOfBathrooms=" + numberOfBathrooms +
                ", maxGuestsNumber=" + maxGuestsNumber +
                ", numberOfBeds=" + numberOfBeds +
                ", photos=" + photos +
                ", numberOfBedrooms=" + numberOfBedrooms +
                ", type=" + type +
                ", services=" + services +
                ", structure=" + structure +
                ", booking=" + booking +
                ", prices=" + prices +
                ", basePrices=" + basePrices +
                ", fees=" + fees +
                ", discounts=" + discounts +
                ", fixFees=" + fixFees +
                ", percentageFees=" + percentageFees +
                ", fixDiscounts=" + fixDiscounts +
                ", percentageDiscounts=" + percentageDiscounts +
                '}';
    }
}