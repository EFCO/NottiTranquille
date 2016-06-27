package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("owner")
public class Owner extends Role {

    /**
     * Default constructor
     */
    public Owner() {
    }
}