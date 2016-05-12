package it.ispw.efco.nottitranquille.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class reprensents a banking account for a Person. Usefull to simulate transactions and payment.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
//@Entity
public class BankingAccount {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String holderName;

    private float amount;

//    @OneToMany
    private List<Transaction> transactions;

    public BankingAccount() {
        this(null);
    }

    public BankingAccount(String holder) {
        this.holderName = holder;
        amount = 0;
        transactions = new ArrayList<Transaction>();
    }

    public void appendTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * The holder of the account puts in moneys.
     *
     * @param amount: quantity of meneys
     * @throws InvalidArgumentException
     */
    public void payIn(float amount) throws InvalidArgumentException {

        if (amount < 0)
            throw new InvalidArgumentException(new String[]{"amount < 0"});

        this.amount += amount;
        transactions.add(new Transaction(amount));
    }

    /**
     * The holder of the account gets moneys.
     *
     * @param amount: quantity of moneys
     * @return boolean: true if amount is available
     * @throws InvalidArgumentException
     */
    public boolean payDown(Float amount) throws InvalidArgumentException {

        if (amount < 0)
            throw new InvalidArgumentException(new String[]{"amount < 0"});

        if (this.amount >= amount) {
            this.amount -= amount;
            transactions.add(new Transaction(-amount));
            return true;
        }

        return false;
    }

    public void increaseAmount(float amount) {
        this.amount += amount;
    }

    public void decreaseAmount(float amount) {
        this.amount -= amount;
    }

//    public Long getId() {
//        return id;
//    }

    public String getHolder() {
        return holderName;
    }

    public float getAmount() {
        return amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
