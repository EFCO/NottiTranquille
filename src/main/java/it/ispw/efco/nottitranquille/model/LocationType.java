package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;

import javax.persistence.*;
import java.util.*;

/**
 * @see ReservationType
 * @see Location
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class LocationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "Location_Service",
            joinColumns = {@JoinColumn(name = "LocationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ServiceId", referencedColumnName = "id")})
    private List<Service> services;

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


}