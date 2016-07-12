package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.PaymentControl;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentForm {

    private String reservationID;

    private String tenantUsername;


    public boolean validate() {

        if (reservationID == null || tenantUsername == null)
            return false;

        PaymentControl controller = PaymentControl.getInstance();

        try {
            controller.pay(new Long(reservationID), tenantUsername);
        } catch (IllegalBookingDate e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }
}