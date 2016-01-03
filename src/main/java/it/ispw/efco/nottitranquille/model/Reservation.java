package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation() {
    }

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private List<Service> services;

    /**
     * 
     */
    private ReservationState state;

    /**
     * 
     */
    private Request request;



    /**
     * 
     */
    public void pay() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Prices CalculatePrice() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void addService() {
        // TODO implement here
    }

    /**
     * 
     */
    public void remoseService() {
        // TODO implement here
    }

    /**
     * 
     */
    public void changeState() {
        // TODO implement here
    }

    /**
     * 
     */
    public void reservationNotify() {
        // TODO implement here
    }

}