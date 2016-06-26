package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class TenantDAO {

    /**
     * Stores {@link Tenant} into persistent system.
     *
     * @param tenant the {@link Tenant} to persist
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
     * @param toUpdate the {@link Tenant} to update with the new state
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
     * @param toDelete the  {@link Tenant} to remove
     */
    public static void delete(Tenant toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Tenant managerLoaded = entityManager.find(Tenant.class, toDelete.getId());
        entityManager.remove(managerLoaded);

        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("JpaQlInspection")
    public static Tenant findByUserName(String tenantName) throws NoResultException {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from Tenant where (username = :name)", Tenant.class)
                    .setParameter("name", tenantName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static Tenant findByNameAndPassword(String tenantName, String passWord)
            throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Tenant t where " +
                    " (username = :name) and (password = :pass) ", Tenant.class)
                    .setParameter("name", tenantName)
                    .setParameter("pass", passWord)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static Tenant findbyId(Long id) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.find(Tenant.class, id);

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

}


