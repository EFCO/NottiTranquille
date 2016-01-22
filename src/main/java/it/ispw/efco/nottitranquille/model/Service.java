package it.ispw.efco.nottitranquille.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Service {

    /**
     * Default constructor
     */
    public Service() {
    }

    /**
     * 
     */
    private String serviceName;

    /**
     * 
     */
    private Boolean selectable;

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }



}