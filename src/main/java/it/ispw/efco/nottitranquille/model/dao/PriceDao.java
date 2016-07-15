package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.PriceBean;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import java.util.List;

/**
 * DAO for {@link Price} entity.
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
public class PriceDAO {

    /**
     * Stores {@link Price} into persistent system.
     *
     * @param priceToStore the Price to persist
     * @throws Exception if the entity is null
     */
    public static void store(Price priceToStore) throws Exception {
        if (priceToStore != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(priceToStore);

            entityManager.getTransaction().commit();
        } else {
            throw new Exception("The entity can not be null!");
        }
    }

    /**
     * Stores {@link PriceBean} into persistent system.
     *
     * @param priceBeanToStore the PriceBean to persist
     * @throws Exception if the entity is null
     */
    public static void store(PriceBean priceBeanToStore) throws Exception {
        store(Price.PriceFromBean(priceBeanToStore));
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
     * @param priceId the id of the Price to deleteWhitMerge
     */
    public static void delete(long priceId) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);
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
     * Returns the count of all {@link Price}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the Prices
     */
    public static Long countAllPrices(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link BasePrice}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the BasePrices
     */
    public static Long countAllBasePrices(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = BasePrice")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link Discount}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the Discounts
     */
    public static Long countAllDiscounts(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = PercentageDiscount or " +
                                "type(price) = FixDiscount")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link Fee}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the Fees
     */
    public static Long countAllFees(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = PercentageFee or " +
                                "type(price) = FixFee")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link FixDiscount}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the FixDiscounts
     */
    public static Long countAllFixDiscounts(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = FixDiscount")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link FixFee}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the FixFees
     */
    public static Long countAllFixFees(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = FixFee")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link PercentageDiscount}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the PercentageDiscounts
     */
    public static Long countAllPercentageDiscounts(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = PercentageDiscount")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Returns the count of all {@link PercentageFee}s for a certain {@link Location} into persistent system.
     *
     * @return the number of the PercentageFees
     */
    public static Long countAllPercentageFees(Location location) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return (Long) entityManager
                .createQuery("select count(price) from Location location JOIN location.prices price where " +
                                "location.id = :locationId and " +
                                "type(price) = PercentageFee")
                .setParameter("locationId", location.getId())
                .getSingleResult();
    }

    /**
     * Retrieves {@link Price}s from persistent system.
     *
     * @param startPosition the offset of result
     * @param maxResult the maximum number of results
     * @return the list of maxResult Prices into persistent system from startPosition
     */
    public static List<? extends Price> retrievePrices(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrieveBasePrices(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = BasePrice",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrieveFees(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = PercentageFee or " +
                        "type(price) = FixFee",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrieveDiscounts(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = PercentageDiscount or " +
                        "type(price) = FixDiscount",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrieveFixDiscounts(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = FixDiscount",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrieveFixFees(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = FixFee",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrievePercentageFees(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = PercentageFee",
                        Price.class)
                .setParameter("locationId", location.getId())
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
    public static List<? extends Price> retrievePercentageDiscounts(Location location, int startPosition, int maxResult) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("select price from Location location JOIN location.prices price where " +
                        "location.id = :locationId and " +
                        "type(price) = PercentageDiscount",
                        Price.class)
                .setParameter("locationId", location.getId())
                .setFirstResult(startPosition)
                .setMaxResults(maxResult)
                .getResultList();
    }
}