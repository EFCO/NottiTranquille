package it.ispw.efco.nottitranquille.model.enumeration;

/**
 * Created by Federico on 14/06/2016.
 */
public enum PriceRanges {
    max100(100,"Fino a 100 euro"),
    max200(200,"Fino a 200 euro"),
    max500(300,"Fino a 500 euro"),
    nolimit(0,"Nessun limite");

    private int maxvalue;
    private String text;

    PriceRanges(int maxvalue, String text) {
        this.maxvalue = maxvalue;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getMaxvalue() {
        return maxvalue;
    }

}
