package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.CatalogueDAO;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.view.SearchBean;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class FilteredSearch {

    /**
     * Default constructor
     */
    public FilteredSearch() {
    }

    public static List<Request> getListOfRequests(SearchBean searchBean) {
        if (searchBean.getCheckin().isAfter(searchBean.getCheckout())) {
            //TODO throw an error
            return null;
        }
        CatalogueDAO catalogueDAO = new CatalogueDAO();
        List<Request> result = catalogueDAO.selectAcceptedRequestsByFilter(searchBean.getNation(),searchBean.getCity(),searchBean.getCheckin(),searchBean.getCheckout(),searchBean.getPricerange());
        return result;
    }



}