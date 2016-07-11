package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Federico on 11/07/2016.
 */
@Entity
@DiscriminatorValue("administrator")
public class Administrator extends Role {
}
