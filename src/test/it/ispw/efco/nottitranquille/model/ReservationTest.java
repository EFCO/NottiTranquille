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

    private Person tenant;


    @Before
    public void setUp() throws Exception {

        // TODO: 17/07/16 create location
        Location loc = LocationDAO.findByID(7l);    // location to book

        tenant = UserDAO.findBy("Zanna");

        /*  JavaBean for Location  */
        LocationBean locBean = new LocationBean();
        locBean.setServices(loc.getServices());
        locBean.setName(loc.getName());
        locBean.setDescription(loc.getDescription());
        locBean.setEnablesDate(loc.getAvailableDate());
        locBean.setId(loc.getId().toString());
        locBean.setReservationType(ReservationType.WithConfirm.getText());

        /* JavaBean for Reservation */
        bean = new ReservationBean();
        bean.setUsername(tenant.getUsername());

        bean.setStartDate("05-04-2017");
        bean.setEndDate("11-04-2017");
        bean.setPrice(loc.getPrice());

        // put the JavaBean of Location into the JavaBean of Reservation
        bean.setLocation(locBean);
    }


    @Test
    public void reserveTest() {

        // syntactically check for information into JavaBean
        // and then call to the controller for the reservation instantiation
        boolean validate = bean.validate();

        Reservation reservation = ReservationDAO.findByID(new Long(bean.getId()));

        /* range of days */
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime da = DateTime.parse(bean.getStartDate(), dateTimeFormatter);
        DateTime a = DateTime.parse(bean.getEndDate(), dateTimeFormatter);

        // through the reservation is created,e the days are still available
        // because they are reserved only after the payment
        boolean booked = reservation.getLocation().isBooked(new Interval(da, a));

        Assert.assertTrue(validate);
        Assert.assertFalse(booked); // reserve period only after payment
        Assert.assertEquals(reservation.getId(), new Long(bean.getId()), 0);


        /* decline and approve */

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