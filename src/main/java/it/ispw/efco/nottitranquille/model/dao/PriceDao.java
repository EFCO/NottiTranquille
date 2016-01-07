package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.JPAInitializer;
import it.ispw.efco.nottitranquille.model.*;
import org.joda.time.Interval;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO for {@link Price} entity.
 *
 *
 * @see Price
 * @see BasePrice
 * @see Discount
 * @see FixDiscount
 * @see PercentageDiscount
 * @see FixFee
 * @see PercentageFee
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PriceDao {

    /**
     * Stores {@link Price} into persistent system.
     *
     * @param price the Price to persist
     */
    public static void store(Price price) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(price);

        entityManager.getTransaction().commit();
    }

    /**
     * Updates {@link Price} into persistent system with the given Price's state.
     *
     * @param priceToUpdate the Price to update with the new state
     */
    public static void update(Price priceToUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Price priceLoaded = entityManager.find(Price.class, priceToUpdate.getId());
        priceLoaded.update(priceToUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Price} from persistent system.
     *
     * @param priceToDelete the Price to remove
     */
    public static void delete(Price priceToDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Price priceLoaded = entityManager.find(Price.class, priceToDelete.getId());
        entityManager.remove(priceLoaded);

        entityManager.getTransaction().commit();
    }

    /**
     * Retrieves all {@link Price}s from persistent system.
     *
     * @return the list of all Prices into persistent system
     */
    public static List<Price> retrieveAllPrices() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Price", Price.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link Price}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all Prices into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Price> retrieveAllPrices(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Price where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Price.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link BasePrice} from persistent system.
     *
     * @return the list of all BasePrice into persistent system
     */
    public static List<BasePrice> retrieveAllBasePrices() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from BasePrice", BasePrice.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link BasePrice}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all BasePrices into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Price> findAllBasePrices(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Price where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Price.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link Fee}s from persistent system.
     *
     * @return the list of all Fees into persistent system
     */
    public static List<Fee> findAllFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Fee", Fee.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link Fee}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all Fees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Fee> findAllFees(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Fee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Fee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link Discount}s from persistent system.
     *
     * @return the list of all Discounts into persistent system
     */
    public static List<Discount> findAllDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Discount", Discount.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link Discount}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all Discounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Discount> findAllDiscounts(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Discount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Discount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link FixDiscount}s from persistent system.
     *
     * @return the list of all FixDiscounts into persistent system
     */
    public static List<FixDiscount> findAllFixDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixDiscount", FixDiscount.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link FixDiscount}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all FixDiscounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<FixDiscount> findAllFixDiscounts(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixDiscount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        FixDiscount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link FixFee}s from persistent system.
     *
     * @return the list of all FixFees into persistent system
     */
    public static List<FixFee> findAllFixFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixFee", FixFee.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link FixFee}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all FixFees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<FixFee> findAllFixFees(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixFee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        FixFee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link PercentageFee}s from persistent system.
     *
     * @return the list of all PercentageFees into persistent system
     */
    public static List<PercentageFee> findAllPercentageFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageFee", PercentageFee.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link PercentageFee}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all PercentageFees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<PercentageFee> findAllPercentageFees(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageFee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        PercentageFee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }

    /**
     * Retrieves all {@link PercentageDiscount}s from persistent system.
     *
     * @return the list of all PercentageDiscounts into persistent system
     */
    public static List<PercentageDiscount> findAllPercentageDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageDiscount", PercentageDiscount.class)
                .getResultList();
    }

    /**
     * Retrieves all {@link PercentageDiscount}s that intersect a given {@link Interval} from persistent system.
     *
     * @return the list of all PercentageDiscounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<PercentageDiscount> findAllPercentageDiscounts(Interval interval) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageDiscount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        PercentageDiscount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .getResultList();
    }
}
