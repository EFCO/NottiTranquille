package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * 
 */
public enum ReservationState {
    Paid("Paid"),
    ToPay("To Pay"),
    Timeout("Timeout"),
    Unknown("Unknown");

    private String text;
    ReservationState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}