package it.ispw.efco.nottitranquille.model;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Service {

    /**
     *
     */
    private String serviceName;

    /**
     *
     */
    private Boolean selectable;

    /**
     *
     */
    private BasePrice price;

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