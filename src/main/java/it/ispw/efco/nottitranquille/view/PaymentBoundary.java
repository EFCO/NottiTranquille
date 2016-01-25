package it.ispw.efco.nottitranquille.view;


import it.ispw.efco.nottitranquille.controller.PaymentControl;
import it.ispw.efco.nottitranquille.model.PaymentData;
import it.ispw.efco.nottitranquille.model.Reservation;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentBoundary {

    private Reservation reservation;

    private String intestatario;

    private String number;

    private int code;


    /**
     * Default constructor
     */
    public PaymentBoundary() {
    }


    public boolean validate(){
        if(intestatario!="" && intestatario!=null) {

            PaymentData data = new PaymentData(intestatario, number, code);
            PaymentControl.getInstance().pay(reservation, data);
            return true;
        }

        return false;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(String intestatario) {
        this.intestatario = intestatario;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}