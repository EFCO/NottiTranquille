package it.ispw.efco.nottitranquille.model.dao;


import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Reservation;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO for {@link it.ispw.efco.nottitranquille.model.Location} entity.
 * <p/>
 * Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

public class LocationDAO {

    /**
     * Stores {@link Location} into persistent system.
     *
     * @param location the Location to persist
     */
    public static void store(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(location);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link Location} into persistent system
     *
     * @param locationToUpdate the Location to update with the new state
     */
    public static void update(Location locationToUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Location locationLoaded = entityManager.find(Location.class, locationToUpdate.getId());
        locationLoaded.update(locationToUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Location } from persistent system.
     *
     * @param toDelete the Location to remove
     */
    public static void delete(Location toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Location locationLoaded = entityManager.find(Location.class, toDelete.getId());
        entityManager.remove(locationLoaded);

        entityManager.getTransaction().commit();
    }

    public static List<Location> findAllLocation() {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        return entityManager.createQuery("select t from Location t", Location.class)
                .getResultList();
    }

    public static Location findByID(Long id) {

        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager.find(Location.class, id);
    }
}
