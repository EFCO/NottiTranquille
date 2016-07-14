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


    //    @ManyToMany(mappedBy = "owners", fetch = FetchType.EAGER)
    @ManyToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "Structure_Owner",
            joinColumns = {
                    @JoinColumn(
                            name = "owner_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "structure_id",
                            referencedColumnName = "id"
                    )
            }
    )
    List<Structure> ownedStructures = new ArrayList<Structure>();
    /**
     * Default constructor
     */
    public Owner() {
    }


    public List<Structure> getOwnedStructures() {
        return ownedStructures;
    }

}