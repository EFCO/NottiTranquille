package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.hibernate.annotations.Columns;
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

    @ManyToMany
    private List<Person> buyers;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentInterval")
    @Columns(columns = { @Column(name = "startDate"), @Column(name = "endDate") })
    private Interval period;


    @ManyToMany
    @JoinTable(name = "Reservation_Service",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ServiceId", referencedColumnName = "id")})
    private List<Service> services;

    @Enumerated
    private ReservationState state;


    /**
     * Default constructor
     */
    public Reservation() {
        this(null);
    }

    public Reservation(Location location) {
        this(null, location);
    }

    public Reservation(Tenant tenant, Location location) {
        this(tenant, location, null);
    }

    public Reservation(Tenant tenant, Location location, Interval interval) {
        this.tenant = tenant;
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
    public void removeService() {
        // TODO implement here
    }


    /**
     * Method needs to update Reservation in the Database.
     * We have to instantiate a new Reservation with update attributes
     *
     * @see it.ispw.efco.nottitranquille.model.dao.ReservationDAO
     * @param toUpdate: Reservation to update in database
     */
    public void update(Reservation toUpdate) {
        this.id = toUpdate.getId();
    }

    /* Getter and Setter */

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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

}