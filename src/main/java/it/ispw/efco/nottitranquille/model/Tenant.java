package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("TN")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Tenant extends RegisteredUser {

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
    private List<Reservation> reservations;

    /**
     * method for TenantDAO: Needs to update information in database
     *
     * @param toUpdate: Class to update
     */
    public void update(Tenant toUpdate) {
        super.update(toUpdate);
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