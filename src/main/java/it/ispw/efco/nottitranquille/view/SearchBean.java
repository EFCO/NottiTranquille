package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearch;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    private String search = "";
    private int[] commodities = null; //new String[Commodities.values().length];

    public void setCommodities(int[] commodities) {
        this.commodities = commodities;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    private HashMap<Structure, ArrayList<Location>> result = new HashMap<Structure, ArrayList<Location>>();


    public HashMap<Structure, ArrayList<Location>> getResult() {
        return result;
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

    @Override
    public String toString() {
        return "SearchBean{" +
                "nation='" + nation + '\'' +
                ", city='" + city + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", pricerange='" + pricerange + '\'' +
                ", locationtype='" + locationtype + '\'' +
                ", maxtenant='" + maxtenant + '\'' +
                ", search='" + search + '\'' +
                ", commodities=" + Arrays.toString(commodities) +
                ", result=" + result +
                '}';
    }

    public void validate() throws Exception {
        //If the search is a basic one I have to check the presence of all the fields.
        this.result = new HashMap<Structure, ArrayList<Location>>();
        if(search == "search" && (this.nation.equals("") && this.city.equals("") && this.checkin == null && this.checkout == null)) {
            throw new Exception("Incorrect input data");
        }
//        Address a = new Address("Roma","Zagarolo","piazza di casa mia","00039");
//        Structure s = new Structure("casa mia", a);
//        List<Interval> booking = new ArrayList<Interval>();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
//        DateTime start = DateTime.parse("01-01-2016", dateTimeFormatter);
//        DateTime end = DateTime.parse("30-12-2016", dateTimeFormatter);
//        booking.add(new Interval(start,end));
//        Location loc = new Location(booking,s,5, LocationType.Hotel);
//        Request r = new Request(s);
//        RequestDAO catalogueDAO = new RequestDAO();
//        catalogueDAO.saveRequest(r);

        try {
            this.result = FilteredSearch.getListOfLocations(this);
        } catch (Exception e) {
            throw e;
        }
    }

    public String api_result() {
        JSONObject jsonObject = new JSONObject();
        try {
            this.validate();
            jsonObject.put("code",1);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < result.size(); i++) {
                JSONObject obj = new JSONObject();
//TODO to fix                Location currentElem = result.get(i);
//                obj.put("id", String.valueOf(i));
//                obj.put("name", currentElem.getStructure().getName());
//                obj.put("type", currentElem.getType().toString());
//                obj.put("address", currentElem.getLocationAddress());
//                obj.put("nation", currentElem.getStructure().getStructureAddress().getNation());
//                obj.put("city", currentElem.getStructure().getStructureAddress().getCity());
                obj.put("price", (25 + i) % 5);//TODO use the right price
                jsonArray.put(obj);
            }
            jsonObject.put("results",jsonArray);
            return jsonObject.toString();
        } catch (Exception e) {
            jsonObject.put("code",0);
            JSONArray jsonArray = new JSONArray(result);
            jsonObject.put("results",jsonArray);
            return jsonObject.toString();
        }



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

    public int[] getCommodities() {
        return commodities;
    }
}
