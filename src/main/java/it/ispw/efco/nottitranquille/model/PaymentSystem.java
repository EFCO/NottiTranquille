package it.ispw.efco.nottitranquille.model;

/**
 * Stub for the payment of a Reservation.
 * The class represents an external payment system.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentSystem {
    private static PaymentSystem ourInstance = new PaymentSystem();

    public static PaymentSystem getInstance() {
        return ourInstance;
    }

    private PaymentSystem() {
    }

    /**
     * @param usernameFrom {@link Person#username} of the payer
     * @param name         Name of who receives the payment.
     * @param amount       Amount of money to transfer
     * @return true if procedure end successful
     */
    public boolean makePayment(String usernameFrom, String name, Float amount) {
        System.out.println(String.format("Payment is made from: %s to: %s of &f euro",
                usernameFrom, name, amount));

        return true;
    }

}
