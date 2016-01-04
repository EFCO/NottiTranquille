package it.ispw.efco.nottitranquille.model;

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
public class PercentageDiscount extends Discount {

    /**
     * Default constructor
     */
	protected PercentageDiscount() {
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
		return price - (price * discount/100);
	}

    public static final class Builder extends Discount.Builder<PercentageDiscount, Builder> {

        protected PercentageDiscount createObject() {
            return new PercentageDiscount();
        }

        protected Builder thisObject() {
            return this;
        }
    }
}