package it.ispw.efco.nottitranquille.model;

import javax.persistence.EntityManager;

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

    public Structure select(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        Structure structure = entityManager.find(Structure.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return structure;
    }

    public void delete(Structure structure) throws Exception {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(structure);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}