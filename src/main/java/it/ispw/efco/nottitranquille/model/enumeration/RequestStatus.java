package it.ispw.efco.nottitranquille.model.enumeration;

import it.ispw.efco.nottitranquille.model.Request;

/**
 * This enumeration represents the possibility state of a {@link Request}.
 <p>
 * Type that can be used:
 *  <li>{@link #ACCEPTED}</li>
 *  <li>{@link #REJECTED}</li>
 *  <li>{@link #TO_BE_REVIEWED}</li>
 * </p>
 * @see Request
 * @see it.ispw.efco.nottitranquille.controller.ApproveInsertRequest
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public enum RequestStatus {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    TO_BE_REVIEWED("To be reviewed");

    private String text;

    RequestStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}