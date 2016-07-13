package it.ispw.efco.nottitranquille.model;


import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime avlda = DateTime.parse("01-01-2017", dateTimeFormatter);
        DateTime avla = DateTime.parse("01-01-2018", dateTimeFormatter);

        this.avlInterval = new Interval(avlda, avla);

        DateTime daA = DateTime.parse(da, dateTimeFormatter);
        DateTime aA = DateTime.parse(a, dateTimeFormatter);

        this.period = new Interval(daA, aA);
    }

    @Before
    public void createLocation() {
        this.location = new Location();

        try {
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

        boolean bool = location.isAvailable(period);

        assert (bool);
    }

    @Test
    public void isNotAvailable() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime da = DateTime.parse("01-01-2017", dateTimeFormatter);
        DateTime a = DateTime.parse("01-01-2019", dateTimeFormatter);


        boolean bool = location.isAvailable(new Interval(da, a));

        assert (!bool);

    }

    @Test
    public void bookTest() {

        location.bookPeriod(period);

        assert (!location.isAvailable(period));
        assert (location.isBooked(period));

    }

}
