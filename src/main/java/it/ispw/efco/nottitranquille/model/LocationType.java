package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 * @see ReservationType
 * @see Location
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class LocationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @ManyToMany
//    @JoinTable(name = "LocationType_Service",
//            joinColumns = {@JoinColumn(name = "LocationTypeId", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "ServiceId", referencedColumnName = "id")})
    @Transient
    private List<Service> services;

    @Enumerated
    private ReservationType reservationType;

    /**
     * Default constructor
     */
    public LocationType() {
        this(null);
    }

    public LocationType(ReservationType reservationType) {
        this(null, reservationType);
    }

    public LocationType(List<Service> services, ReservationType reservationType) {
        this.services = services;
        this.reservationType = reservationType;
    }


    public void update(LocationType toUpdate) {
        this.id = toUpdate.getId();
        this.services = toUpdate.getServices();
        this.reservationType = toUpdate.getReservationType();
    }

    /* Getter and Setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}