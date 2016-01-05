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
     * Builder constructor
     */
    public Discount(Builder builder) {
        super(builder);

        if (builder.discount < 0) {
            throw new IllegalStateException("The discount value must be set grater than zero!");
        } else {
            this.discount = builder.discount;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Discount{" +
                "discount=" + discount +
                '}';
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
         * The Discount's value
         */
        protected double discount = -1;

        /**
         * Sets Discount's value.
         *
         * @param discount the discount
         * @return the builder itself
         */
        public B setDiscount(double discount) {
            // Sets a default value grater than 0 in order to avoid IllegalStateException in Price(builder) construction
            this.priceValue = 1;

            this.discount = discount;
            return thisObject;
        }
    }
}