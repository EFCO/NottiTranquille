package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Manager extends Applicant implements Notifiable {

    @Transient
    private Deque<Notification> notifications;

    @OneToMany
    private Deque<Reservation> toApprove;

    private int newNotification;
    private int reservationToApprove;

    /**
     * Default constructor
     */
    public Manager() {
        notifications = new ArrayDeque<Notification>();
        toApprove = new ArrayDeque<Reservation>();

        newNotification=0;
        reservationToApprove=0;
    }

    public void addReservationToApprove(Reservation reservation){
        toApprove.push(reservation);
        reservationToApprove+=1;
    }

    public void sendNotification(Notification notification){
        //TODO Select Type of Notification from property file
        notifications.push(notification);
        newNotification+=1;
    }



}