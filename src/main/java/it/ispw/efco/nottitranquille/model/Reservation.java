package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
//TODO @Entity
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation() {
    }

    /**
     * 
     */
    private DateTime date;

    /**
     * 
     */
    //TODO @ElementCollection(targetClass = Service.class)
    @Transient
    private List<Service> services;

    /**
     * 
     */
    private ReservationState state;

    /**
     * 
     */
    //TODO @ManyToOne
    @Transient
    private Request request;



    /**
     * 
     */
    public void pay() {
        // TODO implement here
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

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}