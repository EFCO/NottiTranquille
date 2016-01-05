package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Manager extends Person implements Applicant {

    /**
     * Default constructor
     */
    public Manager() {
    }

    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}