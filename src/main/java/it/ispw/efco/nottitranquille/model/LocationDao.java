package it.ispw.efco.nottitranquille.model;

import javax.persistence.EntityManager;

/**
 * Created by claudio on 7/10/16.
 */
public class LocationDAO {
    public void store(Location newLocation, Structure structure) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        structure.addLocation(newLocation);
        entityManager.merge(structure);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
