package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * Created by Federico on 22/01/2016.
 */
public enum Commodities {
    Wifi("WiFi"),
    AirConditioner("Air Conditioner"),
    Strongbox("Strongbox");

    private String text;
    Commodities(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
