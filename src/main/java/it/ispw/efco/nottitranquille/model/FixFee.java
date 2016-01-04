package it.ispw.efco.nottitranquille.model;

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
public class FixFee extends Fee {

	/**
	 * Default constructor
	 */
	protected FixFee() {
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
        return price + fee;
	}

    public static final class Builder extends Fee.Builder<FixFee, Builder> {

        protected FixFee createObject() {
            return new FixFee();
        }

        protected Builder thisObject() {
            return this;
        }
    }
}