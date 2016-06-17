package it.ispw.efco.nottitranquille.model.enumeration;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
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