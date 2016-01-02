package it.ispw.efco.nottitranquille.view;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Federico on 30/12/2015.
 */
public class SearchBean {
    private String nation = "";
    private String city = "";
    private DateTime checkin;
    private DateTime checkout;
    private String pricerange = "";

    public SearchBean() {
    }

    public String getNation() {
        return nation;
    }

    public String getCity() {
        return city;
    }

    public DateTime getCheckin() {
        return checkin;
    }

    public DateTime getCheckout() {
        return checkout;
    }

    public String getPricerange() {
        return pricerange;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCheckin(DateTime checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(DateTime checkout) {
        this.checkout = checkout;
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public boolean validate() {
        System.out.println("Nazione:" + this.nation + "\nCittà: " + this.checkin + this.checkout + this.city + this.pricerange);
        if(this.nation.equals("") || this.city.equals("") || this.checkin == null|| this.checkout == null || this.pricerange.equals("")) {
            return false;
        }
        return true;
    }
}
