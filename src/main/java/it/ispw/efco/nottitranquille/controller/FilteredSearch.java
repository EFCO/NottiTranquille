package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.RequestDAO;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import it.ispw.efco.nottitranquille.model.enumeration.PriceRanges;
import it.ispw.efco.nottitranquille.view.SearchBean;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static HashMap<Structure, ArrayList<Location>> getListOfLocations(SearchBean searchBean) throws Exception {
        if (searchBean.getCheckin().isAfter(searchBean.getCheckout()) && searchBean.getCheckin().isBeforeNow()) {
            throw new Exception("Invalid datetime");
        }

        //TODO to use
        int maxPrice = PriceRanges.valueOf(searchBean.getPricerange()).getMaxvalue();

        Interval interval = new Interval(searchBean.getCheckin(), searchBean.getCheckout());
        LocationType type = null;
        int maxTenant = 0;
        if (searchBean.getSearch().equals("advsearch")) {
            type = LocationType.valueOf(searchBean.getLocationtype());
            maxTenant = Integer.valueOf(searchBean.getMaxtenant());
        }
        RequestDAO requestDAO = new RequestDAO();
        List<Location> result = requestDAO.selectAcceptedRequests(searchBean.getNation(), searchBean.getCity());
        HashMap<Structure, ArrayList<Location>> final_result = new HashMap<Structure, ArrayList<Location>>();
        for (Location location : result) {
            if (location.isAvailable(interval)) {
                //in case of advanced search
                if (searchBean.getSearch().equals("advsearch") && location.getMaxGuestsNumber() >= maxTenant && (type == LocationType.NessunaPreferenza || type == location.getType())) {
                    if (final_result.containsKey(location.getStructure())) {
                        final_result.get(location.getStructure()).add(location);
                    } else {
                        ArrayList<Location> arrayList = new ArrayList<Location>();
                        arrayList.add(location);
                        final_result.put(location.getStructure(), arrayList);
                    }
                } else if (searchBean.getSearch().equals("search")) {
                    if (final_result.containsKey(location.getStructure())) {
                        final_result.get(location.getStructure()).add(location);
                    } else {
                        ArrayList<Location> arrayList = new ArrayList<Location>();
                        arrayList.add(location);
                        final_result.put(location.getStructure(), arrayList);
                    }
                }
            }
        }
        return final_result;
    }

    public static Location getLocationWithId(Long id) {
        RequestDAO requestDAO = new RequestDAO();
        Location results = requestDAO.selectLocationWithId(id);
        return results;
    }

}