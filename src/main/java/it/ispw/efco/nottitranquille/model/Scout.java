package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("scout")
public class Scout extends Role implements Applicant {

    /**
     * Default constructor
     */
    public Scout() {
    }


    public void addStructure(Structure structure) {

    }
}