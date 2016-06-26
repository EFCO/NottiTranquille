package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Manager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ManagerDAO {

    /**
     * Stores {@link Manager} into persistent system.
     *
     * @param manager the {@link Manager} to persist
     */
    public static void store(Manager manager) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(manager);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link Manager} into persistent system
     *
     * @param toUpdate the {@link Manager} to update with the new state
     */
    public static void update(Manager toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Manager managerLoaded = entityManager.find(Manager.class, toUpdate.getId());
        managerLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Manager } from persistent system.
     *
     * @param toDelete the  {@link Manager} to remove
     */
    public static void delete(Manager toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Manager managerLoaded = entityManager.find(Manager.class, toDelete.getId());
        entityManager.remove(managerLoaded);

        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("JpaQlInspection")
    public static Manager findByUserName(String managerName) throws NoResultException {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from Manager where (username = :name)", Manager.class)
                    .setParameter("name", managerName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static Manager findByNameAndPassword(String managerName, String passWord)
            throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from Manager where " +
                    " (managername = :name) and (password = :pass) ", Manager.class)
                    .setParameter("name", managerName)
                    .setParameter("pass", passWord)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static Manager findbyId(Long id) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.find(Manager.class, id);

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

}


