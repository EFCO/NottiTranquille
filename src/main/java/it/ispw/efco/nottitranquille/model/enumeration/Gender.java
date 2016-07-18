package it.ispw.efco.nottitranquille.model.enumeration;

import it.ispw.efco.nottitranquille.model.Person;

/**
 * This enumeration represent the gender of a {@link Person}.
 * <p>
 * Type that can be used:
 *  <li>{@link #MALE}</li>
 *  <li>{@link #FEMALE}</li>
 * </p>
 * @see Person
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String text;
    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}