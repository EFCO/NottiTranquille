package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.RegisteredUserDAO;
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

    public void pay(Long ReservationId, Long TenantId) {
        Person tenant = (Person) RegisteredUserDAO.findbyId(TenantId, Tenant.class);
        Reservation reservation = ReservationDAO.findByID(ReservationId);

        BankingSystem bank = BankingSystem.getInstance();
        bank.transfer(tenant, null, reservation.getPrice());
        System.out.println("Payment is made");

        Location location = reservation.getLocation();

        try {
            location.bookPeriod(reservation.getPeriod());
        } catch (IllegalArgumentException e) {
            // TODO: 21/06/16 throw a new Excepton
        }

        reservation.setState(ReservationState.Paid);
        ReservationDAO.update(reservation);
    }
}
