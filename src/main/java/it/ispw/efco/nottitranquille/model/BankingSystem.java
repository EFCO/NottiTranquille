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

    HashMap<Long, BankingAccount> accounts; // Each person matches his account.

    /**
     * Default constructor
     */
    public BankingSystem() {
        accounts = new HashMap<Long, BankingAccount>();
    }

    /**
     * Create a new BankingAccount. The amount of money is set on 0 initially
     *
     * @param id identifies holder for the account.
     * @return the account created
     */
    public BankingAccount createAccount(Long id, String firstname, String lastname) {
        BankingAccount account = new BankingAccount(id, firstname, lastname);
        accounts.put(id, account);

        return account;
    }

    public List<Transaction> getAllTransaction(Long holderId) throws NoSuchFieldException {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        BankingAccount account = accounts.get(holderId);
        if (account == null)
            throw new NoSuchFieldException("holder has no account");

        return account.getTransactions();

    }

    public void transfer(BankingAccount from, BankingAccount to, Float amount) {
        Transaction transaction = new Transaction(amount, to, from);
        try {
            from.decreaseAmount(amount);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        to.increaseAmount(amount);
        to.appendTransaction(transaction);
        from.appendTransaction(transaction);
    }

    public void transfer(Person from, Person to, Float amount) {

        BankingAccount accountFrom;
        BankingAccount accountTo;

        if (!existAccountFor(from.getId())) {
            accountFrom = createAccount(from.getId(), from.getFirstName(), from.getLastName());
        } else {
            accountFrom = accounts.get(from.getId());
        }

        if (!existAccountFor(to.getId())) {
            accountTo = createAccount(to.getId(), to.getFirstName(), to.getLastName());
        } else {
            accountTo = accounts.get(to.getId());
        }

        this.transfer(accountFrom, accountTo, amount);

    }

    /**
     * @param id: Test if exist an account for this person.
     * @return boolean
     */
    private boolean existAccountFor(Long id) {
        return accounts.containsKey(id);
    }

}


