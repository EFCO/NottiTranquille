package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.Exception.IllagalBookingDate;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDAO;
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

    public void pay(Long ReservationId, String tenantUsername) throws IllagalBookingDate {
        Person tenant = TenantDAO.findByUserName(tenantUsername);
        Reservation reservation = ReservationDAO.findByID(ReservationId);

        BankingSystem bank = BankingSystem.getInstance();
        bank.transfer(tenant, null, reservation.getPrice());
        System.out.println("Payment is made");

        Location location = reservation.getLocation();

        try {
            location.bookPeriod(reservation.getPeriod());
        } catch (IllegalArgumentException e) {
            throw new IllagalBookingDate("The period specified is not already available ", e.getCause());
        }

        reservation.setState(ReservationState.Paid);
        ReservationDAO.update(reservation);
    }
}
