package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentControl {
    private static PaymentControl ourInstance = new PaymentControl();

    public static PaymentControl getInstance() {
        return ourInstance;
    }

    private PaymentControl() {
    }

    public void pay(Long ReservationId, String tenantUsername) throws IllegalBookingDate {
        Reservation reservation = ReservationDAO.findByID(ReservationId);
        Person manager = reservation.getLocation().getManager();

        if (!reservation.getLocation().isAvailable(reservation.getPeriod())) {

            // Remove reservation because period is no longer available
            ReservationController controller = ReservationController.getInstance();
            controller.remove(reservation.getId());

            throw new IllegalBookingDate("The period specified is not already available ");

        }

        try {
            reservation.getLocation().bookPeriod(reservation.getPeriod());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalBookingDate("The period specified is not already available ");
        }

        // makes payment by a stub
        PaymentSystem paymentSystem = PaymentSystem.getInstance();
        paymentSystem.makePayment(tenantUsername, manager.getfirstname() + " " + manager.getlastname(),
                reservation.getPrice());

        reservation.setState(ReservationState.Paid);
        LocationDAO.update(reservation.getLocation()); //update available date
        ReservationDAO.update(reservation);     // update state of reservation
    }
}
