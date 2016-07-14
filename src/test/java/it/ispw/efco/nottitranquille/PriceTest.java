package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.LocationDao;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PriceTest {

    @Test
    @Parameters({"110, 100, 10", "100, 100, 0"})
    public void basePriceWithFixFee(Integer excepted, Integer basePriceValue, Integer fixFeeValue) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(basePriceValue)
                .setInterval(interval)
                .build();

        FixFee basePriceWithFixFee = new FixFee.Builder()
                .setFee(fixFeeValue)
                .setInterval(interval)
                .applyTo(basePrice)
                .build();

        Assert.assertEquals(excepted, basePriceWithFixFee.showPrice(), 0);
    }

    @Test
    @Parameters({"90, 100, 10", "100, 100, 0"})
    public void basePriceWithFixDiscount(Integer excepted, Integer basePriceValue, Integer fixDiscountValue) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(basePriceValue)
                .setInterval(interval)
                .build();

        FixDiscount basePriceWithFixDiscount = new FixDiscount.Builder()
                .setDiscount(fixDiscountValue)
                .setInterval(interval)
                .applyTo(basePrice)
                .build();

        Assert.assertEquals(excepted, basePriceWithFixDiscount.showPrice(), 0);
    }

    @Test
    @Parameters({"110, 100, 10", "100, 100, 0"})
    public void basePriceWithPercentageFee(Integer excepted, Integer basePriceValue, Integer percentageFeeValue) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(basePriceValue)
                .setInterval(interval)
                .build();

        PercentageFee basePriceWithPercentageFee = new PercentageFee.Builder()
                .setFee(percentageFeeValue)
                .setInterval(interval)
                .applyTo(basePrice)
                .build();

        Assert.assertEquals(excepted, basePriceWithPercentageFee.showPrice(), 0);
    }

    @Test
    @Parameters({"90, 100, 10", "100, 100, 0"})
    public void basePriceWithPercentageDiscount(Integer excepted, Integer basePriceValue, Integer percentageDiscountValue) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(basePriceValue)
                .setInterval(interval)
                .build();

        PercentageDiscount basePriceWithPercentageDiscount = new PercentageDiscount.Builder()
                .setDiscount(percentageDiscountValue)
                .setInterval(interval)
                .applyTo(basePrice)
                .build();

        Assert.assertEquals(excepted, basePriceWithPercentageDiscount.showPrice(), 0);
    }

    @Test
    @Parameters({"100, 100, 10, 10, 10, 9.09", "110, 100, 10, 10, 10, 0", "90, 100, 10, 10, 0, 10"})
    public void basePriceWithFixFeeFixDiscountPercentageFeePercentageDiscount(Double excepted, Double basePriceValue, Double fixFeeValue, Double fixDiscountValue, Double percentageFeeValue, Double percentageDiscountValue) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(basePriceValue)
                .setInterval(interval)
                .build();

        FixFee basePriceWithFixFee = new FixFee.Builder()
                .setFee(fixFeeValue)
                .setInterval(interval)
                .applyTo(basePrice)
                .build();

        FixDiscount basePriceWithFixFeeWithFixDiscount = new FixDiscount.Builder()
                .setDiscount(fixDiscountValue)
                .setInterval(interval)
                .applyTo(basePriceWithFixFee)
                .build();

        PercentageFee basePriceWithFixFeeWithFixDiscountPercentageFee = new PercentageFee.Builder()
                .setFee(percentageFeeValue)
                .setInterval(interval)
                .applyTo(basePriceWithFixFeeWithFixDiscount)
                .build();

        PercentageDiscount basePriceWithFixFeeWithFixDiscountPercentageFeePercentageDiscount = new PercentageDiscount.Builder()
                .setDiscount(percentageDiscountValue)
                .setInterval(interval)
                .applyTo(basePriceWithFixFeeWithFixDiscountPercentageFee)
                .build();

        Assert.assertEquals(excepted, basePriceWithFixFeeWithFixDiscountPercentageFeePercentageDiscount.showPrice(), 0.01);
    }

    @Test
    @Parameters({"200, 100, 10, 10, 10, 10, 2016-06-13, 2016-06-15, 2016-06-13, 2016-06-15, 2016-06-12, 2016-06-12, 2016-06-12, 2016-06-12, 2016-06-12, 2016-06-12, 2016-06-12, 2016-06-12"})
    public void calculatePrice(Double excepted, Double basePriceValue, Double fixFeeValue, Double fixDiscountValue, Double percentageFeeValue, Double percentageDiscountValue, String holidayFromDate, String holidayToDate, String basePriceFromDate, String basePriceToDate, String fixFeeFromDate, String fixFeeToDate, String fixDiscountFromDate, String fixDiscountToDate, String percentageFeeFromDate, String percentageFeeToDate, String percentageDiscountFromDate, String percentageDiscountToDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        Location location = new Location();

        BasePrice basePrice = new BasePrice.Builder()
                .setInterval(new Interval(DateTime.parse(basePriceFromDate, dateTimeFormatter), DateTime.parse(basePriceToDate, dateTimeFormatter)))
                .setPrice(basePriceValue)
                .build();

        location.addPrice(basePrice);

        if (fixFeeValue != 0) {
            FixFee fixFee = new FixFee.Builder()
                    .setFee(fixFeeValue)
                    .setInterval(new Interval(DateTime.parse(fixFeeFromDate, dateTimeFormatter), DateTime.parse(fixFeeToDate, dateTimeFormatter)))
                    .build();

            location.addPrice(fixFee);
        }

        if (fixDiscountValue != 0) {
            FixDiscount fixDiscount = new FixDiscount.Builder()
                    .setDiscount(fixDiscountValue)
                    .setInterval(new Interval(DateTime.parse(fixDiscountFromDate, dateTimeFormatter), DateTime.parse(fixDiscountToDate, dateTimeFormatter)))
                    .build();

            location.addPrice(fixDiscount);
        }

        if (percentageFeeValue != 0) {
            PercentageFee percentageFee = new PercentageFee.Builder()
                    .setFee(percentageFeeValue)
                    .setInterval(new Interval(DateTime.parse(percentageFeeFromDate, dateTimeFormatter), DateTime.parse(percentageFeeToDate, dateTimeFormatter)))
                    .build();

            location.addPrice(percentageFee);
        }

        if (percentageDiscountValue != 0) {
            PercentageDiscount percentageDiscount = new PercentageDiscount.Builder()
                    .setDiscount(percentageDiscountValue)
                    .setInterval(new Interval(DateTime.parse(percentageDiscountFromDate, dateTimeFormatter), DateTime.parse(percentageDiscountToDate, dateTimeFormatter)))
                    .build();

            location.addPrice(percentageDiscount);
        }

        try {
            Assert.assertEquals(excepted, location.calculatePrice(new Interval(DateTime.parse(holidayFromDate, dateTimeFormatter), DateTime.parse(holidayToDate, dateTimeFormatter))), 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithDatabase() {
        Location location = LocationDao.retrieveLocations().get(0);

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime from = DateTime.parse("2016-06-12", dateTimeFormatter);
        DateTime to = DateTime.parse("2016-06-18", dateTimeFormatter);

        Interval interval = new Interval(from, to);

        BasePrice basePrice = new BasePrice.Builder()
                .setPrice(15)
                .setInterval(interval)
                .build();

        location.addPrice(basePrice);

        try {
            System.out.print(location.calculatePrice(interval));
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }
}
