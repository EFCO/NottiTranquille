package it.ispw.efco.nottitranquille.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.loader.plan.exec.internal.AbstractLoadPlanBasedLoader;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("tenant")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Tenant extends Role {

    /**
     * Default constructor
     */
    public Tenant() {
        reservations = new ArrayList<Reservation>();
    }

    @OneToMany
    @JoinTable(name = "Tenant_Reservation",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TenantsId", referencedColumnName = "id")})
    @Cascade(CascadeType.ALL)
    private List<Reservation> reservations;

    /**
     * method for TenantDAO: Needs to update information in database
     *
     * @param toUpdate: Class to update
     */
    public void update(Tenant toUpdate) {
        this.reservations = toUpdate.getReservations();
    }

    public boolean addReservation(Reservation reservation) {
        return reservations.add(reservation);
    }

    public boolean deleteReservation(Reservation reservation) {
        return reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}