package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO for {@link Reservation} entity.
 *
 * Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

public class ReservationDAO {

    /**
     * Stores {@link Reservation} into persistent system.
     *
     * @param reservation the Price to persist
     */
    public static void store(Reservation reservation) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(reservation);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link Reservation} into persistent system
     *
     * @param reservationToUpdate the Price to update with the new state
     */
    public static void update(Reservation reservationToUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Reservation reservationLoaded = entityManager.find(Reservation.class, reservationToUpdate.getId());
        reservationLoaded.update(reservationToUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Reservation } from persistent system.
     *
     * @param reservationToDelete the Price to remove
     */
    public static void delete(Reservation reservationToDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Reservation reservationLoaded = entityManager.find(Reservation.class, reservationToDelete.getId());
        entityManager.remove(reservationLoaded);

        entityManager.getTransaction().commit();
    }


    public static List<Reservation> findAllReservation() {
        EntityManager em = JPAInitializer.getEntityManager();
        List<Reservation> events = em.createQuery("from Reservation", Reservation.class)
                .getResultList();
        return events;
    }

    @SuppressWarnings("JpaQlInspection")
    public static List<Reservation> findPaidReservation() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager.createQuery("from Reservation where " +
                                " (state = Paid) ", Reservation.class).getResultList();

    }

    @SuppressWarnings("JpaQlInspection")
    public static List<Reservation> findToPayReservation() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager.createQuery("from Reservation where " +
                " (state = ToPay) ", Reservation.class).getResultList();
    }






}