package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * FixDiscount is one of two concrete {@link Price}'s {@link Decorator}.
 * <br>
 * It applies a fix {@link Discount} to {@link Price}'s value.
 *
 * @see Price
 * @see Decorator
 * @see Discount
 * @see PercentageDiscount
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("FixDiscount")
public class FixDiscount extends Discount {

    /**
     * Default constructor
     */
    protected FixDiscount() {
    }

    /**
     * Builder constructor
     */
    public FixDiscount(Builder builder) {
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
		return price - this.value;
	}

    public static final class Builder extends Discount.Builder<FixDiscount, Builder> {

        /**
         * {@inheritDoc}
         */
        @Override
        protected FixDiscount createObject() {
            return new FixDiscount(this);
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