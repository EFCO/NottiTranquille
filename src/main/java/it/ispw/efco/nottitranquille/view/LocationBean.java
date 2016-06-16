package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Service;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Iterator;
import java.util.List;

/**
 * Bean for Location. Boundary class that contains information for {@link Location}
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationBean {

    private String id;

    /**
     * @see Location#name
     */
    private String name;

    /**
     * @see Location#description
     */
    private String description;

    /**
     * @see Location#availableDate
     */
    private List<Interval> enablesDate;

    /**
     * @see Location#services
     */
    private List<Service> services;

    public void populate(String id) {

        Long ID = new Long(id);

        ReservationController controller = ReservationController.getInstance();
        controller.fillLocationBean(this, ID);

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

            enable += String.format(" {from : [ %d, %d, %d ] , to: [%d,%d,%d] }",
                    start.getYear(), start.getMonthOfYear() - 1, start.getDayOfMonth() - 1,
                    end.getYear(), end.getMonthOfYear() - 1, end.getDayOfMonth() - 1);


            if (availableInterval.hasNext())
                enable += " , ";
        }

        return enable;
    }

    public void setEnablesDate(List<Interval> enablesDate) {
        this.enablesDate = enablesDate;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
