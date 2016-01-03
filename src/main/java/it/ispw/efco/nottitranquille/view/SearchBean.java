package it.ispw.efco.nottitranquille.view;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Federico on 30/12/2015.
 */
public class SearchBean {
    private String nation = "";
    private String city = "";
    private DateTime checkin = null;
    private DateTime checkout = null;
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

    public void setCheckin(String checkin) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");
        this.checkin = DateTime.parse(checkin, dateTimeFormatter);
    }

    public void setCheckout(String checkout) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");
        this.checkout = DateTime.parse(checkout, dateTimeFormatter);
        ;
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public boolean validate() {
        if(this.nation.equals("") || this.city.equals("") || this.checkin == null|| this.checkout == null || this.pricerange.equals("")) {
            return false;
        }
        return true;
    }
}
