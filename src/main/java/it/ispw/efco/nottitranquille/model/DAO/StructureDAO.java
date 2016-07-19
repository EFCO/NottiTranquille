package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Structure;

import javax.persistence.EntityManager;
import java.util.List;

public class StructureDAO {

    /**
     * Stores {@link Structure} into persistent system.
     *
     * @param structure the Price to persist
     */
    public void store(Structure structure, Person manager, Person owner) throws Exception {
        if (structure != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(structure);
            entityManager.merge(manager);
            if (owner != null)
                entityManager.merge(owner);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            throw new Exception("The entity can not be null!");
        }
    }

    public List<Structure> retrieveStructures() {
        EntityManager entityManager = it.ispw.efco.nottitranquille.JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Structure ", Structure.class)
                .getResultList();
    }
}