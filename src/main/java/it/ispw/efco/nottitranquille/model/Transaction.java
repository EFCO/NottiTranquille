package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
//@Entity
public class Transaction {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private float amount;

    private String to;

    private String from;

    /**
     * Default constructor
     */
    public Transaction() {
    }

    public Transaction(float amount) {
        this(amount, null, null);
    }

    public Transaction(float amount, String to, String from) {
        this.amount = amount;
        this.to = to;
        this.from = from;
    }

//    public Long getId() {
//        return id;
//    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public float getAmount() {
        return amount;
    }
}