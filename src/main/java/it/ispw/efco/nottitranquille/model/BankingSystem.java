package it.ispw.efco.nottitranquille.model;


import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class BankingSystem {

    /**
     * Default constructor
     */
    public BankingSystem() {
    }


    /**
     * 
     */
    public static boolean check(PaymentData data) {


        return false;
    }

    /**
     * 
     */
    public static void store(Transaction transaction) {
        //TODO append in database the transaction
    }

}