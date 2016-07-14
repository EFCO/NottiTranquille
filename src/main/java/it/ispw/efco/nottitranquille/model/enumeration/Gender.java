package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * 
 */
public enum Gender {
    Male("Male"),
    Female("Female");

    private String text;
    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}