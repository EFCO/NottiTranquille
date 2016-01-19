package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.LocationType;

import javax.persistence.EntityManager;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationTypeDAO {

    /**
     * Stores {@link it.ispw.efco.nottitranquille.model.LocationType} into persistent system.
     *
     * @param locationType the LocationType to persist
     */
    public static void store(LocationType locationType) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(locationType);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link LocationType} into persistent system
     *
     * @param toUpdate the LocationType to update with the new state
     */
    public static void update(LocationType toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        LocationType locationTypeLoaded = entityManager.find(LocationType.class, toUpdate.getId());
        locationTypeLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link LocationType } from persistent system.
     *
     * @param toDelete the LocationType to remove
     */
    public static void delete(LocationType toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        LocationType locationTypeLoaded = entityManager.find(LocationType.class, toDelete.getId());
        entityManager.remove(locationTypeLoaded);

        entityManager.getTransaction().commit();
    }

}
