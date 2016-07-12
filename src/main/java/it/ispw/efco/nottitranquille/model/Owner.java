package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.util.*;

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