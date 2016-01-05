package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Owner extends Person {

    /**
     * Default constructor
     */
    public Owner() {
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