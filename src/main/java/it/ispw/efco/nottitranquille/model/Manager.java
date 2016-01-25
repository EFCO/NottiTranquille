package it.ispw.efco.nottitranquille.model;

import javax.management.Notification;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Manager extends Applicant implements Notifiable{

    @Transient
    List<Notification> notifications;

    /**
     * Default constructor
     */
    public Manager() {
        notifications=new ArrayList<Notification>();
    }


    public boolean sendNotification(Notification notification) {
        //TODO Select Type of Notification from property file
        if(notification.getType()=="ReservationNotify"){
            notifications.add(notification);
            return true;
        }

        return false;
    }

}