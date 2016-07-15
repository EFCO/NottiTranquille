package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * Created by claudio on 7/10/16.
 */
public enum LocationType {
    BedBreakfast("Bed & Breakfast"),
    Hotel("Hotel"),
    Agriturismo("Agriturismo"),
    NessunaPreferenza("Nessuna Preferenza");

    private String text;

    LocationType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
