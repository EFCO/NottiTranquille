package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.StructureType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("manager")
public class Manager extends Role implements Applicant {

    @OneToMany(mappedBy = "managedBy", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Structure> structures;

    /**
     * Default constructor
     */
    public Manager() {
        this.structures = new ArrayList<Structure>();
    }


    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public void removeStructure(Structure structure) {
        structures.remove(structure);
    }

    private void setStructures(List<Structure> structures) {
        this.structures = structures;
    }


    public List<Structure> getStructures() {
        return structures;
    }

    public List<Structure> getStructuresByType(StructureType type) {
        List<Structure> structuresByType = new ArrayList<Structure>();

        for (Structure structure : structures) {
            if (structure.getType() == type) {
                structuresByType.add(structure);
            }
        }

        return structuresByType;
    }

}