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

    private Float amount;

    private BankingAccount to;

    private BankingAccount from;

    private Long idCausale;

    /**
     * Default constructor
     */
    public Transaction() {
        this(null, null, null);
    }

    public Transaction(Float amount, BankingAccount to, BankingAccount from) {
        this(amount, to, from, null);
    }

    public Transaction(Float amount, BankingAccount to, BankingAccount from, Long causale) {
        this.amount = amount;
        this.to = to;
        this.from = from;
        this.idCausale = causale;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public BankingAccount getTo() {
        return to;
    }

    public void setTo(BankingAccount to) {
        this.to = to;
    }

    public BankingAccount getFrom() {
        return from;
    }

    public void setFrom(BankingAccount from) {
        this.from = from;
    }

    public Long getIdCausale() {
        return idCausale;
    }

    public void setIdCausale(Long idCausale) {
        this.idCausale = idCausale;
    }
}