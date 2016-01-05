package it.ispw.efco.nottitranquille.model;

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
		return price + (price * fee/100);
	}

	public static final class Builder extends Fee.Builder<PercentageFee, Builder> {

		protected PercentageFee createObject() {
			return new PercentageFee(this);
		}

		protected Builder thisObject() {
			return this;
		}
	}
}