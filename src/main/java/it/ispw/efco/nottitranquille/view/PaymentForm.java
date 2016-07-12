package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.PaymentControl;
import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;
import it.ispw.efco.nottitranquille.model.Reservation;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentForm {

    private ReservationBean reservation;

    private String tenantUsername;


    public boolean validate() {

        if (reservation.getId() == null || tenantUsername == null)
            return false;

        PaymentControl controller = PaymentControl.getInstance();

        try {
            controller.pay(new Long(reservation.getId()), tenantUsername);
        } catch (IllegalBookingDate e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void populate(String reservationId) {

        ReservationController controller = ReservationController.getInstance();
        // ask for reservation matching the id
        Reservation reservation = controller.findReservation(new Long(reservationId));

        // populate the ben
        ReservationBean reservationBean = new ReservationBean();
        reservationBean.populate(reservation);

        this.reservation = reservationBean;
    }


    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public ReservationBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationBean reservation) {
        this.reservation = reservation;
    }
}