package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Tenant extends RegisteredUser implements Notifiable {

    /**
     * Default constructor
     */
    public Tenant() {
        reservations = new ArrayList<Reservation>();
        notifications = new ArrayDeque<Notification>();
    }


    @Transient
    private Deque<Notification> notifications;

    @OneToMany
    @JoinTable(name = "Tenant_Reservation",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TenantsId", referencedColumnName = "id")})
    public List<Reservation> reservations;

    /**
     * method for TenantDAO: Needs to update information in database
     *
     * @param toUpdate: Class to update
     */
    public void update(Tenant toUpdate) {
        super.update(toUpdate);
        this.reservations = toUpdate.getReservations();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    //TODO exception if it not exist
    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void sendNotification(Notification notification) {
        //TODO Select Type of Notification from property file
        notifications.push(notification);
    }

}