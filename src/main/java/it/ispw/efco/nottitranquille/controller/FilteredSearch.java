package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.CatalogueDAO;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import it.ispw.efco.nottitranquille.view.SearchBean;
import org.joda.time.Interval;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class FilteredSearch {

    /**
     * Default constructor
     */
    public FilteredSearch() {
    }

    public static List<Location> getListOfStructures(SearchBean searchBean) throws Exception {
        if (searchBean.getCheckin().isAfter(searchBean.getCheckout()) && searchBean.getCheckin().isBeforeNow()) {
            throw new Exception(); //TODO to improve
        }
        int maxPrice = 0; //0 stands for infinite
        if (searchBean.getPricerange().equals("Fino a 100 euro")) {
            maxPrice = 100;
        }
        if (searchBean.getPricerange().equals("Fino a 200 euro")) {
            maxPrice = 200;
        }
        if (searchBean.getPricerange().equals("Fino a 500 euro")) {
            maxPrice = 500;
        }
        Interval interval = new Interval(searchBean.getCheckin(),searchBean.getCheckout());
        LocationType type = LocationType.valueOf(searchBean.getLocationtype());
        int maxTenant = Integer.valueOf(searchBean.getMaxtenant());

        CatalogueDAO catalogueDAO = new CatalogueDAO();
        List<Request> result = catalogueDAO.selectAcceptedRequests(searchBean.getNation(),searchBean.getCity());
        System.out.println(result);
        List<Location> final_result = new ArrayList<Location>();
        for (Request candidate : result) {
            for (Location location : candidate.getStructure().getLocations()) {
                if (location.isAvailable(interval) && location.getType() == type && location.getMaxGuestsNumber() >= maxTenant) { //TODO price check to be added
                    final_result.add(location);
                }
            }
        }
        return final_result;
    }

    public static Location getLocationWithId(Long id) {
        CatalogueDAO catalogueDAO = new CatalogueDAO();
        Location results = catalogueDAO.selectLocationWithId(id);
        return results;
    }

}