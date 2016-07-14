package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Federico on 27/06/2016.
 */
@Entity
@DiscriminatorValue("designer")
public class Designer extends Role {
}
