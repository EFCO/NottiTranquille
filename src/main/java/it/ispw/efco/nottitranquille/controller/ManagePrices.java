package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.DAO.LocationDAO;
import it.ispw.efco.nottitranquille.model.DAO.PriceDAO;
import it.ispw.efco.nottitranquille.view.PriceBean;

import java.util.*;

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
        PriceDAO priceDAO = new PriceDAO();
        return (List<Price>) priceDAO.retrievePrices(location, 0, countAllPrices(location));
	}

    /**
     * Fetches all {@link BasePrice}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all BasePrice
     */
    public static List<BasePrice> fetchAllBasePrices(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<BasePrice>) priceDAO.retrieveBasePrices(location, 0, countAllBasePrices(location));
    }

    /**
     * Fetches all {@link Discount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all Discount
     */
    public static List<Discount> fetchAllDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<Discount>) priceDAO.retrieveDiscounts(location, 0, countAllDiscounts(location));
    }

    /**
     * Fetches all {@link Fee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all Fee
     */
    public static List<Fee> fetchAllFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<Fee>) priceDAO.retrieveFees(location, 0, countAllFees(location));
    }

    /**
     * Fetches all {@link FixDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all FixDiscount
     */
    public static List<FixDiscount> fetchAllFixDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<FixDiscount>) priceDAO.retrieveFixDiscounts(location, 0, countAllFixDiscounts(location));
    }

    /**
     * Fetches all {@link FixFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all FixFee
     */
    public static List<FixFee> fetchAllFixFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<FixFee>) priceDAO.retrieveFixFees(location, 0, countAllFixFees(location));
    }

    /**
     * Fetches all {@link PercentageDiscount}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all PercentageDiscount
     */
    public static List<PercentageDiscount> fetchAllPercentageDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<PercentageDiscount>) priceDAO.retrievePercentageDiscounts(location, 0, countAllPercentageDiscounts(location));
    }

    /**
     * Fetches all {@link PercentageFee}s of a certain {@link Location}.
     *
     * @param location the location from where fetch prices
     * @return the list of all PercentageFee
     */
    public static List<PercentageFee> fetchAllPercentageFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (List<PercentageFee>) priceDAO.retrievePercentageFees(location, 0, countAllPercentageFees(location));
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<Price>) priceDAO.retrievePrices(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<BasePrice>) priceDAO.retrieveBasePrices(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<Discount>) priceDAO.retrieveDiscounts(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<Fee>) priceDAO.retrieveFees(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<FixDiscount>) priceDAO.retrieveFixDiscounts(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<FixFee>) priceDAO.retrieveFixFees(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<PercentageDiscount>) priceDAO.retrievePercentageDiscounts(location, startPosition, limit);
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
        PriceDAO priceDAO = new PriceDAO();
        return (List<PercentageFee>) priceDAO.retrievePercentageFees(location, startPosition, limit);
    }

    /**
     * Counts all {@link Price} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Price
     */
    public static int countAllPrices(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return (priceDAO.countAllPrices(location)).intValue();
    }

    /**
     * Counts all {@link BasePrice} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of BasePrice
     */
    public static int countAllBasePrices(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllBasePrices(location).intValue();
    }

    /**
     * Counts all {@link Discount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Discount
     */
    public static int countAllDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllDiscounts(location).intValue();
    }

    /**
     * Counts all {@link Fee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of Fee
     */
    public static int countAllFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllFees(location).intValue();
    }

    /**
     * Counts all {@link FixDiscount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of FixDiscount
     */
    public static int countAllFixDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllFixDiscounts(location).intValue();
    }

    /**
     * Counts all {@link FixFee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of FixFee
     */
    public static int countAllFixFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllFixFees(location).intValue();
    }

    /**
     * Counts all {@link PercentageDiscount} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of PercentageDiscount
     */
    public static int countAllPercentageDiscounts(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllPercentageDiscounts(location).intValue();
    }

    /**
     * Counts all {@link PercentageFee} of a certain {@link Location}.
     *
     * @param location the location where count prices
     * @return the number of PercentageFee
     */
    public static int countAllPercentageFees(Location location) {
        PriceDAO priceDAO = new PriceDAO();
        return priceDAO.countAllPercentageFees(location).intValue();
    }

    /**
     * Adds {@link Price} to {@link Location}.
     *
     * @param location the location to update
     * @param price the price to add
     */
    public static void addPrice(Location location, PriceBean price) {
        location.addPrice(Price.PriceFromBean(price));
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.update(location);
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
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.update(location);
    }

    /**
     * Deletes {@link Price} of a {@link Location}.
     *
     * @param location the location to update
     * @param price the price to delete
     */
    public static void deletePrice(Location location, PriceBean price) {
        // Removes price from location and update DB
        location.removePrice(price.getId());

        LocationDAO locationDAO = new LocationDAO();
        locationDAO.update(location);
        // Removes also price from the DB
        PriceDAO priceDAO = new PriceDAO();
        priceDAO.delete(price.getId());
    }
}