package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.PriceDao;
import it.ispw.efco.nottitranquille.model.enumeration.Day;
import it.ispw.efco.nottitranquille.model.enumeration.RepetitionType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

public class Demo {
    private Price price;

    public Demo(){
        this.setPrice(null);
    }

    public Demo(Price price){
        this.setPrice(price);
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void display(){
        System.out.printf("%.2f€%n", this.price.showPrice());
    }

    public static void main(String[] args) {

 /*      DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime da = DateTime.parse("2016-01-13", dateTimeFormatter);
        DateTime a = DateTime.parse("2016-01-19", dateTimeFormatter);

        Interval interval = new Interval(da, a);
        PercentageFee percentageFee = new PercentageFee.Builder()
                .setFee(2)
                .setInterval(interval)
                .build();

        PriceDao.store(percentageFee);

        da = DateTime.parse("2016-01-15", dateTimeFormatter);
        a = DateTime.parse("2016-01-25", dateTimeFormatter);

        interval = new Interval(da, a);
        PercentageDiscount percentageDiscount = new PercentageDiscount.Builder()
                .setDiscount(1)
                .setInterval(interval)
                .build();

        PriceDao.store(percentageDiscount);

        da = DateTime.parse("2016-01-13", dateTimeFormatter);
        a = DateTime.parse("2016-01-21", dateTimeFormatter);

        interval = new Interval(da, a);
        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(3)
                .setInterval(interval)
                .build();

        PriceDao.store(basePrice);

        da = DateTime.parse("2016-01-15", dateTimeFormatter);
        a = DateTime.parse("2016-01-19", dateTimeFormatter);

        interval = new Interval(da, a);
        percentageFee = new PercentageFee.Builder()
                .setFee(6)
                .setInterval(interval)
                .build();

        PriceDao.store(percentageFee);

        da = DateTime.parse("2016-01-10", dateTimeFormatter);
        a = DateTime.parse("2016-01-12", dateTimeFormatter);

        interval = new Interval(da, a);
        percentageDiscount = new PercentageDiscount.Builder()
                .setDiscount(42)
                .setInterval(interval)
                .build();

        PriceDao.store(percentageDiscount);

        da = DateTime.parse("2016-01-22", dateTimeFormatter);
        a = DateTime.parse("2016-01-25", dateTimeFormatter);

        interval = new Interval(da, a);
        basePrice = new BasePrice.Builder()
                .setPrice(43)
                .setInterval(interval)
                .build();

        PriceDao.store(basePrice);*/

/*        List<Price> prices = PriceDao.findAllPrices(new Interval(da, a));*/

/*        List <Fee> fees = PriceDao.findAllFees();

        System.out.println("Retrieving fees...");

        System.out.println("\nFees: " + fees.size());
        for (Fee fee : fees) {
            System.out.println(fee.toString());
        }*/

        /*System.out.println("Intializing JPA/Hibernate...");

        System.out.println("Retrieving prices...");

        // retrieve prices
        List<Price> allPrices = PriceDao.findAllPrices();

        // print current list of prices
        System.out.println("\nPrices: " + allPrices.size());
        for (Price price : allPrices) {
            System.out.println(price.toString());
        }

        System.out.println("\nSaving new prices...");
        // create and store events
        BasePrice basePrice = new BasePrice.Builder().setPrice(20).build();
        FixFee fixFee = new FixFee.Builder().setFee(10).build();
        PercentageDiscount percentageDiscount = new PercentageDiscount.Builder().setDiscount(10).build();

        PriceDao.store(basePrice);
        PriceDao.store(fixFee);
        PriceDao.store(percentageDiscount);

        // retrieve prices
        allPrices = PriceDao.findAllPrices();

        // print current list of events
        System.out.println("\n NowPrices: " + allPrices.size());
        for (Price price : allPrices)
            System.out.println(price.toString());

        List<Fee> allFees = PriceDao.findAllFees();
        // print current list of fees
        System.out.println("\nFees: " + allFees.size());
        for (Fee fee : allFees) {
            System.out.println(fee.toString());
        }

        List<PercentageDiscount> allPercentageDiscount = PriceDao.findAllPercentageDiscounts();
        // print current list of percentage discounts
        System.out.println("\nPercentageDiscounts: " + allPercentageDiscount.size());
        for (PercentageDiscount discount : allPercentageDiscount) {
            System.out.println(discount.toString());
        }*/
/*
        Event eu1 = new Event((long)allEvents.size(), "A5");
        EventDaoJPA.updateEventA(eu1);
        Event eu2 = new Event((long)allEvents.size()-2, "A5bis");
        EventDaoJPA.updateEventA(eu2);

        System.out.println("Retrieving events...");
        // retrieve events again
        allEvents = EventDaoJPA.findAllEventsB();

        // print updated list of events
        System.out.println("\nEventi (" + allEvents.size()
                + "):\nId\tData\tTitolo");
        for (Event e : allEvents)
            System.out.println(e.getId() + "\t" + e.getEventDate() + "\t"
                    + e.getTitle());

        System.out.println("Deleting events...");

        Event ed = new Event((long)allEvents.size()-1, "A5");
        EventDaoJPA.deleteEvent(ed);

        System.out.println("Retrieving events...");
        // retrieve events again
        allEvents = EventDaoJPA.findAllEventsB();

        // print updated list of events
        System.out.println("\nEventi (" + allEvents.size()
                + "):\nId\tData\tTitolo");
        for (Event e : allEvents)
            System.out.println(e.getId() + "\t" + e.getEventDate() + "\t"
                    + e.getTitle());
        */
        /*BasePrice basePrice = new BasePrice.Builder()
                .setPrice(20)
                .build();

        Demo demo = new Demo(basePrice);
        System.out.print("Cost: ");
        demo.display();
        System.out.println();

        double percentageFee = 21;
        PercentageFee basePriceWithPercentageFee = new PercentageFee.Builder()
                .applyTo(basePrice)
                .setFee(percentageFee)
                .build();

        demo.setPrice(basePriceWithPercentageFee);
        System.out.printf("Cost with a fee of %.0f%%: ", percentageFee);
        demo.display();

        double discount = 10;
        FixDiscount basePriceWithPercentageFeeAndFixDiscount = new FixDiscount.Builder()
                .applyTo(basePriceWithPercentageFee)
                .setRepetitionType(RepetitionType.EVERY_MONTH)
                .setDays(Collections.singletonList(Day.FIRST_SUNDAY))
                .setDiscount(discount)
                .build();

        demo.setPrice(basePriceWithPercentageFeeAndFixDiscount);
        System.out.printf("Cost with a discount of %.2f€: ", discount);
        demo.display();

        System.out.print(basePriceWithPercentageFeeAndFixDiscount.toString());*/
/*
        double discount = 10;
        FixDiscount priceWithFixDiscount = new FixDiscount.Builder()
                .applyTo(basePrice)
                .setDiscount(discount)
                .build();

        demo.setPrice(priceWithFixDiscount);
        System.out.printf("Cost with a fee of %.2f€: ", discount);
        demo.display();

        double percentageDiscount = 50;
        PercentageDiscount priceWithFixDiscountAndPercentageDiscount = new PercentageDiscount.Builder()
                .applyTo(priceWithFixDiscount)
                .setDiscount(percentageDiscount)
                .build();

        demo.setPrice(priceWithFixDiscountAndPercentageDiscount);
        System.out.printf("Cost discounted down to %.0f%%: ", percentageDiscount);
        demo.display();

        double percentageFee = 21;
        PercentageFee priceWithFixDiscountAndPercentageDiscountAndPercentageFee = new PercentageFee.Builder()
                .applyTo(priceWithFixDiscountAndPercentageDiscount)
                .setFee(percentageFee)
                .build();

        demo.setPrice(priceWithFixDiscountAndPercentageDiscountAndPercentageFee);
        System.out.printf("Cost with a fee of %.0f%%: ", percentageFee);
        demo.display();*/

/*

        Location location = new Location();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        location.getPrice(DateTime.parse("2016-01-13", dateTimeFormatter), DateTime.parse("2016-06-12", dateTimeFormatter));
*/

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime da = DateTime.parse("2016-06-01", dateTimeFormatter);
        DateTime a = DateTime.parse("2016-06-15", dateTimeFormatter);
/*
        BasePrice basePrice = new BasePrice.Builder()
                .setInterval(new Interval(da, a))
                .setPrice(100)
                .build();

        PriceDao.store(basePrice);

        da = DateTime.parse("2016-06-16", dateTimeFormatter);
        a = DateTime.parse("2016-06-30", dateTimeFormatter);

        basePrice = new BasePrice.Builder()
                .setInterval(new Interval(da, a))
                .setPrice(200)
                .build();

        PriceDao.store(basePrice);

        double percentageFee = 21;
        PercentageFee fee = new PercentageFee.Builder()
                .setFee(percentageFee)
                .setRepetitionType(RepetitionType.EVERY_DAY)
                .build();

        PriceDao.store(fee);

        double percentageDiscount = 50;
        PercentageDiscount discount = new PercentageDiscount.Builder()
                .setDiscount(percentageDiscount)
                .setRepetitionType(RepetitionType.EVERY_WEEK)
                .setDays(Collections.singletonList(Day.SUNDAY))
                .build();

        PriceDao.store(discount);*/

        da = DateTime.parse("2016-06-14", dateTimeFormatter);
        a = DateTime.parse("2016-06-17", dateTimeFormatter);

        Location location = new Location();
        System.out.printf("The price for the location between %s - %s is: %f", da.toString(), a.toString(), location.getPrice(new Interval(da, a)));

    }
}
