package it.ispw.efco.nottitranquille.model;

/**
 * Decorator is the abstract component that allow decoration of {@link Price}.
 *
 * @see Price
 * @see BasePrice
 * @see Fee
 * @see Discount
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public abstract class Decorator extends Price {

    /**
     * The instance of Price to decorate
     */
	protected Price price;

	/**
	 * Default constructor
	 */
	protected Decorator() {
	}

    /**
     * Builder constructor
     */
    protected Decorator(Builder builder) {
        super(builder);

        // DO NOT CHECK if null because it could be set later
        this.price = builder.price;
    }

    /**
     * Applies itself to a {@link Price}.
     *
     * @param price the price to decorate
     */
    public void applyTo(Price price) {
        this.price = price;
    }

	/**
     * {@inheritDoc}
	 */
	public double showPrice() {
		return this.price.showPrice();
	}

    protected static abstract class Builder<T extends Decorator, B extends Builder<T, B>> extends Price.Builder<T, B> {

        /**
         * The instance of Price to decorate
         */
        protected Price price;

        /**
         * Applies itself to a {@link Price}.
         *
         * @param price the price to decorate
         * @return  the builder itself
         */
        public B applyTo(Price price) {
            this.price = price;
            return thisObject;
        }
    }
}