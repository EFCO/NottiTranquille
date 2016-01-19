package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Tenant;

import javax.persistence.EntityManager;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class TenantDao {

    /**
     * Stores {@link Tenant} into persistent system.
     *
     * @param tenant the Tenant to persist
     */
    public static void store(Tenant tenant) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(tenant);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link Tenant} into persistent system
     *
     * @param toUpdate the Tenant to update with the new state
     */
    public static void update(Tenant toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

       Tenant tenantLoaded = entityManager.find(Tenant.class, toUpdate.getId());
        tenantLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Tenant } from persistent system.
     *
     * @param toDelete the Tenant to remove
     */
    public static void delete(Tenant toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Tenant tenantLoaded = entityManager.find(Tenant.class, toDelete.getId());
        entityManager.remove(tenantLoaded);

        entityManager.getTransaction().commit();
    }


}
