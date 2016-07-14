package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Service {

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
    /**
     *
     */
    @Transient
    private BasePrice price;


    public Long getId() {
        return id;
    }
    /**
     * Default constructor
     */
    public Service() {
    }

    /**
     *
     * @return
     */
    public BasePrice getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(BasePrice price) {
        this.price = price;
    }
}