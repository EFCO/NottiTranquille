package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearch;
import it.ispw.efco.nottitranquille.model.*;
import org.joda.time.DateTime;
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
    private String locationtype = "";
    private String maxtenant = "";
    private String search="";
    private String[] commodities = null;

    public void setCommodities(String[] commodities) {
        this.commodities = commodities;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //TODO Implement a way to retrieve commodities
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.checkin = DateTime.parse(checkin, dateTimeFormatter);
    }

    public void setCheckout(String checkout) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.checkout = DateTime.parse(checkout, dateTimeFormatter);
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public boolean validate() {
        //If the search is a basic one I have to check the presence of all the fields.
        this.result = new ArrayList<Location>();
        if(search == "search" && (this.nation.equals("") && this.city.equals("") && this.checkin == null && this.checkout == null)) {
            return false;
        }
        System.out.println("commodities = " + commodities.toString());
//        Address a = new Address("Roma","Zagarolo","piazza di casa mia","00039");
//        Structure s = new Structure("casa mia", a);
//        List<Interval> booking = new ArrayList<Interval>();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
//        DateTime start = DateTime.parse("01-01-2016", dateTimeFormatter);
//        DateTime end = DateTime.parse("30-12-2016", dateTimeFormatter);
//        booking.add(new Interval(start,end));
//        Location loc = new Location(booking,s,5, LocationType.Hotel);
//        Request r = new Request(s);
//        CatalogueDAO catalogueDAO = new CatalogueDAO();
//        catalogueDAO.saveRequest(r);
        try {
            this.result = FilteredSearch.getListOfStructures(this);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getMaxtenant() {
        return maxtenant;
    }

    public void setMaxtenant(String maxtenant) {
        this.maxtenant = maxtenant;
    }

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

    public String[] getCommodities() {
        return commodities;
    }
}
