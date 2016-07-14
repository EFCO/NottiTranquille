package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO for {@link Location} entity.
 *
 * @see Location
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationDao {

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
     * Updates {@link Location} into persistent system with the given Location's state.
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
     * Retrieves {@link Location} from persistent system.
     *
     * @return the list of all Location into persistent system
     */
    public static List<Location> retrieveLocations() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Location", Location.class)
                .getResultList();
    }

    /**
     * Deletes {@link Location} from persistent system.
     *
     * @param locationId the id of the Location to delete
     */
    public static void delete(long locationId) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Location locationLoaded = entityManager.find(Location.class, locationId);
        entityManager.remove(locationLoaded);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Location} from persistent system.
     *
     * @param locationToDelete the Location to remove
     */
    public static void delete(Location locationToDelete) {
        delete(locationToDelete.getId());
    }
}
