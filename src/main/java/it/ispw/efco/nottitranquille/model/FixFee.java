package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * FixFee is one of two concrete {@link Price}'s {@link Decorator}.
 * <br>
 * It applies a fix {@link Fee} to {@link Price}'s value.
 *
 * @see Price
 * @see Decorator
 * @see Fee
 * @see PercentageFee
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("FixFee")
public class FixFee extends Fee {

	/**
	 * Default constructor
	 */
	protected FixFee() {
	}

    /**
     * Builder constructor
     */
    protected FixFee(Builder builder) {
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
        return price + this.value;
	}

    public static final class Builder extends Fee.Builder<FixFee, Builder> {

        /**
         * {@inheritDoc}
         */
		@Override
        protected FixFee createObject() {
            return new FixFee(this);
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