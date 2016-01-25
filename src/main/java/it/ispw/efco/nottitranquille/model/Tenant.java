package it.ispw.efco.nottitranquille.model;

import javax.management.Notification;
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
        notifications = new ArrayList<Notification>();
    }


    @Transient
    List<Notification> notifications;

    @OneToMany
    @JoinTable(name = "Tenant_Reservation",
            joinColumns = {@JoinColumn(name = "ReservationId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TenantsId", referencedColumnName = "id")})
    public List<Reservation> reservations;

    public void update(Tenant toUpdate) {
        this.setId(toUpdate.getId());
        this.setFirstName(toUpdate.getFirstName());
        this.setLastName(toUpdate.getLastName());
        this.setUserName(toUpdate.getUserName());
        this.setPassword(toUpdate.getPassword());

        this.reservations = toUpdate.getReservations();


    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean sendNotification(Notification notification) {
        //TODO Select Type of Notification from property file
        if (notification.getType() == "ReservationNotify") {
            notifications.add(notification);
            return true;
        }

        return false;
    }
}