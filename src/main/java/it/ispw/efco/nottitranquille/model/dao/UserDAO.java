package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class UserDAO {

    /**
     * Stores {@link Person} into persistent system.
     *
     * @param person the {@link Person} to persist
     */
    public static void store(Person person) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(person);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Person } from persistent system.
     *
     * @param toDelete the  {@link Person} to remove
     */
    public static void delete(Person toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Person personLoaded = entityManager.find(Person.class, toDelete.getId());
        entityManager.remove(personLoaded);

        entityManager.getTransaction().commit();
    }

    public static Person findbyId(Long id) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.find(Person.class, id);

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static Class discriminator(String username) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select TYPE(e) from Person e where username = :name", Class.class)
                    .setParameter("name", username)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

}


