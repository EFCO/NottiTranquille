package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.DAO.LocationDAO;
import it.ispw.efco.nottitranquille.model.DAO.PriceDAO;
import it.ispw.efco.nottitranquille.view.PriceBean;

import java.util.List;

import static it.ispw.efco.nottitranquille.model.DAO.PriceDAO.*;

/**
 * ManagesPrices Controller.
 * <br>
 * This Use Case shows the process by which a Manager can manage the {@link Price} of his {@link Location} and payable
 * {@link Service}s.
 * <p>
 * For {@link Location}, he can set a {@link BasePrice} for a night or a more complex structure with {@link Discount}s and
 * {@link Fee}s addable in fix or percentage mode (added either as fixed or percentage modifier). He can also plan the
 * price trend during the week and during festivity.
 * </p>
 * <p>
 * For {@link Service}, he can only add a default price.
 * </p>
 *
 * @see Price
 * @see BasePrice
 * @see Discount
 * @see FixDiscount
 * @see PercentageDiscount
 * @see Fee
 * @see FixFee
 * @see PercentageFee
 * @see Service
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@SuppressWarnings("unchecked")
public class ManagePrices {

    /**
     * Fetches all {@link Price}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all Price
     */
	public static List<Price> fetchAllPrices(Location location) {
        return (List<Price>) retrievePrices(location, 0, countAllPrices(location));
	}

    /**
     * Fetches all {@link BasePrice}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all BasePrice
     */
    public static List<BasePrice> fetchAllBasePrices(Location location) {
        return (List<BasePrice>) retrieveBasePrices(location, 0, countAllBasePrices(location));
    }

    /**
     * Fetches all {@link Discount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all Discount
     */
    public static List<Discount> fetchAllDiscounts(Location location) {
        return (List<Discount>) retrieveDiscounts(location, 0, countAllDiscounts(location));
    }

    /**
     * Fetches all {@link Fee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all Fee
     */
    public static List<Fee> fetchAllFees(Location location) {
        return (List<Fee>) retrieveFees(location, 0, countAllFees(location));
    }

    /**
     * Fetches all {@link FixDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all FixDiscount
     */
    public static List<FixDiscount> fetchAllFixDiscounts(Location location) {
        return (List<FixDiscount>) retrieveFixDiscounts(location, 0, countAllFixDiscounts(location));
    }

    /**
     * Fetches all {@link FixFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all FixFee
     */
    public static List<FixFee> fetchAllFixFees(Location location) {
        return (List<FixFee>) retrieveFixFees(location, 0, countAllFixFees(location));
    }

    /**
     * Fetches all {@link PercentageDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all PercentageDiscount
     */
    public static List<PercentageDiscount> fetchAllPercentageDiscounts(Location location) {
        return (List<PercentageDiscount>) retrievePercentageDiscounts(location, 0, countAllPercentageDiscounts(location));
    }

    /**
     * Fetches all {@link PercentageFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all PercentageFee
     */
    public static List<PercentageFee> fetchAllPercentageFees(Location location) {
        return (List<PercentageFee>) retrievePercentageFees(location, 0, countAllPercentageFees(location));
    }

    /**
     * Fetches all {@link Price}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all Price
     */
    public static List<Price> fetchPrices(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<Price>) retrievePrices(location, startPosition, limit);
    }

    /**
     * Fetches all {@link BasePrice}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all BasePrice
     */
    public static List<BasePrice> fetchBasePrices(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<BasePrice>) retrieveBasePrices(location, startPosition, limit);
    }

    /**
     * Fetches all {@link Discount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all Discount
     */
    public static List<Discount> fetchDiscounts(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<Discount>) retrieveDiscounts(location, startPosition, limit);
    }

    /**
     * Fetches all {@link Fee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all Fee
     */
    public static List<Fee> fetchFees(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<Fee>) retrieveFees(location, startPosition, limit);
    }

    /**
     * Fetches all {@link FixDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all FixDiscount
     */
    public static List<FixDiscount> fetchFixDiscounts(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<FixDiscount>) retrieveFixDiscounts(location, startPosition, limit);
    }

    /**
     * Fetches all {@link FixFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all FixFee
     */
    public static List<FixFee> fetchFixFees(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<FixFee>) retrieveFixFees(location, startPosition, limit);
    }

    /**
     * Fetches all {@link PercentageDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all PercentageDiscount
     */
    public static List<PercentageDiscount> fetchPercentageDiscounts(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<PercentageDiscount>) retrievePercentageDiscounts(location, startPosition, limit);
    }

    /**
     * Fetches all {@link PercentageFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @param page the number page to show
     * @param limit the limit of element to show (equals to element into a page)
     * @return the list of all PercentageFee
     */
    public static List<PercentageFee> fetchPercentageFees(Location location, int page, int limit) {
        int startPosition = (page - 1) * (limit);
        return (List<PercentageFee>) retrievePercentageFees(location, startPosition, limit);
    }

    /**
     * Counts all {@link Price} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Price
     */
    public static int countAllPrices(Location location) {
        return (PriceDAO.countAllPrices(location)).intValue();
    }

    /**
     * Counts all {@link BasePrice} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of BasePrice
     */
    public static int countAllBasePrices(Location location) {
        return PriceDAO.countAllBasePrices(location).intValue();
    }

    /**
     * Counts all {@link Discount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Discount
     */
    public static int countAllDiscounts(Location location) {
        return PriceDAO.countAllDiscounts(location).intValue();
    }

    /**
     * Counts all {@link Fee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Fee
     */
    public static int countAllFees(Location location) {
        return PriceDAO.countAllFees(location).intValue();
    }

    /**
     * Counts all {@link FixDiscount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of FixDiscount
     */
    public static int countAllFixDiscounts(Location location) {
        return PriceDAO.countAllFixDiscounts(location).intValue();
    }

    /**
     * Counts all {@link FixFee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of FixFee
     */
    public static int countAllFixFees(Location location) {
        return PriceDAO.countAllFixFees(location).intValue();
    }

    /**
     * Counts all {@link PercentageDiscount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of PercentageDiscount
     */
    public static int countAllPercentageDiscounts(Location location) {
        return PriceDAO.countAllPercentageDiscounts(location).intValue();
    }

    /**
     * Counts all {@link PercentageFee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of PercentageFee
     */
    public static int countAllPercentageFees(Location location) {
        return PriceDAO.countAllPercentageFees(location).intValue();
    }

    /**
     * Adds {@link Price} to {@link Location}.
     *
     * @param location the location to update
     * @param price the price to add
     */
    public static void addPrice(Location location, PriceBean price) {
        location.addPrice(Price.PriceFromBean(price));
        LocationDAO.update(location);
    }

    /**
     * Updates {@link Price} of a {@link Location}.
     *
     * @param location the location to update
     * @param price the price to update
     */
    public static void updatePrice(Location location, PriceBean price) {
        Price priceToUpdate = location.getPriceById(price.getId());
        priceToUpdate.update(Price.PriceFromBean(price));
        LocationDAO.update(location);
    }

    /**
     * Deletes {@link Price} of a {@link Location}.
     *
     * @param location the location to update
     * @param price the price to deleteWhitMerge
     */
    public static void deletePrice(Location location, PriceBean price) {
        // Removes price from location and update DB
        location.removePrice(price.getId());
        LocationDAO.update(location);
        // Removes also price from the DB
        delete(price.getId());
    }
}