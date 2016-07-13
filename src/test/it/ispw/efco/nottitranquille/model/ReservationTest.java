package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.dao.LocationDAO;

import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.UserDAO;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.view.LocationBean;
import it.ispw.efco.nottitranquille.view.ReservationBean;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReservationTest {


    private ReservationBean bean;

    private Person tenant;


    @Before
    public void setUp() throws Exception {
        Location loc = LocationDAO.findByID(7l);
        tenant = UserDAO.findBy("Zanna");

        LocationBean locBean = new LocationBean();
        locBean.setServices(loc.getServices());
        locBean.setName(loc.getName());
        locBean.setDescription(loc.getDescription());
        locBean.setEnablesDate(loc.getAvailableDate());
        locBean.setId(loc.getId().toString());
        locBean.setType("WithConfirm");

        bean = new ReservationBean();
        bean.setUsername(tenant.getUsername());

        bean.setStartDate("05-04-2017");
        bean.setEndDate("11-04-2017");
        bean.setPrice(loc.getPrice());

        bean.setLocationBean(locBean);
    }


    @Test
    public void reserveTest() {


        boolean validate = bean.validate();

        Reservation reservation = ReservationDAO.findByID(new Long(bean.getId()));

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime da = DateTime.parse(bean.getStartDate(), dateTimeFormatter);
        DateTime a = DateTime.parse(bean.getEndDate(), dateTimeFormatter);

        boolean booked = reservation.getLocation().isBooked(new Interval(da, a));


        Assert.assertTrue(validate);
        Assert.assertFalse(booked); // reserve period only after payment
        Assert.assertEquals(reservation.getId(), new Long(bean.getId()), 0);

    }
}