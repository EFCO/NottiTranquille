package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("tenant")
public class Tenant extends Role {

    /**
     * 
     */
    @Transient
    private List<Reservation> reservations;

    public Tenant() {
        super();
    }

    /**
     * @param location
     */
    public void reserve(Location location) {
        // TODO implement here
    }

}