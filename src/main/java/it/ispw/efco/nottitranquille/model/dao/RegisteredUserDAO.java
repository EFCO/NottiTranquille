package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.RegisteredUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class RegisteredUserDAO {

    /**
     * Stores {@link RegisteredUser} into persistent system.
     *
     * @param user the {@link RegisteredUser} to persist
     */
    public static void store(RegisteredUser user) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link RegisteredUser} into persistent system
     *
     * @param toUpdate the {@link RegisteredUser} to update with the new state
     */
    public static void update(RegisteredUser toUpdate, Class<?> type) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        RegisteredUser managerLoaded = (RegisteredUser) entityManager.find(type, toUpdate.getId());
        managerLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link RegisteredUser } from persistent system.
     *
     * @param toDelete the  {@link RegisteredUser} to remove
     */
    public static void delete(RegisteredUser toDelete, Class<?> type) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        RegisteredUser managerLoaded = (RegisteredUser) entityManager.find(type, toDelete.getId());
        entityManager.remove(managerLoaded);

        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("JpaQlInspection")
    public static Object findByUserName(String userName, Class<?> type) throws NoResultException {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from " + type.getName() + " where (username = :name)", type)
                    .setParameter("name", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static Object findByNameAndPassword(String userName, String passWord, Class<?> type)
            throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from " + type.getName() + " where " +
                    " (username = :name) and (password = :pass) ", type)
                    .setParameter("name", userName)
                    .setParameter("pass", passWord)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

}


