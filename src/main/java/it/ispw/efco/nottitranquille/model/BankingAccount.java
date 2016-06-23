package it.ispw.efco.nottitranquille.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class reprensents a banking account for a Person. Useful to simulate transactions and payment.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
//@Entity
public class BankingAccount {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private Long holderId;

    private String holderFirstname;

    private String holderLastname;

    private float amount;

    //    @OneToMany
    private List<Transaction> transactions;

    public BankingAccount(Long personID, String firstname, String lastname) {
        this.holderId = personID;
        this.holderFirstname = firstname;
        this.holderLastname = lastname;
        amount = 0;
        transactions = new ArrayList<Transaction>();
    }

    public void appendTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void increaseAmount(Float amount) {
        this.amount += amount;
    }

    public void decreaseAmount(Float amount) throws IllegalArgumentException {
        if (this.amount < amount)
            throw new IllegalArgumentException(" balance can't be negative ");
        this.amount -= amount;
    }

    public Long getHolderId() {
        return holderId;
    }

    public String getHolderFirstname() {
        return holderFirstname;
    }

    public String getHolderLastname() {
        return holderLastname;
    }

    public float getAmount() {
        return amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
