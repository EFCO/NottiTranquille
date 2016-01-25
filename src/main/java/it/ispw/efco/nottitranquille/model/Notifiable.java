package it.ispw.efco.nottitranquille.model;

import javax.management.Notification;

/**
 * Created by emanuele on 25/01/16.
 */
public interface Notifiable {

    boolean sendNotification(Notification notification);

}
