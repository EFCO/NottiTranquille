package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * 
 */
public enum RequestStatus {
    Accepted("Accepted"),
    Rejected("Rejected"),
    To_be_reviewed("To be reviewed"),
    Unknown("Unknown");

    private String text;
    RequestStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}