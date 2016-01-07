package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * PercentageDiscount is one of two concrete {@link Price}'s {@link Decorator}.
 * <br>
 * It applies a percentage {@link Discount} to {@link Price}'s value.
 *
 * @see Price
 * @see Decorator
 * @see Discount
 * @see FixDiscount
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("PercentageDiscount")
public class PercentageDiscount extends Discount {

    /**
     * Default constructor
     */
	protected PercentageDiscount() {
	}

    /**
     * Builder constructor
     */
    public PercentageDiscount(Builder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
	public double showPrice() {
		return this.applyDiscount(super.showPrice());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	protected double applyDiscount(double price) {
		return price - (price * this.value/100);
	}

    public static final class Builder extends Discount.Builder<PercentageDiscount, Builder> {

        /**
         * {@inheritDoc}
         */
        @Override
        protected PercentageDiscount createObject() {
            return new PercentageDiscount(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Builder thisObject() {
            return this;
        }
    }
}