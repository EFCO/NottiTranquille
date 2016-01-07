package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;

/**
 * PercentageFee is one of two concrete {@link Price}'s {@link Decorator}.
 * <br>
 * It applies a percentage {@link Fee} to {@link Price}'s value.
 *
 * @see Price
 * @see Decorator
 * @see Fee
 * @see FixFee
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("PercentageFee")
public class PercentageFee extends Fee {

	/**
	 * Default constructor
	 */
	protected PercentageFee() {
	}

    /**
     * Builder constructor
     */
    protected PercentageFee(Builder builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
	public double showPrice() {
		return this.applyFee(super.showPrice());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	protected double applyFee(double price) {
		return price + (price * this.value/100);
	}

	public static final class Builder extends Fee.Builder<PercentageFee, Builder> {

        /**
         * {@inheritDoc}
         */
		@Override
		protected PercentageFee createObject() {
			return new PercentageFee(this);
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