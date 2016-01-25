package it.ispw.efco.nottitranquille.controller;


import it.ispw.efco.nottitranquille.model.BankingSystem;
import it.ispw.efco.nottitranquille.model.PaymentData;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.Transaction;

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

    public void pay(Reservation reservation, PaymentData data){
        if(BankingSystem.check(data)==true){

            Transaction transaction = new Transaction(reservation.getPrice(), data.getIntestatario());
            BankingSystem.store(transaction);

        }else{
            //TODO
        }

    }
}
