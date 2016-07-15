package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO for {@link Location} entity.
 *
 * @see Location
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationDAO {

    /**
     * Stores {@link Location} into persistent system.
     *
     * @param newLocation the Location to persist
     * @param structure the Structure
     */
    public void store(Location newLocation, Structure structure) {
        EntityManager entityManager = it.ispw.efco.nottitranquille.model.JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        structure.addLocation(newLocation);
        entityManager.merge(structure);
        entityManager.getTransaction().commit();
        entityManager.close();
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
     * @param locationId the id of the Location to deleteWhitMerge
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

    public void deleteWhitMerge(Location locationToDelete) {
        EntityManager entityManager = it.ispw.efco.nottitranquille.model.JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(locationToDelete));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
