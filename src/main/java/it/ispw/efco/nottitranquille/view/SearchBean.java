package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearch;
import it.ispw.efco.nottitranquille.model.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 30/12/2015.
 */
public class SearchBean {
    private String nation = "";
    private String city = "";
    private DateTime checkin = null;
    private DateTime checkout = null;
    private String pricerange = "";
    private List<Location> result = new ArrayList<Location>();


    public List<Location> getResult() {
        return result;
    }

    public void setResult(List<Location> result) {
        this.result = result;
    }



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
        System.out.println("checkin " + this.checkin.toString());
    }

    public void setCheckout(String checkout) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");
        this.checkout = DateTime.parse(checkout, dateTimeFormatter);
        System.out.println("checkout " + this.checkout.toString());
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public boolean validate() {
        if(this.nation.equals("") || this.city.equals("") || this.checkin == null|| this.checkout == null) {
            return false;
        }
//        Address a = new Address("Roma","Zagarolo","piazza di casa mia","00039");
//        Structure s = new Structure("casa mia", a);
//        List<Interval> booking = new ArrayList<Interval>();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");
//        DateTime start = DateTime.parse("2016-01-01", dateTimeFormatter);
//        DateTime end = DateTime.parse("2016-12-30", dateTimeFormatter);
//        booking.add(new Interval(start,end));
//        Location loc = new Location(booking,s);
//        Request r = new Request(s);
//        CatalogueDAO catalogueDAO = new CatalogueDAO();
//        catalogueDAO.saveRequest(r);
        try {
            this.result = FilteredSearch.getListOfStructures(this);
            for (Location location : result) {
                System.out.println("i risultati" + location.getStructure().toString());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
