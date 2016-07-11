package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;

/**
 * Created by Federico on 27/06/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Role {

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
