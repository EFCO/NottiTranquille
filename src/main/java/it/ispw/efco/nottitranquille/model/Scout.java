package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
@DiscriminatorValue("Scout")
public class Scout extends Person implements Applicant {

    /**
     * Default constructor
     */
    public Scout() {
    }


}