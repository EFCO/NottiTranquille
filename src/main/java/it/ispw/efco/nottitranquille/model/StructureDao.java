package it.ispw.efco.nottitranquille.model;

import javax.persistence.EntityManager;

public class StructureDAO {

    /**
     * Stores {@link Structure} into persistent system.
     *
     * @param structure the Price to persist
     */
    public void store(Structure structure, Long id) throws Exception {
        if (structure != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            Person manager = entityManager.find(Person.class, id);
            entityManager.getTransaction().begin();
            ((Manager) manager.getRole("Manager")).addStructure(structure);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            throw new Exception("The entity can not be null!");
        }
    }
}