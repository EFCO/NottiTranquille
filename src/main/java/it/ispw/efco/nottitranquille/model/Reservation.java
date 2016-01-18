package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
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

    @ManyToOne
    private Tenant tenant;

    @ManyToMany
    private List<Person> buyers;

    private Interval period;

    /**
     * Also {@link Location} has services: here we indicate services bought.
     */
    @ManyToMany
    @JoinTable(name="Reservation_Service",
            joinColumns={@JoinColumn(name="ReservationId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ServiceId", referencedColumnName="id")})
    private List<Service> services;

    /**
     *
     */
    @Enumerated
    private ReservationState state;

    /**
     *
     */
    @OneToOne
    private Request request;

    @ManyToOne
    private Location location;

    /**
     * Default constructor
     */
    public Reservation() {
    }

    public Reservation(Request req, ReservationState state){
        this.request=req;
        this.state=state;
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

    public Request getRequest() {
        return this.request;
    }

    public ReservationState getState() {
        return this.state;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public Long getId() {
        return this.id;
    }

    public void update(Reservation toUpdate) {
        this.id = toUpdate.getId();
        this.request = toUpdate.getRequest();
        this.services = toUpdate.getServices();
        this.state = toUpdate.getState();
    }


}