package it.ispw.efco.nottitranquille.model;

/**
 * Discount is one of two abstract decoration that are allowed for {@link Price}.
 *
 * <p>
 *      This abstract class is realized by {@link FixDiscount} and {@link PercentageDiscount}.
 * </p>
 *
 * @see Price
 * @see Decorator
 * @see FixDiscount
 * @see PercentageDiscount
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public abstract class Discount extends Decorator {

	/**
     * The Discount's value
	 */
	protected double discount;

	/**
	 * Default constructor
     */
	protected Discount() {
	}

    /**
     * Applies discount to {@link Price}'s value.
     *
     * @param price the Price's value
     * @return  the new Price's value
     */
    protected abstract double applyDiscount(double price);

    protected static abstract class Builder<T extends Discount, B extends Builder<T, B>> extends Decorator.Builder<T, B> {

        /**
         * Sets Discount's value.
         *
         * @param discount the discount
         * @return the builder itself
         */
        public B setDiscount(double discount) {
            object.discount = discount;
            return thisObject;
        }
    }
}