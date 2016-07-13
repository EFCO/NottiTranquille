package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("owner")
public class Owner extends Role {


    @ManyToMany(mappedBy = "owners", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Structure> ownedStructures;
    /**
     * Default constructor
     */
    public Owner() {
        this.ownedStructures = new ArrayList<Structure>();
    }

    public void addOwnedStructure(Structure structure) {
        this.ownedStructures.add(structure);
    }

    public List<Structure> getOwnedStructures() {
        return ownedStructures;
    }


}