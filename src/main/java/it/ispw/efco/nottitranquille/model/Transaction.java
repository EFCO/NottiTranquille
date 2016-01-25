package it.ispw.efco.nottitranquille.model;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Transaction {

    private float price;

    private String to;

    /**
     * Default constructor
     */
    public Transaction() {
    }

    public Transaction(float price, String to) {
        this.price = price;
        this.to = to;
    }
}