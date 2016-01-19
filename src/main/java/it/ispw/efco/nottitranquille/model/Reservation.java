package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.Type;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Location location;

    @ManyToOne
    private Tenant tenant;

    @Transient
    private List<Person> buyers;

    @Transient
    private Interval period;

    /**
     * Also {@link Location} has services: here we indicate services bought.
     */
    @ManyToMany
    @JoinTable(name = "Reservation_Service",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ServiceId", referencedColumnName = "id")})
    private List<Service> services;


    @Enumerated
    private ReservationState state;


    @OneToOne
    private Request request;


    /**
     * Default constructor
     */
    public Reservation() {
        this(null);
    }

    public Reservation(Location location) {
        this(null, location);
    }

    public Reservation(Tenant tentant, Location location) {
        this(tentant, location, null);
    }

    public Reservation(Tenant tentant, Location location, Interval interval) {
        this.tenant = tentant;
        this.location = location;
        this.period = interval;

        this.state= ReservationState.Unknown;
    }


    /**
     *
     */
    public void pay() {
        // TODO implement here
    }

    /**
     * @return Prices
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

    public void update(Reservation toUpdate) {
        this.id = toUpdate.getId();
       /* this.services = toUpdate.getServices();
        this.state = toUpdate.getState();*/
    }

    /* Getter and Setter */

    public Long getId() {
        return this.id;
    }

    /* public ReservationState getState() {
        return this.state;
    }

    public List<Service> getServices() {
        return this.services;
    }*/


}