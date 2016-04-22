package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Service;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Iterator;
import java.util.List;

/**
 * Bean for Location. View Class contains Location class
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationBean {

    private String id;

    private String name;
    private String description;

    private List<Interval> enablesDate;

    private List<Service> services;

    public void populate(String id) {

        Long ID = new Long(id);

        //find Location from database to show it's details
        Location location = LocationDAO.findByID(ID);

        this.id = id;
        this.name = location.getName();
        this.description = location.getDescription();
        this.enablesDate = location.getAvailableDate();
        this.services = location.getServices();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnablesDate() {
        String enable = "";

        Iterator<Interval> availableInterval = enablesDate.iterator();
        while (availableInterval.hasNext()) {
            Interval interval = availableInterval.next();
            DateTime start = interval.getStart();
            DateTime end = interval.getEnd();

            enable += String.format(" {from : [ %d, %d, %d ] , to [%d,%d,%d] }",
                    start.getYear(), start.getMonthOfYear() - 1, start.getDayOfMonth() - 1,
                    end.getYear(), end.getMonthOfYear() - 1, end.getDayOfMonth() - 1);


            if (availableInterval.hasNext())
                enable += " , ";
        }

        return enable;
    }

    public List<Service> getServices() {
        return services;
    }
}
