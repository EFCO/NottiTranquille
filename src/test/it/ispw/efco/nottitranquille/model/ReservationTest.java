package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.dao.LocationDAO;

import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.UserDAO;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.view.ListReservationBean;
import it.ispw.efco.nottitranquille.view.LocationBean;
import it.ispw.efco.nottitranquille.view.ReservationBean;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReservationTest {


    private ReservationBean bean;

    private Location location;

    private Person tenant;


    @Before
    public void setUp() throws Exception {


        location = LocationDAO.findByID(5l);    // location to book

        tenant = UserDAO.findBy("Zanna");

        /*  JavaBean for Location  */
        LocationBean locBean = new LocationBean();
        locBean.setServices(location.getServices());
        locBean.setName(location.getName());
        locBean.setDescription(location.getDescription());
        locBean.setEnablesDate(location.getAvailableDate());
        locBean.setId(location.getId().toString());
        locBean.setReservationType(ReservationType.WithConfirm.getText());

        /* JavaBean for Reservation */
        bean = new ReservationBean();
        bean.setUsername(tenant.getUsername());

        bean.setStartDate("06-10-2017");
        bean.setEndDate("08-10-2017");
        bean.setPrice(location.getPrice());

        // put the JavaBean of Location into the JavaBean of Reservation
        bean.setLocation(locBean);
    }


    @Test
    public void reserveTest() {

        // syntactically check for information into JavaBean
        // and then call to the controller for the reservation instantiation
        boolean validate = bean.validate();


        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime bookingFrom = DateTime.parse(bean.getStartDate(), dateTimeFormatter);
        DateTime bookingTo = DateTime.parse(bean.getEndDate(), dateTimeFormatter);

        Interval interval = new Interval(bookingFrom, bookingTo);

        // test if the range of days is still available
        if (location.isAvailable(interval)) {

            Reservation reservation = ReservationDAO.findByID(new Long(bean.getId()));

             /* range of days */

            DateTime bookedFrom = DateTime.parse(bean.getStartDate(), dateTimeFormatter);
            DateTime bookedTo = DateTime.parse(bean.getEndDate(), dateTimeFormatter);

            // through the reservation is created,e the days are still available
            // because they are reserved only after the payment
            boolean booked = reservation.getLocation().isBooked(new Interval(bookedFrom, bookedTo));

            Assert.assertTrue(validate);
            Assert.assertFalse(booked); // reserve period only after payment
            Assert.assertEquals(reservation.getId(), new Long(bean.getId()), 0);



             /* decline and approve */

            if (reservation.getLocation().getReservationType() == ReservationType.WithConfirm) {

                // create a list of reservation bean to emulate view
                List<ReservationBean> res = new ArrayList<ReservationBean>();
                res.add(bean);
                ListReservationBean listBean = new ListReservationBean();
                listBean.setBeans(res);
                listBean.setnRes(1);

                listBean.approve(reservation.getId().toString());

                Assert.assertTrue(reservation.getState() == ReservationState.ToPay);
            }

        }

    }

}