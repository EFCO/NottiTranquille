package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.joda.time.DateTime;

/**
 * Bean describing {@link Reservation}.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ReservationBean {

    private String name; //Name of the Location

    // Interval of days in which the Location is reserved
    private DateTime startDate;
    private DateTime endDate;

    private ReservationState state;   // state of reservation
    private Float price; // price paid or to pay for location in the corresponding interval of days

    private String tenant; //Name of the Tenant

    private Long id; // Reservation's id

    public void populate(Reservation reservation) {
        this.name = reservation.getLocation().getName();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.state = reservation.getState();
        this.price = reservation.getPrice();
        this.tenant = reservation.getTenant().getFirstName() + " " + reservation.getTenant().getLastName();
        this.id = reservation.getId();
    }

    public String getName() {
        return name;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public ReservationState getState() {
        return state;
    }

    public Float getPrice() {
        return price;
    }

    public String getTenant() {
        return tenant;
    }

    public Long getId() {
        return id;
    }
}
