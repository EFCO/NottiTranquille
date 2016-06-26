package it.ispw.efco.nottitranquille.model.Exception;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class IllagalBookingDate extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IllagalBookingDate(String message) {
        super(message);
    }

    public IllagalBookingDate(Throwable cause) {
        super(cause);
    }

    public IllagalBookingDate(String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
