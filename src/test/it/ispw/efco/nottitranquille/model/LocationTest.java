package it.ispw.efco.nottitranquille.model;


import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class LocationTest {

    private Interval avlInterval;   // available interval of time for the location

    private Interval period;    // period to test

    private Location location;

    public LocationTest(String da, String a) {

        // Instantiate a formatter to declare date's format
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        // Available range of days
        DateTime avlda = DateTime.parse("01-01-2017", dateTimeFormatter);
        DateTime avla = DateTime.parse("01-01-2018", dateTimeFormatter);

        this.avlInterval = new Interval(avlda, avla);

        // range to test
        DateTime daA = DateTime.parse(da, dateTimeFormatter);
        DateTime aA = DateTime.parse(a, dateTimeFormatter);

        this.period = new Interval(daA, aA);
    }

    @Before
    public void createLocation() {


        this.location = new Location();

        try {

            //update location with available days
            location.addAvailablePeriod(avlInterval);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters() {

        return Arrays.asList(new String[][]{
                {"05-01-2017", "10-01-2017"},
                {"01-01-2017", "01-01-2018"}
        });
    }

    @Test
    public void isAvailable() {

        // Test if period is available for location
        boolean bool = location.isAvailable(period);

        Assert.assertTrue(bool);
    }

    @Test
    public void isNotAvailable() {

        /* create new range of days to test*/
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime da = DateTime.parse("01-01-2017", dateTimeFormatter);
        DateTime a = DateTime.parse("01-01-2019", dateTimeFormatter);

        // statement to test
        boolean bool = location.isAvailable(new Interval(da, a));

        Assert.assertFalse(bool);

    }

    @Test
    public void bookTest() {

        // after this statement, this period of days must be reserved for the location
        location.bookPeriod(period);

        // a range of days might be not available but not booked
        Assert.assertFalse(location.isAvailable(period));
        Assert.assertTrue(location.isBooked(period));

    }

}
