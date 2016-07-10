package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.StructureBean;
import org.joda.time.Interval;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * DAO for {@link Structure} entity.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class StructureDao {

    /**
     * Stores {@link Structure} into persistent system.
     *
     * @param structure the Price to persist
     */
    public static void store(Structure structure) throws Exception {
        if (structure != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(structure);

            entityManager.getTransaction().commit();
        } else {
            throw new Exception("The entity can not be null!");
        }
    }

    /**
     * Stores {@link it.ispw.efco.nottitranquille.view.StructureBean} into persistent system.
     *
     * @param priceToUpdate the PriceBean to persist
     */
    public static void store(StructureBean priceToUpdate) throws Exception {
        // TODO
        //store(Structure.PriceFromBean(priceToUpdate));
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
     * Updates {@link Price} into persistent system with the given Price's state.
     *
     * @param priceToUpdate the Price to update with the new state
     */
    public static void update(PriceBean priceToUpdate) {
        update(Price.PriceFromBean(priceToUpdate));
    }

    /**
     * Deletes {@link Price} from persistent system.
     *
     * @param priceId the id of the Price to delete
     */
    public static void delete(long priceId) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Price priceLoaded = entityManager.find(Price.class, priceId);
        entityManager.remove(priceLoaded);

        entityManager.getTransaction().commit();
    }

    /**
     * Deletes {@link Price} from persistent system.
     *
     * @param priceToDelete the Price to remove
     */
    public static void delete(Price priceToDelete) {
        delete(priceToDelete.getId());
    }

    /**
     * Returns the count of all {@link Price}s into persistent system.
     *
     * @return the number of the Prices
     */
    public static long countAllPrices() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(Price.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link BasePrice}s into persistent system.
     *
     * @return the number of the BasePrices
     */
    public static long countAllBasePrices() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(BasePrice.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link Discount}s into persistent system.
     *
     * @return the number of the Discounts
     */
    public static long countAllDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(Discount.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link Fee}s into persistent system.
     *
     * @return the number of the Fees
     */
    public static long countAllFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(Fee.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link FixDiscount}s into persistent system.
     *
     * @return the number of the FixDiscounts
     */
    public static long countAllFixDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(FixDiscount.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link FixFee}s into persistent system.
     *
     * @return the number of the FixFees
     */
    public static long countAllFixFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(FixFee.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link PercentageDiscount}s into persistent system.
     *
     * @return the number of the PercentageDiscounts
     */
    public static long countAllPercentageDiscounts() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(PercentageDiscount.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Returns the count of all {@link PercentageFee}s into persistent system.
     *
     * @return the number of the PercentageFees
     */
    public static long countAllPercentageFees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(PercentageFee.class)));

        return entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
    }

    /**
     * Retrieves {@link Price}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of maxResult Prices into persistent system from startPosition
     */
    public static List<Price> retrievePrices(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Price", Price.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link Price}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all Prices into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Price> retrievePrices(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Price where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Price.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link BasePrice} from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all BasePrice into persistent system
     */
    public static List<BasePrice> retrieveBasePrices(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from BasePrice", BasePrice.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link BasePrice}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all BasePrices into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Price> retrieveBasePrices(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from BasePrice where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Price.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link Fee}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all Fees into persistent system
     */
    public static List<Fee> retrieveFees(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Fee", Fee.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link Fee}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all Fees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Fee> retrieveFees(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Fee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Fee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link Discount}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all Discounts into persistent system
     */
    public static List<Discount> retrieveDiscounts(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Discount", Discount.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link Discount}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all Discounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<Discount> retrieveDiscounts(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Discount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        Discount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link FixDiscount}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all FixDiscounts into persistent system
     */
    public static List<FixDiscount> retrieveFixDiscounts(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixDiscount", FixDiscount.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link FixDiscount}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all FixDiscounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<FixDiscount> retrieveFixDiscounts(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixDiscount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        FixDiscount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link FixFee}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all FixFees into persistent system
     */
    public static List<FixFee> retrieveFixFees(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixFee", FixFee.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link FixFee}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all FixFees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<FixFee> retrieveFixFees(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from FixFee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        FixFee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link PercentageFee}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all PercentageFees into persistent system
     */
    public static List<PercentageFee> retrievePercentageFees(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageFee", PercentageFee.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link PercentageFee}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all PercentageFees into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<PercentageFee> retrievePercentageFees(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageFee where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        PercentageFee.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link PercentageDiscount}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all PercentageDiscounts into persistent system
     */
    public static List<PercentageDiscount> retrievePercentageDiscounts(int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageDiscount", PercentageDiscount.class)
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }

    /**
     * Retrieves {@link PercentageDiscount}s that intersect a given {@link Interval} from persistent system.
     *
     * @param interval  the interval in which we are interested to retrieve Prices
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of all PercentageDiscounts into persistent system valid in the given Interval
     */
    @SuppressWarnings("JpaQlInspection")
    public static List<PercentageDiscount> retrievePercentageDiscounts(Interval interval, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from PercentageDiscount where " +
                                "(interval.begin >= :startDate and interval.begin <= :endDate) or " +
                                "(interval.end >= :startDate and interval.end <= :endDate) or " +
                                "(interval.begin <= :startDate and interval.end >= :endDate)",
                        PercentageDiscount.class)
                .setParameter("startDate", interval.getStart())
                .setParameter("endDate", interval.getEnd())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }
}