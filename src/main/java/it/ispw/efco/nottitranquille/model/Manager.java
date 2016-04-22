package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.*;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Manager extends Applicant implements Notifiable {

    @Transient
    private Deque<Notification> notifications;

    @OneToMany
    // Bound to use linked list and not Deque because it is not supported from JPA.
    private List<Reservation> toApprove;

    private int newNotification;
    private int reservationToApprove;

    /**
     * Default constructor
     */
    public Manager() {
        notifications = new ArrayDeque<Notification>();
        toApprove = new ArrayList<Reservation>();

        newNotification = 0;
        reservationToApprove = 0;
    }

    public boolean addReservationToApprove(Reservation reservation) {
        //TODO EXCEPTION
        for (Reservation r : toApprove)
            if (r.equals(reservation))
                return false;

        toApprove.add(reservation);
        reservationToApprove += 1;

        return true;
    }

    public boolean deleteReservationToApprove(Reservation reservation) {
        Iterator list = toApprove.iterator();
        while (list.hasNext()) {
            Reservation r = (Reservation) list.next();

            if (r.equals(reservation)) {
                list.remove();
                return true;
            }
        }

        return false;
    }

    public void sendNotification(Notification notification) {
        //TODO Select Type of Notification from property file
        notifications.push(notification);
        newNotification += 1;
    }

    public void update(Manager toUpdate) {
        super.update(toUpdate);
        this.newNotification = toUpdate.getNewNotification();
        this.reservationToApprove = toUpdate.getReservationToApprove();

        this.toApprove = toUpdate.getToApprove();
        this.notifications = toUpdate.getNotifications();
    }

    public Deque<Notification> getNotifications() {
        return notifications;
    }

    public List<Reservation> getToApprove() {
        return toApprove;
    }

    public int getNewNotification() {
        return newNotification;
    }

    public int getReservationToApprove() {
        return reservationToApprove;
    }
}