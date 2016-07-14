package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;

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
@Entity
public abstract class Discount extends Decorator {

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
    }

    @Override
    public String toString() {
        return "Discount = " + super.toString();
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
            this.value = discount;
            return thisObject;
        }
    }
}