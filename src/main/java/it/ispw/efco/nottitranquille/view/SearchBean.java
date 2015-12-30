package it.ispw.efco.nottitranquille.view;

import java.util.Date;

/**
 * Created by Federico on 30/12/2015.
 */
public class SearchBean {
    private String nation = "";
    private String city = "";
    private Date checkin = null;
    private Date checkout = null;
    private String pricerange = "";

    public SearchBean() {
    }

    public String getNation() {
        return nation;
    }

    public String getCity() {
        return city;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
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

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public boolean validate() {
        if(this.nation.equals("") || this.city.equals("") || this.checkin == null || this.checkout == null || this.pricerange.equals("")) {
            return false;
        }
        return true;
    }
}
