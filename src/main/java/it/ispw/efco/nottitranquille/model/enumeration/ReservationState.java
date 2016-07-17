package it.ispw.efco.nottitranquille.model.enumeration;

/**
 *
 */
public enum ReservationState {
    Paid("Paid"),
    ToPay("ToPay"),
    Init("Init"),
    Declined("Decline"),
    ToApprove("ToApprove");

    private String text;

    ReservationState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}