package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * Created by claudio on 7/10/16.
 */
public enum LocationType {
    SingleRoom("Single Room"),
    DoubleRoom("Double Room"),
    Suite("Suite");

    private String text;

    LocationType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
