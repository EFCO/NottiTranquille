package it.ispw.efco.nottitranquille.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Stub for payment of a reservation.
 * Class Singleton represents an external entity like a Bank. Usefull to simulate a payment for
 * a Reservation.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class BankingSystem {
    private static BankingSystem ourInstance = new BankingSystem();

    public static BankingSystem getInstance() {
        return ourInstance;
    }

    HashMap<String, BankingAccount> accounts; // Each person matches his account.

    /**
     * Default constructor
     */
    public BankingSystem() {
        accounts = new HashMap<String, BankingAccount>();
    }

    /**
     * @param person: Test if exist an account for this person.
     * @return boolean
     */
    public boolean existAccountFor(String person) {
        if (accounts.containsKey(person))
            return true;

        return false;
    }

    /**
     * Create a new BankingAccount. The amount of money is set on 0 initially
     *
     * @param person: holder for the account.
     */
    public void createAccount(String person) {
        accounts.put(person, new BankingAccount(person));
    }

    public List<Transaction> getAllTransaction(String holder) throws NoSuchFieldException {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        BankingAccount account = accounts.get(holder);
        if (account == null)
            throw new NoSuchFieldException("holder has no account");

        return account.getTransactions();

    }

    /**
     * Move money from an account to another and register a Transaction in them.
     *
     * @param from:   account from the moneys are picked up
     * @param to:     account where the moneys are put
     * @param amount: quantity of money
     * @throws IllegalArgumentException
     */
    public void transfer(BankingAccount from, BankingAccount to, float amount) throws IllegalArgumentException {

        if (from.getAmount() < amount)
            throw new IllegalArgumentException("Amount not availabe");

        Transaction transaction = new Transaction(amount, to.getHolder(), from.getHolder());
        from.appendTransaction(transaction);
        to.appendTransaction(transaction);

        from.decreaseAmount(amount);
        to.increaseAmount(amount);
    }

}


