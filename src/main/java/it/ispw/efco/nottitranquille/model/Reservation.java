package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation() {
        this(null, null, null);
    }

    public Reservation(Tenant tenant, Location location, Interval interval) {
        this.tenant = tenant;
        this.location = location;
        this.period = interval;

        this.state = ReservationState.Init;

        try {
            this.price = location.getPrice();
        } catch (NullPointerException e) {
            this.price = 0;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Location location;

    @ManyToOne
    private Tenant tenant;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Person> buyers;


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentInterval")
    @Columns(columns = {@Column(name = "startDate"), @Column(name = "endDate")})
    private Interval period;

/*
    @ManyToMany
    @JoinTable(name = "Reservation_Service",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ServiceId", referencedColumnName = "id")})
    private List<Service> services;
*/

    @Enumerated
    private ReservationState state;

    private float price;

    /**
     * Method needs to update Reservation in the Database.
     * We have to instantiate a new Reservation with update attributes
     *
     * @param toUpdate: Reservation to update in database
     * @see it.ispw.efco.nottitranquille.model.dao.ReservationDAO
     */
    public void update(Reservation toUpdate) {
        this.id = toUpdate.getId();
        this.location = toUpdate.getLocation();
        this.tenant = toUpdate.getTenant();
        this.buyers = toUpdate.getBuyers();
        this.period = toUpdate.getPeriod();
        this.state = toUpdate.getState();
        this.price = toUpdate.getPrice();
    }

    /* GETTER AND SETTER */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Person> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Person> buyers) {
        this.buyers = buyers;
    }

    public Interval getPeriod() {
        return period;
    }

    public void setPeriod(Interval period) {
        this.period = period;
    }

    public DateTime getStartDate() {
        return period.getStart();
    }

    public DateTime getEndDate() {
        return period.getEnd();
    }

    public ReservationState getState() {
        return state;
    }

    public String state() {
        switch (this.state) {
            case Paid:
                return "Paid";
            case ToPay:
                return "ToPay";
            case Init:
                return "Unknown";
            case Declined:
                return "Declined";
            case ToApprove:
                return "ToApprove";
        }

        return null;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}