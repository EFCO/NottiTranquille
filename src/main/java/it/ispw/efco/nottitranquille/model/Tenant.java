package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Tenant extends RegisteredUser {

    /**
     * Default constructor
     */
    public Tenant() {
    }

    /**
     * 
     */
    @OneToMany
    @JoinTable(name="Tenant_Reservation",
            joinColumns={@JoinColumn(name="ReservationId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="TenantsId", referencedColumnName="id")})
    public List<Reservation> reservations;

    public void update(Tenant toUpdate) {
        super.setId(toUpdate.getId());

    }


}