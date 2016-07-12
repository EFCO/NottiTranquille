package it.ispw.efco.nottitranquille.model.Exception;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class IllegalBookingDate extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IllegalBookingDate(String message) {
        super(message);
    }

    public IllegalBookingDate(Throwable cause) {
        super(cause);
    }

    public IllegalBookingDate(String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
